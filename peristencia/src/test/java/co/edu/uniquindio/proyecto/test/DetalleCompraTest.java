package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    private CompraRepo compraRepo;
    private ProductoRepo productoRepo;
    @Test
    //registrar un mensaje
    public void registrardetalleCompraTest() {
        Compra compra = new Compra();
        compraRepo.save(compra);

        Producto p1= new Producto();
        productoRepo.save(p1);
        DetalleCompra detalleCompra = new DetalleCompra(1,200000,3,compra,p1);
        DetalleCompra detalleCompraGuardado = detalleCompraRepo.save(detalleCompra);
        Assertions.assertNotNull(detalleCompraGuardado);

    }

    @Test
    @Sql("classpath:detalleCompras.sql")
    //Eliminar un detalle compra
    public void eliminardetalleCompraTest() {

        detalleCompraRepo.deleteById(1);
        DetalleCompra detalleCompraBuscado = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertNull(detalleCompraBuscado);

    }

    @Test
    //Actualizar un detalle compra
    @Sql("classpath:detalleCompras.sql")
    public void actualizardetalleCompraTest() {
        DetalleCompra detalleCompraGuardado = detalleCompraRepo.findById(1).orElse(null);
        detalleCompraGuardado.setPrecioProducto(30000);
        detalleCompraRepo.save(detalleCompraGuardado);

        DetalleCompra detalleCompraBuscado = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertEquals(30000, detalleCompraBuscado.getPrecioProducto());


    }

    @Test
    @Sql("classpath:detalleCompras.sql")
    public void listardetalleCompraTest() {
        List<DetalleCompra> listDetalleCompras = detalleCompraRepo.findAll();
        listDetalleCompras.forEach(u -> System.out.println(u));

    }

}
