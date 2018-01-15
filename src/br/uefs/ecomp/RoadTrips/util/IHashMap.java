package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;

public interface IHashMap {
    
    public void put(Object key, Object value) throws DadoDuplicadoException;
    
    public Object get(Object key) throws DadoNaoEncontradoException;
    
    public void removeKey(Object key) throws DadoNaoEncontradoException;
    
    public void removeValue(Object value) throws DadoNaoEncontradoException;
    
    public boolean isEmpty();
    
    public int size();
}
