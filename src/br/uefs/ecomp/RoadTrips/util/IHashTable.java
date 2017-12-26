package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;

public interface IHashTable {
    
    public void put(Object o) throws DadoDuplicadoException;
    
    public Object get(Object o) throws DadoNaoEncontradoException;
    
    public Object remove(Object o) throws DadoNaoEncontradoException;
    
    public boolean isEmpty();
    
    public int size();
}
