package me.pnsa.app.structure.infrastructure.repositories;

import me.pnsa.app.structure.infrastructure.entities.AgendaEntity;
import me.pnsa.app.structure.infrastructure.entities.StructureTypeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureTypeRepository extends ReactiveCrudRepository<StructureTypeEntity, Long> {
}
