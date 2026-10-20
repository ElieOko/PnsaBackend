package me.pnsa.app.users.infrastructure.repositories;

import me.pnsa.app.users.infrastructure.entities.AccountEntity;
import me.pnsa.app.users.infrastructure.entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
}
