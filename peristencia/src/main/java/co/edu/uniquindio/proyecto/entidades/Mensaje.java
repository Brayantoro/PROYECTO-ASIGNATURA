package co.edu.uniquindio.proyecto.entidades;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String emisor;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaMensaje;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Chat chatMensaje;


    public Mensaje(String s, String mensaje_emisor, LocalDate of, Integer codigo) {
    }
}
