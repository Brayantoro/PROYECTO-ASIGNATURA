package co.edu.uniquindio.proyecto.entidades;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable {


    //@Max(9999)
    //@Positive
   // @Column(nullable = false)
    //private Integer anioNacimiento;

    //@ManyToMany(mappedBy = "autores")
    //private List<Libro> libros;


    public Administrador(String codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}
