package me.pnsa.app.structure.application.services;

import me.pnsa.app.structure.infrastructure.entities.StructureEntity;
import me.pnsa.app.structure.infrastructure.repositories.StructureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructureService {
    protected StructureRepository repository;

    public List<StructureEntity> findAll() {
        return repository.findAllByIsActive().toStream().toList();
    }

    public StructureEntity findByID(Long id) {
        Object data = repository.findById(id);
        return repository.findById(id).block();
    }
}
