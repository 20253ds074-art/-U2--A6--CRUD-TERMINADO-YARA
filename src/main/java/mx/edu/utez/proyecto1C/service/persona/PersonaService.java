package mx.edu.utez.proyecto1C.service.persona;

import jakarta.transaction.Transactional;
import mx.edu.utez.proyecto1C.controller.persona.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1C.exception.customExceptions.BadRequestException;
import mx.edu.utez.proyecto1C.model.persona.Persona;
import mx.edu.utez.proyecto1C.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> getAll() {
        return personaRepository.findAll();
    }

    public Persona getById(long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("La persona con el ID especificado no existe"));
    }

    @Transactional
    public Persona updatePersona(long id, RequestUpdatePersonaDTO payload) {
        Persona persona = getById(id);

        persona.setNombre(payload.getNombre());
        persona.setPrimerApellido(payload.getPrimerApellido());
        persona.setSegundoApellido(payload.getSegundoApellido());
        persona.setFechaNacimiento(payload.getFechaNacimiento());
        persona.setCorreo(payload.getCorreo());
        persona.setCurp(payload.getCurp());

        return personaRepository.save(persona);
    }
}