package me.pnsa.app.address.infrastructure.repositories;

import me.pnsa.app.address.infrastructure.entities.AddressEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<AddressEntity, Long> {
    Mono<AddressEntity> findByFullAddressAndStructureId(String fullAddress, Long structureId);
}
