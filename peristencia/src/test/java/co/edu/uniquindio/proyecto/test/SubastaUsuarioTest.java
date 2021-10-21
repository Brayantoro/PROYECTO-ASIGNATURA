package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
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
public class SubastaUsuarioTest {

    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;
    @Autowired
    private SubastaRepo subastaRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrarSubastaTest() {
        //(1,"24/12/2021",100000,1,"01");
        Subasta subasta =new Subasta();
        subasta.setCodigo(4);
        subastaRepo.save(subasta);
        Usuario usuario=new Usuario();
        usuario.setCodigo("05");
        usuarioRepo.save(usuario);

        SubastaUsuario nuevaSubastaUsuario = new SubastaUsuario();
        nuevaSubastaUsuario.setCodigo(4);
        nuevaSubastaUsuario.setFechaLimite(LocalDate.of(2022,12,03));
        nuevaSubastaUsuario.setValor(900000);
        nuevaSubastaUsuario.setSubastaSubastaUsuario(subasta);
        nuevaSubastaUsuario.setUsuarioSubastaUsuario(usuario);

        SubastaUsuario guardada = subastaUsuarioRepo.save(nuevaSubastaUsuario);

        Assertions.assertNotNull(guardada);
    }


    @Test
    @Sql("classpath:subastaUsuarios.sql")
    public void eliminarSubastaUsuarioTest() {
        subastaUsuarioRepo.deleteById(2);
        SubastaUsuario subastaEliminada = subastaUsuarioRepo.findById(2).orElse(null);

        Assertions.assertNull(subastaEliminada);
    }

    //Test para actualizar una subasta
    @Test
    @Sql("classpath:subastaUsuarios.sql")
    public void actualizarSubastaTest() {
        SubastaUsuario subastaUsuarioGuardada = subastaUsuarioRepo.findById(1).orElse(null);
        assert subastaUsuarioGuardada != null;
        subastaUsuarioGuardada.setFechaLimite(LocalDate.of(2021, 10, 20));

        subastaUsuarioRepo.save(subastaUsuarioGuardada);
        SubastaUsuario subastaUsuarioBuscada = subastaUsuarioRepo.findById(1).orElse(null);
        assert subastaUsuarioBuscada != null;
        Assertions.assertEquals(LocalDate.of(2021, 10, 20),subastaUsuarioBuscada.getFechaLimite());
    }

    @Test
    @Sql("classpath:subastaUsuarios.sql")
    public void listarSubastaTest() {
        List<SubastaUsuario> listaSubastaUsuario = subastaUsuarioRepo.findAll();
        listaSubastaUsuario.forEach(System.out::println);
    }

}
