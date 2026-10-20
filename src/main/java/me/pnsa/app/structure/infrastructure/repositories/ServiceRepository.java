package me.pnsa.app.structure.infrastructure.repositories;

import me.pnsa.app.structure.infrastructure.entities.ServiceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ServiceRepository extends ReactiveCrudRepository<ServiceEntity, Long> {
    Mono<ServiceEntity> findByNameIgnoreCase(String name);
}
