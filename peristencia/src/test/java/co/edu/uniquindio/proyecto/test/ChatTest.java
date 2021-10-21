package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

@Autowired
private ChatRepo chatRepo;

@Test
@Sql ("classpath:chats.sql")
public  void registarChatTest(){
    Chat c = new Chat();
    c.setCodigo(4);
    Producto p = new Producto();
    c.setChatProducto(p);
    Usuario u = new Usuario();
    c.setChatUsuario(u);

    Chat chatGuardado=chatRepo.save(c);
    Assertions.assertNotNull(chatGuardado);

}
    @Test
    @Sql("classpath:chats.sql")
    public void eliminarChatTest(){

        chatRepo.deleteById(1);

        Chat buscado =chatRepo.findById(1).orElse(null);

        Assertions.assertNull(buscado);

    }

    @Test
    @Sql("classpath:chats.sql")
    public  void actulizarAdministrador() {


        Chat guardado =chatRepo.findById(1).orElse(null);
        //modificamos
        guardado.setCodigo(2);
        //guardamos modificacion
        chatRepo.save(guardado);

        Chat buscado = chatRepo.findById(1).orElse(null);

        Assertions.assertEquals(1, buscado.getCodigo());

    }
    @Test
    @Sql("classpath:chats.sql")
    public void listarChatTest(){
        List<Chat> chats = chatRepo.findAll();
        chats.forEach(c -> System.out.println(c));
    }
}
