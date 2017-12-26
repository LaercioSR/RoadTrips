package br.uefs.ecomp.RoadTrips.util;

import java.util.Iterator;

public interface IGrafo extends Iterable{
    
    public void addVertex(Object data);
    
    public Iterator vertices();
    
    public int numVertices();
    
    public void removeVertex(Object o);
    
    public void addEdge(Object verticeA, Object verticeB, double peso);
    
    public Iterator edges();
    
    public int numEdges();
    
    public void removeEdge(Object verticeA, Object verticeB, double peso);
}
