package mx.edu.utez.proyecto1C.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.usuario.dto.RequestCrearUsuarioDTO;
import mx.edu.utez.proyecto1C.controller.usuario.dto.RequestUpdateUsuarioDTO;
import mx.edu.utez.proyecto1C.model.usuario.Usuario;
import mx.edu.utez.proyecto1C.service.usuario.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable long id) {
        return usuarioService.getUserById(id);
    }

    @PostMapping
    public Usuario create(@RequestBody @Valid RequestCrearUsuarioDTO payload) {
        return usuarioService.createUser(payload);
    }

    @PatchMapping("/{id}/status")
    public Usuario toggleStatus(@PathVariable long id, @RequestParam boolean enable) {
        return usuarioService.toggleUserStatus(id, enable);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable long id, @RequestBody @Valid RequestUpdateUsuarioDTO payload) {
        return usuarioService.updateUsuario(id, payload);
    }
}