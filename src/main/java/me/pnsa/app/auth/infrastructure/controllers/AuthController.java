package me.pnsa.app.auth.infrastructure.controllers;

import jakarta.validation.Valid;
import me.pnsa.app.auth.application.services.UserAuthService;
import me.pnsa.app.auth.application.services.YoungAuthService;
import me.pnsa.app.auth.domain.dto.ChangePinRequest;
import me.pnsa.app.auth.domain.dto.UserLoginRequest;
import me.pnsa.app.auth.domain.dto.YoungLoginRequest;
import me.pnsa.app.auth.domain.dto.YoungRegisterRequest;
import me.pnsa.app.structure.infrastructure.routes.Endpoint;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Endpoint.ROOT)
public class AuthController {
    private final YoungAuthService youngAuthService;
    private final UserAuthService userAuthService;

    public AuthController(YoungAuthService youngAuthService, UserAuthService userAuthService) {
        this.youngAuthService = youngAuthService;
        this.userAuthService = userAuthService;
    }

    @PostMapping(Endpoint.PUBLIC_PATH + "auth/young/register")
    public Mono<?> registerYoung(@Valid @RequestBody YoungRegisterRequest request) {
        return youngAuthService.register(request);
    }

    @PostMapping(Endpoint.PUBLIC_PATH + "auth/young/login")
    public Mono<?> loginYoung(@Valid @RequestBody YoungLoginRequest request) {
        return youngAuthService.login(request);
    }

    @PutMapping(Endpoint.PROTECTED_PATH + "auth/young/pin")
    public Mono<?> changeYoungPin(@Valid @RequestBody ChangePinRequest request) {
        return youngAuthService.changePin(request);
    }

    @PostMapping(Endpoint.PUBLIC_PATH + "auth/user/login")
    public Mono<?> loginUser(@Valid @RequestBody UserLoginRequest request) {
        return userAuthService.login(request);
    }

    @PutMapping(Endpoint.PROTECTED_PATH + "auth/user/pin")
    public Mono<?> changeUserPin(@Valid @RequestBody ChangePinRequest request) {
        return userAuthService.changePin(request);
    }
}
