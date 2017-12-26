package br.uefs.ecomp.RoadTrips.util;

import java.util.Iterator;

public class Grafo implements IGrafo {

    
    
    private class Vertex {
        private Object data;
        
        public void Vertex(Object data){
            this.data = data;
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
    
    @Override
    public void addVertex(Object data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator vertices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int numVertices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeVertex(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEdge(Object verticeA, Object verticeB, double peso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator edges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int numEdges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEdge(Object verticeA, Object verticeB, double peso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
