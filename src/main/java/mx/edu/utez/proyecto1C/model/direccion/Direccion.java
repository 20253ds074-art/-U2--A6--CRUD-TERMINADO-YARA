package mx.edu.utez.proyecto1C.model.direccion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1C.model.persona.Persona;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String calle;
    private String colonia;
    private String municipio;
    private String estado;
    private int numero;
    private String codigoPostal;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
}
