package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;

/**
 * Interfac para HashTables. Define método úteis para HashTable.
 * @author Laercio
 */
public interface IHashTable extends Iterable {
 
    /**
     * Método adiciona uma dado ao HashTable.
     * @param o Dado a ser adicionado.
     * @throws DadoDuplicadoException Casoo dado játenha sido adionado.
     */   
    public void put(Object o) throws DadoDuplicadoException;
    
    /**
     * Método retorna um dado desejado.
     * @param o Object parecido com que está sendo procurado.
     * @return Dado procurado.
     * @throws DadoNaoEncontradoException Caso o dado procurado não seja encontrado.
     */
    public Object get(Object o) throws DadoNaoEncontradoException;
   
    /**
     * Remove um dado do HashTable.
     * @param o Object parecido com que está sendo removido.
     * @return Dado removido.
     * @throws DadoNaoEncontradoException Caso o dado procurado não seja encontrado.
     */
    public Object remove(Object o) throws DadoNaoEncontradoException;
    
    /**
     * Método verdadeiro retorna se o HashTable estiver vazio.
     * @return True se o HashTable estiver vazio.
     */
    public boolean isEmpty();
     
    /**
     * Método retorna o número de entidades no HashTable.
     * @return Número de entidades no HashTable.
     */   
    public int size();
}
