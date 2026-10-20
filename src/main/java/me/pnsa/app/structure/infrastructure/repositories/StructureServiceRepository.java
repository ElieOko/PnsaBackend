package me.pnsa.app.structure.infrastructure.repositories;

import me.pnsa.app.structure.infrastructure.entities.StructureEntity;
import me.pnsa.app.structure.infrastructure.entities.StructureServiceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureServiceRepository extends ReactiveCrudRepository<StructureServiceEntity, Long> {
}
