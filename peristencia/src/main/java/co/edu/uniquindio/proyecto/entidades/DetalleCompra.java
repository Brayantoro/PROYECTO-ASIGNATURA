package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DetalleCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Integer precioProducto;

    @Positive
    @Column(nullable = false)
    private Integer unidades;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra compraDetalleCompra;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto productoDetalleCompra;



    public DetalleCompra(Integer codigo,Integer precioProducto, Integer unidades, Compra compraDetalleCompra, Producto productoDetalleCompra) {

    }
}
