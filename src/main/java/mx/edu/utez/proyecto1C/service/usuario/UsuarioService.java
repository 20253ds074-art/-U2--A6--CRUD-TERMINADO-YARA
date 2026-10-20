package mx.edu.utez.proyecto1C.service.usuario;

import jakarta.transaction.Transactional;
import mx.edu.utez.proyecto1C.controller.usuario.dto.RequestCrearUsuarioDTO;
import mx.edu.utez.proyecto1C.controller.usuario.dto.RequestUpdateUsuarioDTO;
import mx.edu.utez.proyecto1C.exception.customExceptions.BadRequestException;
import mx.edu.utez.proyecto1C.model.persona.Persona;
import mx.edu.utez.proyecto1C.model.usuario.Roles;
import mx.edu.utez.proyecto1C.model.usuario.Usuario;
import mx.edu.utez.proyecto1C.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto1C.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    // 1. Consultar información de un usuario por ID
    public Usuario getUserById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("El usuario con el ID " + id + " no existe"));
    }

    @Transactional
    public Usuario createUser(RequestCrearUsuarioDTO payload) {
        if (usuarioRepository.findByUsername(payload.getUsername()).isPresent()) {
            throw new BadRequestException("El nombre de usuario ya se encuentra registrado");
        }

        Persona persona = new Persona();
        persona.setNombre(payload.getNombres());
        persona.setPrimerApellido(payload.getPrimerApellido());
        persona.setSegundoApellido(payload.getSegundoApellido());
        persona.setFechaNacimiento(payload.getFechaNacimiento());
        persona.setCorreo(payload.getCorreo());
        persona.setCurp(payload.getCurp());
        Persona personaGuardada = personaRepository.save(persona);

        Usuario usuario = new Usuario();
        usuario.setUsername(payload.getUsername());
        usuario.setPassword(payload.getPassword());
        usuario.setEnable(true);
        usuario.setRol(Roles.valueOf(payload.getRol()));
        usuario.setDescripcion(payload.getDescripcion());
        usuario.setPersona(personaGuardada);

        return usuarioRepository.save(usuario);
    }

    // 2. Activar y desactivar usuario (por id)
    @Transactional
    public Usuario toggleUserStatus(long id, boolean enable) {
        Usuario usuario = getUserById(id);
        usuario.setEnable(enable);
        return usuarioRepository.save(usuario);
    }

    // 3. Actualizar username y descripcion sin perder relacion con Persona ni borrar otros datos
    @Transactional
    public Usuario updateUsuario(long id, RequestUpdateUsuarioDTO payload) {
        Usuario usuario = getUserById(id);

        // Validar que el username no esté en uso por otro usuario diferente
        usuarioRepository.findByUsername(payload.getUsername())
                .ifPresent(u -> {
                    if (u.getId() != id) {
                        throw new BadRequestException("El nombre de usuario ya está ocupado por otro registro");
                    }
                });

        usuario.setUsername(payload.getUsername());
        usuario.setDescripcion(payload.getDescripcion());

        // Se preserva la relación con Persona y los demás campos (password, rol, enable) intactos
        return usuarioRepository.save(usuario);
    }
}