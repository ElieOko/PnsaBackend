package me.pnsa.app.users.application.services;

import me.pnsa.app.users.infrastructure.entities.AccountEntity;
import me.pnsa.app.users.infrastructure.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Mono<AccountEntity> findByAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "Compte introuvable.")));
    }

    public Flux<AccountEntity> findAllAccount() {
        return accountRepository.findAll()
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "Aucun compte trouvé.")));
    }

}
