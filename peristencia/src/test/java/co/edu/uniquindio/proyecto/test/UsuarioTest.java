package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {


    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;



    @Test
    public void resgistrarUsuarioTest(){
 //"03","brayan@gmail.com","Brayan Gil","12345",3);
        //guardar usuario en la base de datos
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Armenia");
        ciudadRepo.save(ciudad);

        Usuario usuario=new Usuario();
        usuario.setCodigo("05");
        usuario.setEmail("brayane@gmail.com");
        usuario.setPassword("984400");
        usuario.setCiudadUsuario(ciudad);

        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        Assertions.assertNotNull(usuarioGuardado);


    }
    //Test para eliminar un usuario
    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarUsuarioTest() {
        usuarioRepo.deleteById("01");
        Usuario usuarioBorrado = usuarioRepo.findById("01").orElse(null);

        Assertions.assertNull(usuarioBorrado);
    }

    //Test para actualizar un usuario
    @Test
    @Sql("classpath:usuarios.sql")
    public void actualizarUsuarioTest() {
        Usuario usuarioGuardado = usuarioRepo.findById("1").orElse(null);
        assert usuarioGuardado != null;
        usuarioGuardado.setNombre("andres");
        usuarioRepo.save(usuarioGuardado);

        Usuario usuarioBuscado = usuarioRepo.findById("1").orElse(null);
        assert usuarioBuscado != null;
        Assertions.assertEquals("andres", usuarioBuscado.getNombre());
    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void ListarUsuarioTest(){

        List<Usuario> usuarios = usuarioRepo.findAll();

        Assertions.assertEquals(3, usuarios.size());
    usuarios.forEach(u -> System.out.println(u) );
    }


}
