package mx.edu.utez.proyecto1C.controller.persona;

import mx.edu.utez.proyecto1C.controller.persona.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1C.model.persona.Persona;
import mx.edu.utez.proyecto1C.service.persona.PersonaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<Persona> getAll() {
        return personaService.getAll();
    }

    @GetMapping("/{id}")
    public Persona getById(@PathVariable long id) {
        return personaService.getById(id);
    }

    @PutMapping("/{id}")
    public Persona update(@PathVariable long id, @RequestBody RequestUpdatePersonaDTO payload) {
        return personaService.updatePersona(id, payload);
    }
}