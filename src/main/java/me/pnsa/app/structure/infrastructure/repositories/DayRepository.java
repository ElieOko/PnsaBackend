package me.pnsa.app.structure.infrastructure.repositories;

import me.pnsa.app.structure.infrastructure.entities.DayEntity;
import me.pnsa.app.structure.infrastructure.entities.ServiceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends ReactiveCrudRepository<DayEntity, Long> {
}
