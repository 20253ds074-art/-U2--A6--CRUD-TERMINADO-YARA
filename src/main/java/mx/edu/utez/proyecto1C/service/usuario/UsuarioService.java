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
    //SERVICIOS


    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();

    }

    public Usuario createUser(RequestCrearUsuarioDTO payload){
        //Crear instancia de usuario y lenarlla en base a la info de payload

        if (usuarioRepository.findByUsername(payload.getUsername()).isPresent()){
            throw new BadRequestException("Este usuario ya esta en uso");
        }
        //registrar persona
        Persona newPersona= new Persona();
        newPersona.setNombre(payload.getNombres());
        newPersona.setPrimerApellido(payload.getPrimerApellido());
        newPersona.setSegundoApellido(payload.getSegundoApellido());
        newPersona.setFechaNacimiento(payload.getFechaNacimiento());
        newPersona.setCorreo(payload.getCorreo());
        newPersona.setCurp(payload.getCurp());

        //creando registro de persona y obtener sus datos como registro existente como el id
        newPersona=personaRepository.save(newPersona);



        //Crear instancia de usuario
        Usuario newUser = new Usuario();
        newUser.setUsername(payload.getUsername());
        newUser.setPassword(payload.getPassword());
        newUser.setDescripcion(payload.getDescripcion());
        newUser.setEnable(true);
        newUser.setRol(
                Roles.valueOf(payload.getRol())
        );

        newUser.setPersona(newPersona);

        return usuarioRepository.save(newUser);
    }

    // Consultar usuario por ID
    public Usuario getUserById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("El usuario con el ID proporcionado no existe"));
    }

    @Transactional
    public Usuario toggleUserStatus(long id, boolean isEnable) {
        Usuario usuario = getUserById(id);
        usuario.setEnable(isEnable);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario updateUsuario(long id, RequestUpdateUsuarioDTO payload) {
        Usuario usuario = getUserById(id);

        if (!usuario.getUsername().equals(payload.getUsername()) &&
                usuarioRepository.findByUsername(payload.getUsername()).isPresent()) {
            throw new BadRequestException("El username ya se encuentra en uso");
        }

        usuario.setUsername(payload.getUsername());
        usuario.setDescripcion(payload.getDescripcion());


        return usuarioRepository.save(usuario);
    }

}
