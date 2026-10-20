package me.pnsa.app.auth.application.services;

import me.pnsa.app.auth.domain.dto.AuthResponse;
import me.pnsa.app.auth.domain.dto.ChangePinRequest;
import me.pnsa.app.auth.domain.dto.UserLoginRequest;
import me.pnsa.app.users.infrastructure.entities.UserEntity;
import me.pnsa.app.users.infrastructure.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@Service
public class UserAuthService {
    private static final Pattern PIN_PATTERN = Pattern.compile("^\\d{4}$");
    private final UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<AuthResponse> login(UserLoginRequest request) {
        String phoneNumber = normalizePhoneNumber(request.phoneNumber());
        validatePhoneNumber(phoneNumber);
        validatePin(request.pin());

        return userRepository.findByPhoneNumber(phoneNumber)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable.")))
                .flatMap(user -> {
                    if (!Boolean.TRUE.equals(user.getIsActive()) || Boolean.TRUE.equals(user.getIsLock())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN, "Compte verrouillé ou inactif."));
                    }
                    if (!request.pin().equals(user.getPassword())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "PIN invalide."));
                    }
                    return Mono.just(new AuthResponse(
                            user.getUserId(),
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

        return userRepository.findByPhoneNumber(phoneNumber)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable.")))
                .flatMap(user -> {
                    if (!request.oldPin().equals(user.getPassword())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ancien PIN invalide."));
                    }
                    user.setPassword(request.newPin());
                    user.setPasswordVisible(request.newPin());
                    user.setMustChangePassword(Boolean.FALSE);
                    return userRepository.save(user).map(saved -> new AuthResponse(
                            saved.getUserId(),
                            saved.getPhoneNumber(),
                            saved.getMustChangePassword(),
                            "PIN modifié avec succès."
                    ));
                });
    }

    public Mono<UserEntity> createStructureUser(String phoneNumber, String pin, String email) {
        validatePhoneNumber(phoneNumber);
        validatePin(pin);
        String normalizedPhone = normalizePhoneNumber(phoneNumber);
        return userRepository.findByPhoneNumber(normalizedPhone)
                .switchIfEmpty(Mono.defer(() -> {
                    UserEntity user = new UserEntity();
                    user.setPhoneNumber(normalizedPhone);
                    user.setEmail(email);
                    user.setPassword(pin);
                    user.setPasswordVisible(pin);
                    user.setIsLock(Boolean.FALSE);
                    user.setIsActive(Boolean.TRUE);
                    user.setMustChangePassword(Boolean.TRUE);
                    return userRepository.save(user);
                }));
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
