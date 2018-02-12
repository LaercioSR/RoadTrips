package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;

/**
 * Interface para Grafos. Defines os métodos úteis para um grafo.
 */
public interface IGrafo extends Iterable{
    
    /**
     * Método cria e adiciona um vértice ao grafo.
     * @param data Dado do vértice.
     * @throws DadoDuplicadoException 
     */
    public void addVertex(Object data) throws DadoDuplicadoException;
    
    /**
    * Método retorna o número de vértices.
    * @return Número de vertices.
    */
    public int numVertices();
    
    /**
    * Método remove o vértice que possui o mesmo dado do object passado como paramêtro.
    * @param o Dado do vértice a ser removido.
    * @throws DadoNaoEncontradoException Caso o vértice não seja encontrado.
    */
    public void removeVertex(Object o) throws DadoNaoEncontradoException;
    
    /**
    * Método cria e adiciona uma aresta ao grafo.
    * @param pontoA Ponto A da aresta.
    * @param pontoB Ponto B da aresta.
    * @param peso Peso da aresta.
    * @throws DadoNaoEncontradoException Caso não seja encontrado um dos vértices com os pontos passados.
    * @throws DadoDuplicadoException Caso já exista uma aresta com esses pontos.
    */  
    public void addEdge(Object pontoA, Object pontoB, double peso) throws DadoDuplicadoException, DadoNaoEncontradoException;
    
    /**
    * Método remove a aresta que liga os pontos passados.
    * @param pontoA Ponto A da aresta a ser removida.
    * @param pontoB Ponto B da aresta a ser removida.
    * @throws DadoNaoEncontradoException Caso não seja encontrado um dos vértices com os pontos passados.
    */
    public void removeEdge(Object pontoA, Object pontoB) throws DadoNaoEncontradoException;
}
