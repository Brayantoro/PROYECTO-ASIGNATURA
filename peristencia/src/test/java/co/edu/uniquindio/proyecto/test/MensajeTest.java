package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;

    private ChatRepo chatRepo;
    @Test
    //registrar un mensaje
    public void registrarMensajeTest() {
        Chat chat = new Chat();
        chatRepo.save(chat);
        Mensaje mensaje = new Mensaje("1", "mensaje emisor", LocalDate.of(2018, 10, 30),chat.getCodigo());
        Mensaje mensajeGuardado = mensajeRepo.save(mensaje);
        Assertions.assertNotNull(mensajeGuardado);

    }

    @Test
    @Sql("classpath:mensajes.sql")
    //Elinar un mensaje
    public void eliminarMensajeTest() {

        mensajeRepo.deleteById(1);

        Mensaje mensajeBuscado = mensajeRepo.findById(1).orElse(null);

        Assertions.assertNull(mensajeBuscado);

    }

    @Test
    @Sql("classpath:mensajes.sql")
    //Actualizar un mensaje
    public void actualizarMensajeTest() {
           Chat chatMensaje = new Chat();
        List<Mensaje> mensaje= mensajeRepo.findAll();

        for (int i=0; i<mensaje.size(); i++){
            Mensaje aux;
            if (i==mensaje.size()){
                aux= mensaje.get(i);

            }
        }

        Mensaje mensajeGuardado = mensajeRepo.findById(1).orElse(null);

        mensajeRepo.save(mensajeGuardado);

        Mensaje mensajeBuscado = mensajeRepo.findById(1).orElse(null);
        Assertions.assertEquals("Mensaje nuevo", mensajeBuscado.getChatMensaje());

    }

    @Test
    @Sql("classpath:mensajes.sql")
    //Mostrar lista de mensajes de un usuario
    public void listarMensajeTest() {

        List<Mensaje> mensajes = mensajeRepo.findAll();
        mensajes.forEach(u -> System.out.println(u));

    }
}
