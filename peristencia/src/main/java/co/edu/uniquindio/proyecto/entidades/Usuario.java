package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {



    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> numTelefono;


    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Ciudad ciudadUsuario;

    @OneToMany(mappedBy = "chatUsuario")
    private List<Chat> chats;

    @OneToMany(mappedBy = "usuarioCompra")
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuarioComentario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuarioSubastaUsuario")
    private List<SubastaUsuario> subastaUsuarios;

    @ManyToMany(mappedBy = "usuarios")
    private List<Producto> productos;


    //@OneToMany(mappedBy = "usuarioPrestamo")
    //@ToString.Exclude
    //private List<Prestamo> prestamos;

    //public Usuario(String codigo, String nombre, LocalDate fechaNacimiento, GeneroPersona genero, String email, Map<String, String> numTelefono,Ciudad ciudadUsuario) {
        //super(codigo, nombre, fechaNacimiento, genero);

        //this.numTelefono = numTelefono;
       // this.ciudadUsuario = ciudadUsuario;
    //}




}
