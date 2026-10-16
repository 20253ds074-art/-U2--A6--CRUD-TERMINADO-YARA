package mx.edu.utez.proyecto1C.model.persona;

import jakarta.persistence.*;
import lombok.*;
import mx.edu.utez.proyecto1C.model.cursos.Curso;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "personas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private String correo;
    private String curp;

    /*@ManyToMany
    @JoinTable(
            name = "personas_cursos",
            joinColumns= @JoinColumn(name = "persona_id"),
            inverseJoinColumns=@JoinColumn(name = "cursos_id")
    )

    private List<Curso> cursos;*/




}
