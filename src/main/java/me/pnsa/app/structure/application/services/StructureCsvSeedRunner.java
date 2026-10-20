package me.pnsa.app.structure.application.services;

import me.pnsa.app.address.infrastructure.entities.AddressEntity;
import me.pnsa.app.address.infrastructure.entities.PhoneNumberEntity;
import me.pnsa.app.address.infrastructure.repositories.AddressRepository;
import me.pnsa.app.address.infrastructure.repositories.PhoneNumberRepository;
import me.pnsa.app.auth.application.services.UserAuthService;
import me.pnsa.app.structure.infrastructure.entities.ServiceEntity;
import me.pnsa.app.structure.infrastructure.entities.StructureEntity;
import me.pnsa.app.structure.infrastructure.entities.StructureServiceEntity;
import me.pnsa.app.structure.infrastructure.repositories.ServiceRepository;
import me.pnsa.app.structure.infrastructure.repositories.StructureRepository;
import me.pnsa.app.structure.infrastructure.repositories.StructureServiceRepository;
import me.pnsa.app.users.infrastructure.entities.AccountEntity;
import me.pnsa.app.users.infrastructure.entities.AccountUserEntity;
import me.pnsa.app.users.infrastructure.entities.UserEntity;
import me.pnsa.app.users.infrastructure.repositories.AccountRepository;
import me.pnsa.app.users.infrastructure.repositories.AccountUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class StructureCsvSeedRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(StructureCsvSeedRunner.class);

    private final ResourceLoader resourceLoader;
    private final UserAuthService userAuthService;
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;
    private final StructureRepository structureRepository;
    private final AddressRepository addressRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final ServiceRepository serviceRepository;
    private final StructureServiceRepository structureServiceRepository;

    @Value("${app.seed.structures.enabled:false}")
    private boolean enabled;

    @Value("${app.seed.structures.resource:classpath:data/structures.csv}")
    private String csvResourcePath;

    @Value("${app.seed.structures.default-pin:1234}")
    private String defaultPin;

    public StructureCsvSeedRunner(
            ResourceLoader resourceLoader,
            UserAuthService userAuthService,
            AccountRepository accountRepository,
            AccountUserRepository accountUserRepository,
            StructureRepository structureRepository,
            AddressRepository addressRepository,
            PhoneNumberRepository phoneNumberRepository,
            ServiceRepository serviceRepository,
            StructureServiceRepository structureServiceRepository
    ) {
        this.resourceLoader = resourceLoader;
        this.userAuthService = userAuthService;
        this.accountRepository = accountRepository;
        this.accountUserRepository = accountUserRepository;
        this.structureRepository = structureRepository;
        this.addressRepository = addressRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.serviceRepository = serviceRepository;
        this.structureServiceRepository = structureServiceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!enabled) {
            return;
        }

        Resource resource = resourceLoader.getResource(csvResourcePath);
        if (!resource.exists()) {
            log.warn("Seed CSV not found at '{}'. Seed skipped.", csvResourcePath);
            return;
        }

        List<Map<String, String>> rows = readRows(resource);
        if (rows.isEmpty()) {
            log.info("No data rows found in '{}'.", csvResourcePath);
            return;
        }

        int loaded = 0;
        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            try {
                seedRow(row);
                loaded++;
            } catch (Exception ex) {
                log.warn("Seed row {} skipped: {}", i + 1, ex.getMessage());
            }
        }
        log.info("Structure CSV seed completed. {} rows processed.", loaded);
    }

    private void seedRow(Map<String, String> row) {
        String structureName = value(row, "Structure");
        String province = value(row, "Province");
        String city = value(row, "Ville");
        String fullAddress = value(row, "Adresse");
        String phoneNumber = normalizePhoneNumber(value(row, "Numéro Téléphone", "Numero Telephone", "Numéro Telephone"));
        String latitudeRaw = value(row, "Latitude", "Y");
        String longitudeRaw = value(row, "Longitude", "X");
        String servicesRaw = value(row, "Services disponibles");

        if (!StringUtils.hasText(structureName) || !StringUtils.hasText(phoneNumber)) {
            throw new IllegalArgumentException("Structure ou téléphone manquant.");
        }

        String pin = defaultPin;
        UserEntity user = userAuthService.createStructureUser(phoneNumber, pin, null).block();
        if (user == null || user.getUserId() == null) {
            throw new IllegalStateException("Impossible de créer le compte utilisateur.");
        }

        AccountEntity account = accountRepository.findByNameIgnoreCase(structureName).block();
        if (account == null) {
            AccountEntity toSave = new AccountEntity();
            toSave.setName(structureName);
            toSave.setIsActive(Boolean.TRUE);
            account = accountRepository.save(toSave).block();
        }
        if (account == null || account.getAccountId() == null) {
            throw new IllegalStateException("Impossible de créer le compte business.");
        }

        AccountUserEntity existingLink = accountUserRepository.findByAccountIdAndUserId(account.getAccountId(), user.getUserId()).block();
        if (existingLink == null) {
            AccountUserEntity accountUser = new AccountUserEntity();
            accountUser.setAccountId(account.getAccountId());
            accountUser.setUserId(user.getUserId());
            accountUser.setIsActive(Boolean.TRUE);
            accountUserRepository.save(accountUser).block();
        }

        StructureEntity structure = structureRepository.findByNameIgnoreCaseAndUserId(structureName, user.getUserId()).block();
        if (structure == null) {
            StructureEntity entity = new StructureEntity();
            entity.setUserId(user.getUserId());
            entity.setName(structureName);
            entity.setDescription(buildDescription(province, city));
            entity.setIsActive(Boolean.TRUE);
            entity.setLatitude(parseCoordinate(latitudeRaw));
            entity.setLongitude(parseCoordinate(longitudeRaw));
            structure = structureRepository.save(entity).block();
        }
        if (structure == null || structure.getId() == null) {
            throw new IllegalStateException("Impossible de créer la structure.");
        }

        if (StringUtils.hasText(fullAddress)) {
            AddressEntity existingAddress = addressRepository.findByFullAddressAndStructureId(fullAddress, structure.getId()).block();
            if (existingAddress == null) {
                AddressEntity address = new AddressEntity();
                address.setAvenue(city);
                address.setFullAddress(fullAddress);
                address.setStructureId(structure.getId());
                address.setIsActive(Boolean.TRUE);
                addressRepository.save(address).block();
            }
        }

        PhoneNumberEntity existingPhone = phoneNumberRepository.findByFullPhoneNumberAndStructureId(phoneNumber, structure.getId()).block();
        if (existingPhone == null) {
            PhoneNumberEntity number = new PhoneNumberEntity();
            number.setFullPhoneNumber(phoneNumber);
            number.setStructureId(structure.getId());
            number.setIsActive(Boolean.TRUE);
            phoneNumberRepository.save(number).block();
        }

        for (String serviceName : parseServices(servicesRaw)) {
            ServiceEntity service = serviceRepository.findByNameIgnoreCase(serviceName).block();
            if (service == null) {
                ServiceEntity toSave = new ServiceEntity();
                toSave.setName(serviceName);
                toSave.setIsActive(Boolean.TRUE);
                service = serviceRepository.save(toSave).block();
            }
            if (service == null || service.getId() == null) {
                continue;
            }

            StructureServiceEntity link = structureServiceRepository.findByStructureIdAndServiceId(structure.getId(), service.getId()).block();
            if (link == null) {
                StructureServiceEntity structureService = new StructureServiceEntity();
                structureService.setStructureId(structure.getId());
                structureService.setServiceId(service.getId());
                structureServiceRepository.save(structureService).block();
            }
        }
    }

    private List<Map<String, String>> readRows(Resource resource) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String headerLine = reader.readLine();
            if (!StringUtils.hasText(headerLine)) {
                return Collections.emptyList();
            }
            List<String> headers = parseCsvLine(headerLine);
            List<Map<String, String>> rows = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                if (!StringUtils.hasText(line)) {
                    continue;
                }
                List<String> values = parseCsvLine(line);
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    String header = headers.get(i);
                    String value = i < values.size() ? values.get(i).trim() : "";
                    row.put(header.trim(), value);
                }
                rows.add(row);
            }
            return rows;
        }
    }

    private List<String> parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
                continue;
            }
            if (c == ',' && !inQuotes) {
                values.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        values.add(current.toString());
        return values;
    }

    private String value(Map<String, String> row, String... keys) {
        for (String key : keys) {
            String value = row.get(key);
            if (StringUtils.hasText(value)) {
                return value.trim();
            }
        }
        return "";
    }

    private Double parseCoordinate(String raw) {
        if (!StringUtils.hasText(raw)) {
            return 0.0;
        }
        try {
            return Double.parseDouble(raw.replace(",", "."));
        } catch (NumberFormatException ignored) {
            return 0.0;
        }
    }

    private String normalizePhoneNumber(String rawPhoneNumber) {
        if (!StringUtils.hasText(rawPhoneNumber)) {
            return "";
        }
        return rawPhoneNumber.replace(" ", "").trim();
    }

    private String buildDescription(String province, String city) {
        if (!StringUtils.hasText(province) && !StringUtils.hasText(city)) {
            return "Structure importée depuis CSV";
        }
        return String.format("%s - %s", defaultString(province), defaultString(city)).trim();
    }

    private String defaultString(String value) {
        return StringUtils.hasText(value) ? value.trim() : "";
    }

    private List<String> parseServices(String rawServices) {
        if (!StringUtils.hasText(rawServices)) {
            return Collections.emptyList();
        }
        String normalized = rawServices.replace("·", ".").replace("•", ".");
        String[] split = normalized.split("\\s*\\.\\s*");
        List<String> values = new ArrayList<>();
        for (String item : split) {
            String service = item.trim();
            if (!StringUtils.hasText(service) || "Aucun service renseigné".equalsIgnoreCase(service)) {
                continue;
            }
            values.add(service);
        }
        return values;
    }
}
