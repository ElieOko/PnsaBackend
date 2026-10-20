package me.pnsa.app.users.infrastructure.repositories;

import me.pnsa.app.users.infrastructure.entities.AccountUserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserRepository extends ReactiveCrudRepository<AccountUserEntity, Long> {
}
