package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.util.Iterator;

/**
 * Inteface para HashMaps. Defines métodos úteis para MashMap.
 * @author Laercio
 */
public interface IHashMap {
    
    /**
     * Adiciona uma entidade ao HashMap.
     * @param key Chave do valor a ser adicionado.
     * @param value Valor a ser adicionado.
     * @throws DadoDuplicadoException Caso, no HashMap, já exista uma chave (Key) igual a passada como paramêtro.
     */
    public void put(Object key, Object value) throws DadoDuplicadoException;
    
    /**
     * Método retorna o valor da Chave (Key) desejada.
     * @param key Chave do valor que deseja ser recuperado.
     * @return Valor referente a Key passada como paramêtro.
     * @throws DadoNaoEncontradoException Caso não encontre a chave passada no HashMap.
     */
    public Object get(Object key) throws DadoNaoEncontradoException;
    
    /**
     * Método remove o valor da Chave (Key) desejada.
     * @param key Chave do valor que deseja ser removida.
     * @throws DadoNaoEncontradoException Caso não encontre a chave passada no HashMap.
     */    
    public void removeKey(Object key) throws DadoNaoEncontradoException;
     
    /**
     * Método remove o valor que foi passado como paramêtro.
     * @param value Valor a ser removido.
     * @throws DadoNaoEncontradoException Caso não encontre o valor passada do HashMap. 
     */   
    public void removeValue(Object value) throws DadoNaoEncontradoException;
        
    /**
     * Método verdadeiro retorna se o HashMap estiver vazio.
     * @return True se o HashMap estiver vazio.
     */
    public boolean isEmpty();
     
    /**
     * Método retorna o número de entidades no HashMap.
     * @return Número de entidades no HashMap.
     */   
    public int size();
        
    /**
     * Método retorna um iterator com os valores do HashMap.
     * @return Iterator dos valores do HashMap.
     */
    public Iterator iterator();
}
