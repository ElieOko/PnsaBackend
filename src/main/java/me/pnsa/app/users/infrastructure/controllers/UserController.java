package me.pnsa.app.users.infrastructure.controllers;

import me.pnsa.app.structure.infrastructure.routes.Endpoint;
import me.pnsa.app.users.application.services.UserService;
import me.pnsa.app.users.domain.dto.CreateUserRequest;
import me.pnsa.app.users.infrastructure.entities.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Endpoint.ROOT)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(Endpoint.PRIVATE_PATH + "users")
    public Flux<?> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(Endpoint.PRIVATE_PATH + "users/{userId}")
    public Mono<?> userById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(Endpoint.PRIVATE_PATH + "users")
    public Mono<?> createUser(@RequestBody CreateUserRequest request) {
        UserEntity user = new UserEntity();
        user.setPhoneNumber(request.phoneNumber());
        user.setEmail(request.email());
        user.setPassword(request.pin());
        return userService.createUser(user);
    }
}
