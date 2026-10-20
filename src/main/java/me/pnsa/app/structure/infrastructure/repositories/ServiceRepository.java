package me.pnsa.app.structure.infrastructure.repositories;

import me.pnsa.app.structure.infrastructure.entities.ServiceEntity;
import me.pnsa.app.structure.infrastructure.entities.StructureEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends ReactiveCrudRepository<ServiceEntity, Long> {
}
