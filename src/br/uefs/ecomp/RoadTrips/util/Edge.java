package br.uefs.ecomp.RoadTrips.util;

public class Edge {
    private final Vertex vertexA;
    private final Vertex vertexB;
    private final double peso;

    public Edge(Vertex vertexA, Vertex vertexB, double peso) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.peso = peso;
    }
}
