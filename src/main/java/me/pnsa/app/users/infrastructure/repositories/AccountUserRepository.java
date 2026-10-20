package me.pnsa.app.users.infrastructure.repositories;

import me.pnsa.app.users.infrastructure.entities.AccountUserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountUserRepository extends ReactiveCrudRepository<AccountUserEntity, Long> {
    Mono<AccountUserEntity> findByAccountIdAndUserId(Long accountId, Long userId);
}
