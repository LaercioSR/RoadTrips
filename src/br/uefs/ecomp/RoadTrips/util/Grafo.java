package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Classe manipula e armazera dados com o comportamento de um grafo.
 */
public class Grafo implements IGrafo, Serializable {

    private HashTable vertices;

    /**
     * Controi um grafo.
     */
    public Grafo() {
        vertices = new HashTable();
    }

    /**
     * Método cria e adiciona um vértice ao grafo.
     *
     * @param data Dado do vértice.
     * @throws DadoDuplicadoException
     */
    @Override
    public void addVertex(Object data) throws DadoDuplicadoException {
        Vertex novo = new Vertex(data);
        vertices.put(novo);
    }

    /**
     * Método retorna o número de vértices.
     *
     * @return Número de vertices.
     */
    @Override
    public int numVertices() {
        return vertices.size();
    }

    /**
     * Método remove o vértice que possui o mesmo dado do object passado como
     * paramêtro.
     *
     * @param o Dado do vértice a ser removido.
     * @throws DadoNaoEncontradoException Caso o vértice não seja encontrado.
     */
    @Override
    public void removeVertex(Object o) throws DadoNaoEncontradoException {
        Vertex verticeCmp = new Vertex(o);
        vertices.remove(verticeCmp);
    }

    /**
     * Método cria e adiciona uma aresta ao grafo.
     *
     * @param pontoA Ponto A da aresta.
     * @param pontoB Ponto B da aresta.
     * @param peso Peso da aresta.
     * @throws DadoNaoEncontradoException Caso não seja encontrado um dos
     * vértices com os pontos passados.
     * @throws DadoDuplicadoException Caso já exista uma aresta com esses
     * pontos.
     */
    @Override
    public void addEdge(Object pontoA, Object pontoB, double peso) throws DadoNaoEncontradoException, DadoDuplicadoException {
        Vertex verticeCmp = new Vertex(pontoA);
        Vertex verticeA = (Vertex) vertices.get(verticeCmp);
        verticeCmp = new Vertex(pontoB);
        Vertex verticeB = (Vertex) vertices.get(verticeCmp);

        Edge novo = new Edge(verticeA, verticeB, peso);
        verticeA.addEdge(verticeB, novo);
        verticeB.addEdge(verticeA, novo);
    }

    /**
     * Método remove a aresta que liga os pontos passados.
     *
     * @param pontoA Ponto A da aresta a ser removida.
     * @param pontoB Ponto B da aresta a ser removida.
     * @throws DadoNaoEncontradoException Caso não seja encontrado um dos
     * vértices com os pontos passados.
     */
    @Override
    public void removeEdge(Object pontoA, Object pontoB) throws DadoNaoEncontradoException {
        Vertex verticeCmp = new Vertex(pontoA);
        Vertex verticeA = (Vertex) vertices.get(verticeCmp);
        verticeCmp = new Vertex(pontoB);
        Vertex verticeB = (Vertex) vertices.get(verticeCmp);

        verticeA.removeEdge(verticeB);
        verticeB.removeEdge(verticeA);
    }

    /**
     * Método retorna o vértice que possui o object a como dado.
     *
     * @param a Dado do vértice.
     * @return Vértice desejado.
     * @throws DadoNaoEncontradoException Caso não encontre um vértice com o
     * object a como dado.
     */
    public Vertex getVertex(Object a) throws DadoNaoEncontradoException {
        Vertex novo = new Vertex(a);
        return (Vertex) vertices.get(novo);
    }

    /**
     * Método retorna o iterador dos vértices do grafo.
     *
     * @return Iterator dos vértices.
     */
    @Override
    public Iterator iterator() {
        return vertices.iterator();
    }
}
