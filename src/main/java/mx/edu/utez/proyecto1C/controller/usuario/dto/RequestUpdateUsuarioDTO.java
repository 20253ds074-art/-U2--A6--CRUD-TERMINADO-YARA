package mx.edu.utez.proyecto1C.controller.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateUsuarioDTO {

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 4, max = 40, message = "El username debe tener entre 4 y 40 caracteres")
    private String username;

    private String descripcion;
}