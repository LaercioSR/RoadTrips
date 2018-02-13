package br.uefs.ecomp.RoadTrips.util;

import java.io.Serializable;

/**
 * Classe implementa o comportamento de uma aresta de um grafo, com dois vértices 
 * e um peso.
 */
public class Edge implements Serializable {
    private final Vertex vertexA;
    private final Vertex vertexB;
    private final double peso;

    /**
     * Controi uma aresta com os dados passados.
     * @param vertexA
     * @param vertexB
     * @param peso 
     */
    public Edge(Vertex vertexA, Vertex vertexB, double peso) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.peso = peso;
    }

    /**
     * Método retorna o vértice A da aresta.
     * @return Vértice A.
     */
    public Vertex getVertexA() {
        return vertexA;
    }

    /**
     * Método retorna o vértice B da aresta.
     * @return Vértice B.
     */
    public Vertex getVertexB() {
        return vertexB;
    }

    /**
     * Método retorna o peso da aresta.
     * @return Peso da aresta.
     */
    public double getPeso() {
        return peso;
    }
}
