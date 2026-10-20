package me.pnsa.app.users.infrastructure.repositories;

import me.pnsa.app.users.infrastructure.entities.UserYoungEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserYoungRepository extends ReactiveCrudRepository<UserYoungEntity, Long> {
    Mono<UserYoungEntity> findByPhoneNumber(String phoneNumber);
}
