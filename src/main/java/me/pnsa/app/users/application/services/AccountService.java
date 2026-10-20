package me.pnsa.app.users.application.services;

import me.pnsa.app.users.infrastructure.entities.AccountEntity;
import me.pnsa.app.users.infrastructure.repositories.AccountRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    private final TransactionalOperator transactionalOperator;
    private AccountRepository accountRepository;

    public AccountService(TransactionalOperator transactionalOperator) {
        this.transactionalOperator = transactionalOperator;
    }

    public Mono<AccountEntity> findByAccount(Long accountId) {
        return accountRepository.findById(accountId).switchIfEmpty(Mono.error(new ResponseStatusException(
                HttpStatusCode.valueOf(404), "Account not found"))).flatMap(account -> accountRepository.findById(accountId))
                .as(transactionalOperator::transactional);
    }

    public Flux<AccountEntity> findAllAccount() {
        return accountRepository.findAll().switchIfEmpty(Mono.error(
                new ResponseStatusException(HttpStatusCode.valueOf(404), "Account not found")
        )).flatMap(account -> accountRepository.findAll())
                .as(transactionalOperator::transactional);
    }

}
