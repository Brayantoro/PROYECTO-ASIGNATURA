package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
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
public class CompraTests {
    @Autowired
    private CompraRepo compraRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;


    @Test
    public void registrarCompraTest() {

        Usuario u= new Usuario();
        usuarioRepo.save(u);
        List<DetalleCompra> detalleComp=detalleCompraRepo.findAll();
        Compra miCp = new Compra("0", LocalDate.of(2018, 10, 30),"TARJETA",u,detalleComp);
        Compra compraGuardado = compraRepo.save(miCp);
        Assertions.assertNotNull(compraGuardado);
    }

    @Test
    @Sql("classpath:compras.sql")
    //Eliminar una compra
    public void eliminarCompraTest() {
        compraRepo.deleteById(1);
        Compra compraEliminada = compraRepo.findById(1).orElse(null);

        Assertions.assertNull(compraEliminada);
    }

    @Test
    @Sql("classpath:compras.sql")
    //Actualizar una compra
    public void actualizarCompraTest() {
        Compra buscada = compraRepo.findById(1).orElse(null);
        buscada.setMedioPago("Efectivo");
        Compra compraNueva = compraRepo.save(buscada);

        Assertions.assertEquals("Efectivo", compraNueva.getMedioPago());
    }

    @Test
    @Sql("classpath:compras.sql")
    //Mostrar lista de compras de un usuario
    public void listarComprasTest() {
        List<Compra> listaCompras = compraRepo.findAll();

        Assertions.assertEquals(3, listaCompras.size());
    }

}
