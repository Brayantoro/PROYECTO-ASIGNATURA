package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaTest {

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private ProductoRepo productoRepo;

    //Metodos CRUD Test
    //Test para registrar un subasta
    @Test
    public void registrarSubastaTest() {
        Producto producto= new Producto();
        producto.setCodigo(4);
        productoRepo.save(producto);

        Subasta nuevaSubasta = new Subasta();
        nuevaSubasta.setCodigo(4);
        nuevaSubasta.setFechaLimite(LocalDate.of(2010,12,24));
        nuevaSubasta.setProductoSubasta(producto);

        Subasta saveSubasta = subastaRepo.save(nuevaSubasta);

        Assertions.assertNotNull(saveSubasta);
    }

    //Test para eliminar una subasta
    @Test
    @Sql("classpath:subastas.sql")
    public void eliminarSubastaTest() {
        subastaRepo.deleteById(2);
        Subasta subastaEliminada = subastaRepo.findById(2).orElse(null);

        Assertions.assertNull(subastaEliminada);
    }

    //Test para actualizar una subasta
    @Test
    @Sql("classpath:subastas.sql")
    public void actualizarSubastaTest() {
        Subasta subastaGuardada = subastaRepo.findById(1).orElse(null);
        assert subastaGuardada != null;
        subastaGuardada.setFechaLimite(LocalDate.of(2021, 10, 20));
        subastaRepo.save(subastaGuardada);
        Subasta subastaBuscada = subastaRepo.findById(1).orElse(null);
        assert subastaBuscada != null;
        Assertions.assertEquals(LocalDate.of(2021, 10, 20),subastaBuscada.getFechaLimite());
    }

    @Test
    @Sql("classpath:subastas.sql")
    public void listarSubastaTest() {
        List<Subasta> listaSubastas = subastaRepo.findAll();
        listaSubastas.forEach(System.out::println);
    }
}
