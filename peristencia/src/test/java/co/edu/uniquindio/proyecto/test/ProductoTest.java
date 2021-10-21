package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
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
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

//(1,"TECNOLOGIA","para realizar llamdas",800000,"10/08/2021","celular",199002,4,1);
    //Test para registrar un producto
    @Test
    public void regsitrarProductoTest(){

       Ciudad ciudad = new Ciudad();
        ciudad.setNombre("cartagena");
        ciudadRepo.save(ciudad);
        Producto producto = new Producto();
        producto.setCodigo(4);
        producto.setCategoria(Categoria.BELLEZA);
        producto.setDescripcion(" portatil de gama alta");
        producto.setDescuento(27790);
        producto.setFechaLimite( LocalDate.of(2022, 10, 30));
        producto.setNombre("portatil");
        producto.setPrecio(3555555);
        producto.setUnidades(3);
        producto.setCiudadProducto(ciudad);

        Producto Producto = productoRepo.save(producto);

        Assertions.assertNotNull(producto);
    }

    //Test para eliminar un producto
    @Test
    @Sql("classpath:productos.sql")
    public void eliminarProductoTest(){
        productoRepo.deleteById(1);
        Producto productoEliminado = productoRepo.findById(1).orElse(null);
        Assertions.assertNull(productoEliminado);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void actualizarProductoTest(){
        Producto productoGuardado = productoRepo.findById(1).orElse(null);
        assert productoGuardado != null;
        productoGuardado.setDescuento(200000);

        Producto productoBuscado = productoRepo.findById(1).orElse(null);
        assert productoBuscado != null;
        Assertions.assertEquals(200000,productoBuscado.getDescuento());
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarProductoTest(){
        List<Producto> listaProductos = productoRepo.findAll();
        listaProductos.forEach(System.out::println);
    }
}
