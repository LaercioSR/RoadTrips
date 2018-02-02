package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.util.Iterator;

public class Vertex {

    private Object data;
    private HashMap arestas;

    public Vertex(Object data) {
        this.data = data;
        this.arestas = new HashMap();
    }

    public Object getData() {
        return data;
    }

    public void addEdge(Vertex v, Edge e) throws DadoDuplicadoException {
        arestas.put(v, e);
    }

    public void removeEdge(Vertex v) throws DadoNaoEncontradoException {
        arestas.removeKey(v);
    }
    
    public Iterator iteratorEdge() {
        return arestas.iterator();
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Vertex)
            return data.equals(((Vertex) o).getData());
        return false;
    }
}
