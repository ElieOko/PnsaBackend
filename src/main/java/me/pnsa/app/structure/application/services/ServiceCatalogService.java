package me.pnsa.app.structure.application.services;

import me.pnsa.app.structure.domain.dto.ServiceDTO;
import me.pnsa.app.structure.infrastructure.entities.ServiceEntity;
import me.pnsa.app.structure.infrastructure.repositories.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceCatalogService {
    private final ServiceRepository serviceRepository;

    public ServiceCatalogService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Flux<ServiceEntity> findAll() {
        return serviceRepository.findAll();
    }

    public Mono<ServiceEntity> findById(Long id) {
        return serviceRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Service introuvable.")));
    }

    public Mono<ServiceEntity> create(ServiceDTO dto) {
        ServiceEntity entity = new ServiceEntity();
        entity.setName(dto.name());
        entity.setIsActive(dto.isActive() == null ? Boolean.TRUE : dto.isActive());
        return serviceRepository.save(entity);
    }

    public Mono<ServiceEntity> update(Long id, ServiceDTO dto) {
        return findById(id).flatMap(entity -> {
            entity.setName(dto.name());
            entity.setIsActive(dto.isActive() == null ? entity.getIsActive() : dto.isActive());
            return serviceRepository.save(entity);
        });
    }

    public Mono<Void> delete(Long id) {
        return serviceRepository.deleteById(id);
    }
}
