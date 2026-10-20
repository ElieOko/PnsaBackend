package me.pnsa.app.users.infrastructure.repositories;

import me.pnsa.app.users.infrastructure.entities.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, Long> {
}
