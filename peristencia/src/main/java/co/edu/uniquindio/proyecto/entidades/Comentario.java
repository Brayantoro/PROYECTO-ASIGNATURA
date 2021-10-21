package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 500)
    private String mensaje;

    @Column(nullable = false, length = 500)
    private String respuesta;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaComentario;

    @Max(5)
    @Positive
    @Column(nullable = false)
    private Integer calificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioComentario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto productoComentario;


}
