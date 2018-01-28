package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import br.uefs.ecomp.RoadTrips.util.Grafo;
import br.uefs.ecomp.RoadTrips.util.HashMap;

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

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
