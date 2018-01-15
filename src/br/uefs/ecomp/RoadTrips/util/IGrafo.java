package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;

public interface IGrafo extends Iterable{
    
    public void addVertex(Object data) throws DadoDuplicadoException;
    
    public int numVertices();
    
    public void removeVertex(Object o) throws DadoNaoEncontradoException;
    
    public void addEdge(Object pontoA, Object pontoB, double peso) throws DadoDuplicadoException, DadoNaoEncontradoException;
    
    public void removeEdge(Object pontoA, Object pontoB) throws DadoNaoEncontradoException;
}
