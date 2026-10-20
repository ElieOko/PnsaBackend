package me.pnsa.app.address.infrastructure.repositories;

import me.pnsa.app.address.infrastructure.entities.EmailEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends ReactiveCrudRepository<EmailEntity, Long> {
}
