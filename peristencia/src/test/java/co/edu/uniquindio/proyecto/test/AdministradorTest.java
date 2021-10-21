package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

@Autowired
private AdministradorRepo administradorRepo;


@Test
@Sql("classpath:administradores.sql")
public void registarTest (){
Administrador administrador = new Administrador();
administrador.setCodigo("1");
administrador.setNombre("jorge mora");
administrador.setEmail("jorge@gmail.com");
administrador.setPassword("1456df");

Administrador administradorGuardado=administradorRepo.save(administrador);
    Assertions.assertNotNull(administradorGuardado);

}

@Test
@Sql("classpath:administradores.sql")
public void eliminarAdministrador(){

  administradorRepo.deleteById("04");

  Administrador buscado =administradorRepo.findById("04").orElse(null);

   Assertions.assertNull(buscado);

}

@Test
@Sql("classpath:administradores.sql")
public  void actulizarAdministrador(){
    Administrador  guardado =administradorRepo.findById("04").orElse(null);
     //modificamos
     guardado.setNombre("carlos");
       //guardamos modificacion
     administradorRepo.save(guardado);

    Administrador buscado =administradorRepo.findById("04").orElse(null);

    Assertions.assertEquals("carlos",buscado.getNombre());
}
@Test
@Sql("classpath:administradores.sql")
public void listarAdministrador(){

    List<Administrador> administrador=administradorRepo.findAll();
    System.out.println("base de datos:\n"+administrador+"...............................");
}


}
