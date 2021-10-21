package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false, length = 250)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    @Future
    private LocalDate fechaLimite;

    @Positive
    @Column(nullable = false)
    private Integer descuento;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> ruta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "chatProducto")
    private List<Chat> chats;

    @ManyToMany
    @JoinColumn(nullable = false)
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "productoDetalleCompra")
    private List<DetalleCompra> detalleCompras;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudadProducto;

    @OneToMany(mappedBy = "productoComentario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "productoSubasta")
    private List<Subasta> subastas;
}
