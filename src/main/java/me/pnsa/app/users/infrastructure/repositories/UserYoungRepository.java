package me.pnsa.app.users.infrastructure.repositories;

import me.pnsa.app.users.infrastructure.entities.UserYoungEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserYoungRepository extends ReactiveCrudRepository<UserYoungEntity, Long> {
}
