package me.pnsa.app.structure.application.services;

import me.pnsa.app.structure.domain.dto.StructureCreateDTO;
import me.pnsa.app.structure.infrastructure.entities.StructureEntity;
import me.pnsa.app.structure.infrastructure.repositories.StructureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StructureService {
    private final StructureRepository repository;

    public StructureService(StructureRepository repository) {
        this.repository = repository;
    }

    public Flux<StructureEntity> findAll() {
        return repository.findAllByIsActive();
    }

    public Mono<StructureEntity> findByID(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Structure introuvable.")));
    }

    public Mono<StructureEntity> create(StructureCreateDTO dto) {
        StructureEntity entity = new StructureEntity();
        entity.setUserId(dto.userId());
        entity.setStructureTypeId(dto.structureTypeId());
        entity.setProvinceId(dto.provinceId());
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setLatitude(dto.latitude());
        entity.setLongitude(dto.longitude());
        entity.setIsActive(Boolean.TRUE);
        return repository.save(entity);
    }

    public Mono<StructureEntity> update(Long id, StructureCreateDTO dto) {
        return findByID(id).flatMap(entity -> {
            entity.setUserId(dto.userId());
            entity.setStructureTypeId(dto.structureTypeId());
            entity.setProvinceId(dto.provinceId());
            entity.setName(dto.name());
            entity.setDescription(dto.description());
            entity.setLatitude(dto.latitude());
            entity.setLongitude(dto.longitude());
            entity.setIsActive(Boolean.TRUE);
            return repository.save(entity);
        });
    }

    public Mono<Void> destroy(Long id) {
        return findByID(id).flatMap(entity -> {
            entity.setIsActive(Boolean.FALSE);
            return repository.save(entity).then();
        });
    }
}
