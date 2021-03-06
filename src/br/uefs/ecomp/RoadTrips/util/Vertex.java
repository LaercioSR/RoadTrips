package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe implementa o comportamento de um vértice de um grafo.
 */
public class Vertex implements Serializable {

    private Object data;
    private HashMap arestas;

    /**
     * Constroi o vértice ({@code Vertex}) com o dado passado como paramêtro.
     * @param data 
     */
    public Vertex(Object data) {
        this.data = data;
        this.arestas = new HashMap();
    }

    /**
     * Método retorna o dado do Vertex.
     * @return Dado do vertex.
     */
    public Object getData() {
        return data;
    }

    /**
     * Método adiciona uma aresta ao vértice.
     * @param v O outro vértice da aresta.
     * @param e A aresta.
     * @throws DadoDuplicadoException Caso já tenha uma aresta com esse vértice.
     */
    public void addEdge(Vertex v, Edge e) throws DadoDuplicadoException {
        LinkedList listaAresta;
        try {
            listaAresta = (LinkedList) arestas.get(v);
        } catch (DadoNaoEncontradoException ex) {
            listaAresta = new LinkedList();
            arestas.put(v, listaAresta);
        }
        listaAresta.add(e);
    }

    /**
     * Método remove uma aresta.
     * @param v O outro vértice da aresta.
     * @throws DadoNaoEncontradoException Caso não encontre uma aresta com o vértice desejado.
     */
    public void removeEdge(Vertex v) throws DadoNaoEncontradoException {
        arestas.removeKey(v);
    }
    
    /**
     * Método retorna o Iterador de arestas.
     * @return Iterator de arestas.
     */
    public Iterator iteratorEdge() {
        LinkedList listArestas = new LinkedList();
        Iterator it = arestas.iterator();
        
        while(it.hasNext()){
            Iterator itArestas = ((LinkedList)it.next()).descendingIterator();
            while(itArestas.hasNext()){
                listArestas.add((Edge) itArestas.next());
            }
        }
        
        return listArestas.descendingIterator();
    }

    /**
     * Método cria um hash para o Vértice.
     * @return Hash do vértice.
     */
    @Override
    public int hashCode() {
        return data.hashCode();
    }
    
    /**
     * Método compara se o objeto passado como paramêtro é igual a esse vértice pelo dado.
     * @param o Object a ser comparado.
     * @return True se o object é igual ao vértice.
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Vertex)
            return data.equals(((Vertex) o).getData());
        return false;
    }
}
