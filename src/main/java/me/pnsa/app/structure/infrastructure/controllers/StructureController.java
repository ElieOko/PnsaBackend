package me.pnsa.app.structure.infrastructure.controllers;

import me.pnsa.app.structure.domain.dto.StructureCreateDTO;
import me.pnsa.app.structure.domain.models.Structure;
import me.pnsa.app.structure.infrastructure.routes.Endpoint;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@Profile({Profile.DEVELOPMENT, Profile.TEST})
@RequestMapping(Endpoint.ROOT)
public class StructureController {
//    @GetMapping(Endpoint.PUBLIC_PATH + "structure")
    public void findAll() {
//        return new Structure();
    }

    @PostMapping(value = Endpoint.PRIVATE_PATH + "structure")
    public void store(@RequestBody StructureCreateDTO structure) {

//        return new Structure();

    }

//    @PostMapping(Endpoint.PRIVATE_PATH + "structure")
    public void findAllPrivate() {}

    public void findBydId(@PathVariable Long structureId) {}

    public void update() {}

    public void destroy() {}
}
