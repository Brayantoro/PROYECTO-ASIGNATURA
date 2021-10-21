package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@AllArgsConstructor
@ToString
public class Persona implements Serializable {

    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;

    public Persona(String codigo) {
    }

    // @Column(nullable = false)
     //private LocalDate fechaNacimiento;

    //@Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    //private GeneroPersona genero;


}
