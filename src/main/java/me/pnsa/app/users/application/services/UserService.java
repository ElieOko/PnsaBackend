package me.pnsa.app.users.application.services;

import me.pnsa.app.users.infrastructure.entities.UserEntity;
import me.pnsa.app.users.infrastructure.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.parser.Entity;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
public class UserService {
    private final TransactionalOperator transactionalOperator;
    private UserRepository userRepository;

    public UserService(TransactionalOperator transactionalOperator) {
        this.transactionalOperator = transactionalOperator;
    }

    void createUser(UserEntity user){
        userRepository.save(user);
    }

    void updateUser(){

    }

    void deleteUser(){

    }

    void deleteUserById(Long id){}

    public Mono<UserEntity> getUserById(Long id){
      return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"User not found")))
                .flatMap(user -> userRepository.findById(id))
                .as(transactionalOperator::transactional);
    }

    public Flux<UserEntity> getAllUsers(){
        return userRepository.findAll()
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")))
                .flatMap(user -> userRepository.findAll())
                .as(transactionalOperator::transactional);

    }
}
