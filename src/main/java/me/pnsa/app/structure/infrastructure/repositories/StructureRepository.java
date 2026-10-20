package me.pnsa.app.structure.infrastructure.repositories;

import me.pnsa.app.structure.infrastructure.entities.StructureEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StructureRepository extends ReactiveCrudRepository<StructureEntity, Long> {
    @Query("SELECT * FROM structures WHERE is_active = true ORDER BY created_at DESC")
    Flux<StructureEntity> findAllByIsActive();
}
