package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.util.Iterator;

public class Grafo implements IGrafo {

    private HashTable vertices;
    
    public Grafo(){
        vertices = new HashTable();
    }
    
    @Override
    public void addVertex(Object data) throws DadoDuplicadoException {
        Vertex novo = new Vertex(data);
        vertices.put(novo);
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public void removeVertex(Object o) throws DadoNaoEncontradoException {
        Vertex verticeCmp = new Vertex(o);
        vertices.remove(verticeCmp);
    }

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

    @Override
    public void removeEdge(Object pontoA, Object pontoB) throws DadoNaoEncontradoException {
        Vertex verticeCmp = new Vertex(pontoA);
        Vertex verticeA = (Vertex) vertices.get(verticeCmp);
        verticeCmp = new Vertex(pontoB);
        Vertex verticeB = (Vertex) vertices.get(verticeCmp);
        
        verticeA.removeEdge(verticeB);
        verticeB.removeEdge(verticeA);
    }
    
    public Vertex getVertex(Object a) throws DadoNaoEncontradoException {
        Vertex novo = new Vertex(a);
        return (Vertex) vertices.get(novo);
    }
    
    @Override
    public Iterator iterator() {
        return vertices.iterator();
    }
}
