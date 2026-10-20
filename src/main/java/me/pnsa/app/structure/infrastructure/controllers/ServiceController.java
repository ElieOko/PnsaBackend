package me.pnsa.app.structure.infrastructure.controllers;

import me.pnsa.app.structure.domain.dto.ServiceDTO;
import me.pnsa.app.structure.domain.dto.StructureCreateDTO;
import me.pnsa.app.structure.infrastructure.routes.Endpoint;
import org.springframework.web.bind.annotation.*;

@RestController
//@Profile({Profile.DEVELOPMENT, Profile.TEST})
@RequestMapping(Endpoint.ROOT)
public class ServiceController {
//    @GetMapping(Endpoint.PUBLIC_PATH + "structure")
    public void findAll() {
//        return new Structure();
    }

    @PostMapping(value = Endpoint.PRIVATE_PATH + "services")
    public void store(@RequestBody ServiceDTO service) {

//        return new Structure();

    }

//    @PostMapping(Endpoint.PRIVATE_PATH + "structure")
    public void findAllPrivate() {}

    public void findBydId(@PathVariable Long serviceId) {}

    public void update() {}

    public void destroy() {}
}
