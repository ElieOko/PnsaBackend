package me.pnsa.app.structure.infrastructure.controllers;

import me.pnsa.app.structure.application.services.StructureService;
import me.pnsa.app.structure.domain.dto.StructureCreateDTO;
import me.pnsa.app.structure.infrastructure.routes.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Endpoint.ROOT)
public class StructureController {
    private final StructureService structureService;

    public StructureController(StructureService structureService) {
        this.structureService = structureService;
    }

    @GetMapping(Endpoint.PUBLIC_PATH + "structure")
    public Flux<?> findAll() {
        return structureService.findAll();
    }

    @GetMapping(Endpoint.PUBLIC_PATH + "structure/{structureId}")
    public Mono<?> findById(@PathVariable Long structureId) {
        return structureService.findByID(structureId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(Endpoint.PRIVATE_PATH + "structure")
    public Mono<?> store(@RequestBody StructureCreateDTO structure) {
        return structureService.create(structure);
    }

    @PutMapping(Endpoint.PRIVATE_PATH + "structure/{structureId}")
    public Mono<?> update(@PathVariable Long structureId, @RequestBody StructureCreateDTO structure) {
        return structureService.update(structureId, structure);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(Endpoint.PRIVATE_PATH + "structure/{structureId}")
    public Mono<Void> destroy(@PathVariable Long structureId) {
        return structureService.destroy(structureId);
    }
}
