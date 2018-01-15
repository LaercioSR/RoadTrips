package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.util.Iterator;

public class Grafo implements IGrafo {

    private HashTable vertices;
    
    private class Vertex {
        private Object data;
        private HashMap arestas;
        
        public Vertex(Object data) {
            this.data = data;
            this.arestas = new HashMap();
        }
        
        public void addEdge(Vertex v, Edge e) throws DadoDuplicadoException {
            arestas.put(v, e);
        }
        
        public void removeEdge(Vertex v) throws DadoNaoEncontradoException {
            arestas.removeKey(v);
        }
        
        @Override
        public int hashCode() {
            return data.hashCode();
        }
    }
    
    private class Edge {
        private final Vertex vertexA;
        private final Vertex vertexB;
        private final double peso;

        public Edge(Vertex vertexA, Vertex vertexB, double peso) {
            this.vertexA = vertexA;
            this.vertexB = vertexB;
            this.peso = peso;
        }
    }
    
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
    
    @Override
    public Iterator iterator() {
        return new myIterator();
    }
    
    private class myIterator implements Iterator{

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object next() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
