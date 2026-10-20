package me.pnsa.app.users.infrastructure.controllers;

import me.pnsa.app.structure.infrastructure.routes.Endpoint;
import me.pnsa.app.users.application.services.AccountService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Endpoint.ROOT)
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(Endpoint.PRIVATE_PATH + "accounts")
    public Flux<?> allAccounts() {
        return accountService.findAllAccount();
    }

    @GetMapping(Endpoint.PRIVATE_PATH + "accounts/{accountId}")
    public Mono<?> accountById(@PathVariable Long accountId) {
        return accountService.findByAccount(accountId);
    }
}
