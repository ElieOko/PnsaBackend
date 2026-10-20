package me.pnsa.app.structure.infrastructure.repositories;

import me.pnsa.app.structure.infrastructure.entities.StructureServiceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StructureServiceRepository extends ReactiveCrudRepository<StructureServiceEntity, Long> {
    Mono<StructureServiceEntity> findByStructureIdAndServiceId(Long structureId, Long serviceId);
}
