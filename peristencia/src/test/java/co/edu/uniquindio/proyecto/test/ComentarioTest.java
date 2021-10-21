package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;


    @Test
    @Sql("classpath:comentarios.sql")
    //registar comentario
    public void registrarComentarioTest() {
        Comentario  comentario = new Comentario();
        comentario.setCodigo(4);
        comentario.setCalificacion(2);
        comentario.setFechaComentario(LocalDateTime.from(LocalDate.of(2018, 10, 30)));
        comentario.setMensaje("esta disponible");


        Comentario comentarioGuardado = comentarioRepo.save(comentario);
        Assertions.assertNotNull(comentarioGuardado);
    }

    @Test
    @Sql("classpath:comentarios.sql")
    //Eliminar un comentario
    public void eliminarComentarioTest() {
        comentarioRepo.deleteById(1);
        Comentario comentarioEliminado= comentarioRepo.findById(1).orElse(null);

        Assertions.assertNull(comentarioEliminado);
    }

    @Test
    @Sql("classpath:comentarios.sql")
    //Actualizar Comentario
    public void actualizarComentarioTest() {
        Comentario comentarioGuardado = comentarioRepo.findById(1).orElse(null);
        comentarioGuardado.setCalificacion(1);
        Comentario nuevoComentario = comentarioRepo.save(comentarioGuardado);
        Assertions.assertEquals(1, nuevoComentario.getCalificacion());
    }

    @Test
    @Sql("classpath:comentarios.sql")
    //Mostrar lista de comentarios de un usuario
    public void listarComentariosTest() {
        List<Comentario> listaComentario = comentarioRepo.findAll();

        Assertions.assertEquals(3, listaComentario.size());
    }

}
