package mx.edu.utez.proyecto1C.controller.persona.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class RequestUpdatePersonaDTO {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private String correo;
    private String curp;
}