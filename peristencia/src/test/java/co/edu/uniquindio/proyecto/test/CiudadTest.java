package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    //Registrar una ciudad
    public void registrarCiudadTest() {
        Ciudad  c = new Ciudad();
        c.setCodigo(0);
        c.setNombre("Armenia");
        Ciudad ciudadGuardada = ciudadRepo.save(c);
        Assertions.assertNotNull(ciudadGuardada);
    }


    @Test
    @Sql("classpath:ciudades.sql")
    //Eliminar una ciudad
    public void eliminarCiudadTest() {
        Ciudad ciudadEliminar = ciudadRepo.findById(1).orElse(null);
        ciudadRepo.deleteById(1);
        Assertions.assertNull(ciudadEliminar);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Actualizar una ciudad
    public void actualizarCiudadTest() {
        Ciudad ciudadGuardada = ciudadRepo.findById(1).orElse(null);
         ciudadGuardada.setNombre("cali");
        Ciudad  ciudadBusacada = ciudadRepo.save(ciudadGuardada);
        Assertions.assertEquals("Pereira", ciudadBusacada.getNombre());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Mostrar lista de ciudades
    public void listarCiudadesTest() {
        List<Ciudad> listaCiudades = ciudadRepo.findAll();
        Assertions.assertEquals(3, listaCiudades.size());
    }


}
