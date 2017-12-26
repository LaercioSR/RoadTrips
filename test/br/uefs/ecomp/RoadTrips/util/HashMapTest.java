package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
    
    HashMap mapaHash;
    
    @Before
    public void setUp() {
        mapaHash = new HashMap();
    }
    
    @Test
    public void testInserir() throws DadoDuplicadoException {
        Assert.assertTrue(mapaHash.isEmpty());
        mapaHash.put("Maçã", 7);
        mapaHash.put("Carambola", 8);
        mapaHash.put("Uva", 10);
        
        Assert.assertFalse(mapaHash.isEmpty());
        Assert.assertEquals(3, mapaHash.size());
    }
    
    @Test(expected = DadoDuplicadoException.class)
    public void testInserirChaveDuplicada() throws DadoDuplicadoException {
        mapaHash.put("Maçã", 7);
        mapaHash.put("Maçã", 8);
    }
    
    @Test
    public void testProcurarDado() throws DadoDuplicadoException, DadoNaoEncontradoException {
        mapaHash.put("Maçã", 7);
        mapaHash.put("Carambola", 8);
        
        Integer fruta = (Integer) mapaHash.get("Maçã");
        Assert.assertEquals(7, fruta.intValue());
    }
    
    @Test(expected = DadoNaoEncontradoException.class)
    public void testProcurarDadoNaoInserido() throws DadoNaoEncontradoException {
        mapaHash.get("Maçã");
    }
    
    @Test
    public void testRemoverPelaChave() throws DadoDuplicadoException, DadoNaoEncontradoException {
        mapaHash.put("Maçã", 7);
        mapaHash.put("Pêra", 2);
        mapaHash.put("Carambola", 8);
        mapaHash.put("Uva", 10);
        
        mapaHash.removeKey("Maçã");
        Assert.assertEquals(3, mapaHash.size());
        mapaHash.removeKey("Pêra");
        Assert.assertEquals(2, mapaHash.size());
    }
    
    @Test
    public void testRemoverPeloValor() throws DadoDuplicadoException, DadoNaoEncontradoException {
        mapaHash.put("Maçã", 7);
        mapaHash.put("Pêra", 2);
        mapaHash.put("Carambola", 8);
        mapaHash.put("Uva", 10);
        
        mapaHash.removeValue(7);
        Assert.assertEquals(3, mapaHash.size());
        mapaHash.removeValue(10);
        Assert.assertEquals(2, mapaHash.size());
    }
    
    @Test(expected = DadoNaoEncontradoException.class)
    public void testRemoverDadoNaoInseridoPelaChave() throws DadoNaoEncontradoException {
        mapaHash.removeKey("Maçã");
    }
    
    @Test(expected = DadoNaoEncontradoException.class)
    public void testRemoverDadoNaoInseridoPeloValor() throws DadoNaoEncontradoException {
        mapaHash.removeValue(7);
    }
    
    @Test
    public void testProcurarDepoisDadosRemovidos() throws DadoDuplicadoException, DadoNaoEncontradoException {
        mapaHash.put("Caju", 9);
        mapaHash.put("Maçã", 7);
        mapaHash.put("Pêra", 2);
        mapaHash.put("Carambola", 8);
        mapaHash.put("Uva", 10);
        
        mapaHash.removeKey("Maçã");
        try{
            mapaHash.get("Maçã");
        } catch(DadoNaoEncontradoException e){
            Assert.assertTrue(true);
        }
        
        Integer fruta = (Integer) mapaHash.get("Pêra");
        Assert.assertEquals(2, fruta.intValue());
    }
}