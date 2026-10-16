package mx.edu.utez.proyecto1C.controller.usuario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateUsuarioDTO {
    private String username;
    private String descripcion;
}