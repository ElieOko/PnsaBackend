package me.pnsa.app.structure.infrastructure.controllers;

import me.pnsa.app.structure.application.services.ServiceCatalogService;
import me.pnsa.app.structure.domain.dto.ServiceDTO;
import me.pnsa.app.structure.infrastructure.routes.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Endpoint.ROOT)
public class ServiceController {
    private final ServiceCatalogService serviceCatalogService;

    public ServiceController(ServiceCatalogService serviceCatalogService) {
        this.serviceCatalogService = serviceCatalogService;
    }

    @GetMapping(Endpoint.PUBLIC_PATH + "services")
    public Flux<?> findAll() {
        return serviceCatalogService.findAll();
    }

    @GetMapping(Endpoint.PUBLIC_PATH + "services/{serviceId}")
    public Mono<?> findById(@PathVariable Long serviceId) {
        return serviceCatalogService.findById(serviceId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(Endpoint.PRIVATE_PATH + "services")
    public Mono<?> store(@RequestBody ServiceDTO service) {
        return serviceCatalogService.create(service);
    }

    @PutMapping(Endpoint.PRIVATE_PATH + "services/{serviceId}")
    public Mono<?> update(@PathVariable Long serviceId, @RequestBody ServiceDTO service) {
        return serviceCatalogService.update(serviceId, service);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(Endpoint.PRIVATE_PATH + "services/{serviceId}")
    public Mono<Void> destroy(@PathVariable Long serviceId) {
        return serviceCatalogService.delete(serviceId);
    }
}
