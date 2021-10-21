package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaPrestamo;

    @Column(nullable = false, length = 100)
    private String medioPago;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioCompra;

    @OneToMany(mappedBy = "compraDetalleCompra")
    private List<DetalleCompra> detalleCompras;


    public Compra(String s, LocalDate of, String tarjeta, Usuario u, List<DetalleCompra> detalleComp) {
    }
}
