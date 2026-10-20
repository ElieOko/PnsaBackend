package me.pnsa.app.address.infrastructure.repositories;

import me.pnsa.app.address.infrastructure.entities.PhoneNumberEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends ReactiveCrudRepository<PhoneNumberEntity, Long> {
}
