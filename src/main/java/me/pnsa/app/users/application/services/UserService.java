package me.pnsa.app.users.application.services;

import me.pnsa.app.users.infrastructure.entities.UserEntity;
import me.pnsa.app.users.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class UserService {
    private static final Pattern PIN_PATTERN = Pattern.compile("^\\d{4}$");
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserEntity> createUser(UserEntity user){
        validatePhoneNumber(user.getPhoneNumber());
        validatePin(user.getPassword());
        user.setPasswordVisible(user.getPassword());
        user.setIsActive(Boolean.TRUE);
        user.setIsLock(Boolean.FALSE);
        if (user.getMustChangePassword() == null) {
            user.setMustChangePassword(Boolean.TRUE);
        }
        return userRepository.findByPhoneNumber(normalizePhoneNumber(user.getPhoneNumber()))
                .flatMap(existing -> Mono.<UserEntity>error(new ResponseStatusException(
                        CONFLICT, "Un utilisateur existe déjà avec ce numéro de téléphone.")))
                .switchIfEmpty(Mono.defer(() -> {
                    user.setPhoneNumber(normalizePhoneNumber(user.getPhoneNumber()));
                    return userRepository.save(user);
                }));
    }

    public Mono<UserEntity> updatePinByPhoneNumber(String phoneNumber, String oldPin, String newPin) {
        validatePhoneNumber(phoneNumber);
        validatePin(oldPin);
        validatePin(newPin);
        return userRepository.findByPhoneNumber(normalizePhoneNumber(phoneNumber))
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "Utilisateur introuvable.")))
                .flatMap(user -> {
                    if (!oldPin.equals(user.getPassword())) {
                        return Mono.error(new ResponseStatusException(UNAUTHORIZED, "Ancien PIN invalide."));
                    }
                    user.setPassword(newPin);
                    user.setPasswordVisible(newPin);
                    user.setMustChangePassword(Boolean.FALSE);
                    return userRepository.save(user);
                });
    }

    public Mono<UserEntity> getUserById(Long id){
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "Utilisateur introuvable.")));
    }

    public Flux<UserEntity> getAllUsers(){
        return userRepository.findAll()
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "Aucun utilisateur trouvé.")));
    }

    private String normalizePhoneNumber(String phoneNumber) {
        return phoneNumber == null ? null : phoneNumber.replace(" ", "").trim();
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!StringUtils.hasText(phoneNumber)) {
            throw new ResponseStatusException(BAD_REQUEST, "Le numéro de téléphone est obligatoire.");
        }
    }

    private void validatePin(String pin) {
        if (!StringUtils.hasText(pin) || !PIN_PATTERN.matcher(pin).matches()) {
            throw new ResponseStatusException(BAD_REQUEST, "Le PIN doit contenir exactement 4 chiffres.");
        }
    }
}
