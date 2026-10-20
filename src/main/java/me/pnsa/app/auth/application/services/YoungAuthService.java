package me.pnsa.app.auth.application.services;

import me.pnsa.app.auth.domain.dto.AuthResponse;
import me.pnsa.app.auth.domain.dto.ChangePinRequest;
import me.pnsa.app.auth.domain.dto.YoungLoginRequest;
import me.pnsa.app.auth.domain.dto.YoungRegisterRequest;
import me.pnsa.app.users.infrastructure.entities.UserYoungEntity;
import me.pnsa.app.users.infrastructure.repositories.UserYoungRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@Service
public class YoungAuthService {
    private static final Pattern PIN_PATTERN = Pattern.compile("^\\d{4}$");
    private final UserYoungRepository userYoungRepository;

    public YoungAuthService(UserYoungRepository userYoungRepository) {
        this.userYoungRepository = userYoungRepository;
    }

    public Mono<AuthResponse> register(YoungRegisterRequest request) {
        String phoneNumber = normalizePhoneNumber(request.phoneNumber());
        validatePhoneNumber(phoneNumber);
        validatePin(request.pin());

        return userYoungRepository.findByPhoneNumber(phoneNumber)
                .flatMap(existing -> Mono.<AuthResponse>error(new ResponseStatusException(
                        HttpStatus.CONFLICT, "Ce numéro est déjà utilisé par un compte jeune."
                )))
                .switchIfEmpty(Mono.defer(() -> {
                    UserYoungEntity entity = new UserYoungEntity();
                    entity.setPhoneNumber(phoneNumber);
                    entity.setPassword(request.pin());
                    entity.setIsActive(Boolean.TRUE);
                    entity.setIsLock(Boolean.FALSE);
                    entity.setMustChangePassword(Boolean.TRUE);
                    return userYoungRepository.save(entity)
                            .map(saved -> new AuthResponse(
                                    saved.getId(),
                                    saved.getPhoneNumber(),
                                    saved.getMustChangePassword(),
                                    "Compte jeune créé avec succès."
                            ));
                }));
    }

    public Mono<AuthResponse> login(YoungLoginRequest request) {
        String phoneNumber = normalizePhoneNumber(request.phoneNumber());
        validatePhoneNumber(phoneNumber);
        validatePin(request.pin());

        return userYoungRepository.findByPhoneNumber(phoneNumber)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte jeune introuvable.")))
                .flatMap(user -> {
                    if (!Boolean.TRUE.equals(user.getIsActive()) || Boolean.TRUE.equals(user.getIsLock())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN, "Compte jeune verrouillé ou inactif."));
                    }
                    if (!request.pin().equals(user.getPassword())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "PIN invalide."));
                    }
                    return Mono.just(new AuthResponse(
                            user.getId(),
                            user.getPhoneNumber(),
                            user.getMustChangePassword(),
                            "Connexion réussie."
                    ));
                });
    }

    public Mono<AuthResponse> changePin(ChangePinRequest request) {
        String phoneNumber = normalizePhoneNumber(request.phoneNumber());
        validatePhoneNumber(phoneNumber);
        validatePin(request.oldPin());
        validatePin(request.newPin());

        return userYoungRepository.findByPhoneNumber(phoneNumber)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte jeune introuvable.")))
                .flatMap(user -> {
                    if (!request.oldPin().equals(user.getPassword())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ancien PIN invalide."));
                    }
                    user.setPassword(request.newPin());
                    user.setMustChangePassword(Boolean.FALSE);
                    return userYoungRepository.save(user)
                            .map(saved -> new AuthResponse(
                                    saved.getId(),
                                    saved.getPhoneNumber(),
                                    saved.getMustChangePassword(),
                                    "PIN modifié avec succès."
                            ));
                });
    }

    private String normalizePhoneNumber(String rawPhoneNumber) {
        return rawPhoneNumber == null ? null : rawPhoneNumber.replace(" ", "").trim();
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!StringUtils.hasText(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le numéro de téléphone est obligatoire.");
        }
    }

    private void validatePin(String pin) {
        if (!StringUtils.hasText(pin) || !PIN_PATTERN.matcher(pin).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le PIN doit contenir exactement 4 chiffres.");
        }
    }
}
