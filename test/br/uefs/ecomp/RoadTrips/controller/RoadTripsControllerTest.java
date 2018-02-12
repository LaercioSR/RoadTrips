package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class RoadTripsControllerTest {
    private RoadTripsController controller;
    
    
    @Before
    public void setUp() {
        controller = new RoadTripsController();
    }
    
    @Test
    public void testCadastrarUsuario() throws DadoDuplicadoException {
        Assert.assertFalse(controller.usuarioCadastrado("ROOT"));
        controller.cadastrarUsuario("ROOT", "", "root@gmail.com");
        Assert.assertTrue(controller.usuarioCadastrado("ROOT"));
    }
    
    @Test (expected = DadoDuplicadoException.class)
    public void testCadastrarUsuarioLoginIndisponivel() throws DadoDuplicadoException {
        controller.cadastrarUsuario("ROOT", "", "root@gmail.com");
        controller.cadastrarUsuario("ROOT", "1234", "root1234@hotmail.com");
    }
    
    
}
