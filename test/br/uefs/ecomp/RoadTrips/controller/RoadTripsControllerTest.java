package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.model.Estabelecimento;
import br.uefs.ecomp.RoadTrips.model.Intersecao;
import br.uefs.ecomp.RoadTrips.model.TipoEstabelecimento;
import br.uefs.ecomp.RoadTrips.model.TipoIntersecao;
import br.uefs.ecomp.RoadTrips.model.Usuario;
import br.uefs.ecomp.RoadTrips.model.Viagem;
import br.uefs.ecomp.RoadTrips.util.Edge;
import br.uefs.ecomp.RoadTrips.util.Vertex;
import java.util.Iterator;
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
    
    @Test
    public void testAutenticarLogin() throws DadoDuplicadoException, DadoNaoEncontradoException {
        controller.cadastrarUsuario("ROOT", "", "root@gmail.com");
        Usuario usuario = controller.autenticarLogin("ROOT", "");
        Assert.assertEquals("ROOT", usuario.getLogin());
    }
    
    @Test (expected = DadoNaoEncontradoException.class)
    public void testAutenticarLoginSenhaErrada() throws DadoDuplicadoException, DadoNaoEncontradoException {
        controller.cadastrarUsuario("ROOT", "", "root@gmail.com");
        controller.autenticarLogin("ROOT", "1234");
    }
    
    @Test (expected = DadoNaoEncontradoException.class)
    public void testAutenticarLoginUsuarioInexistente() throws DadoDuplicadoException, DadoNaoEncontradoException {
        controller.autenticarLogin("ROOT", "");
    }
    
    @Test
    public void testAdicionarCidade() throws DadoDuplicadoException {
        Cidade cidade = controller.adicionarCidade("Pintadas", 531.4, 10342, "Cidade pequena, com economia voltada a agricultura familia", -11.866028, -39.907202, null);
        Assert.assertTrue(controller.pontoCadastrado(cidade));
    }
    
    @Test (expected = DadoDuplicadoException.class)
    public void testAdicionarCidadeDuplicada() throws DadoDuplicadoException {
        controller.adicionarCidade("Pintadas", 531.4, 10342, "Cidade pequena, com economia voltada a agricultura familia", -11.866028, -39.907202, null);
        controller.adicionarCidade("Pintadas", 0.0, 0, "", 0.0, 0.0, null);
    }
    
    @Test
    public void testAdicionarIntersecao() throws DadoDuplicadoException {
        Intersecao intersecao = controller.adicionarIntersecao(TipoIntersecao.rotula, "Teste", 0, 0);
        Assert.assertTrue(controller.pontoCadastrado(intersecao));
    }
    
    @Test (expected = DadoDuplicadoException.class)
    public void testAdicionarIntersecaoDuplicada() throws DadoDuplicadoException {
        controller.adicionarIntersecao(TipoIntersecao.rotula, "Teste", 200.0, -500.1);
        controller.adicionarIntersecao(TipoIntersecao.cruzamento, "Teste", 0, 0);
    }
    
    @Test
    public void testAdicionarEstabelecimento() throws DadoDuplicadoException {
        Cidade cidade = controller.adicionarCidade("Pintadas", 531.4, 10342, "Cidade pequena, com economia voltada a agricultura familia", -11.866028, -39.907202, null);
        controller.adicionarEstabelecimento("Restaurante das Mulheres", TipoEstabelecimento.LugaresParaComer, cidade, null);
        
        Iterator it = cidade.iteratorEstabelecimentos();
        
        Assert.assertEquals("Restaurante das Mulheres", ((Estabelecimento)it.next()).getNome());
    }
    
    @Test
    public void testAdicionarRota() throws DadoDuplicadoException, DadoNaoEncontradoException {
        Cidade cidadeA = controller.adicionarCidade("Pintadas", 531.4, 10342, "Cidade pequena, com economia voltada a agricultura familia", -11.866028, -39.907202, null);
        Cidade cidadeB = controller.adicionarCidade("Ipirá", 3.060, 62631, "Só importa que fronteira com Pintadas", -12.156631, -39.741004, null);
        controller.adicionarRota(cidadeA, cidadeB, 48.3);
        
        Vertex vertexA = controller.buscarVerticePonto(cidadeA);
        Vertex vertexB = controller.buscarVerticePonto(cidadeB);
        
        Iterator itVertexA = vertexA.iteratorEdge();
        Iterator itVertexB = vertexB.iteratorEdge();
        
        Edge testeA = (Edge) itVertexA.next();
        Assert.assertEquals(vertexB, testeA.getVertexB());
        Assert.assertEquals(48.3, testeA.getPeso(), 0.001);
        
        Edge testeB = (Edge) itVertexB.next();
        Assert.assertEquals(vertexA, testeB.getVertexA());
        Assert.assertEquals(48.3, testeB.getPeso(), 0.001);
    }
    
    @Test (expected = DadoDuplicadoException.class)
    public void testAdicionarEstabelecimentoDuplicada() throws DadoDuplicadoException {
        Cidade cidade = controller.adicionarCidade("Pintadas", 531.4, 10342, "Cidade pequena, com economia voltada a agricultura familia", -11.866028, -39.907202, null);
        controller.adicionarEstabelecimento("Restaurante das Mulheres", TipoEstabelecimento.LugaresParaComer, cidade, null);
        controller.adicionarEstabelecimento("Restaurante das Mulheres", TipoEstabelecimento.LugaresParaComer, cidade, null);
    }
    
    @Test
    public void testCriarViagemParaUsuario() throws DadoDuplicadoException, DadoNaoEncontradoException {
        controller.cadastrarUsuario("Mario", "PrincessaPeach", "dmca@noa.nintendo.com");
        Usuario usuario = controller.autenticarLogin("Mario", "PrincessaPeach");
        Viagem viagem = controller.criarViagem("Viagem ao Reino do Cogumelo");
        
        controller.adiocionarViagemUsuario(usuario, viagem);
        Iterator it = usuario.iteratorViagem();
        
        Assert.assertEquals("Viagem ao Reino do Cogumelo", ((Viagem)it.next()).getNome());
    }
}
