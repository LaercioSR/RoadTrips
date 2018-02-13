package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe {@code HashTable} para armazenamento e manipulação de dados.
 */
public class HashTable implements IHashTable, Serializable {
    private final transient double LOAD_FACTOR = 0.5;
    private final transient Object EMPTY = new Object();
    
    Object[] datas;
    private int tamanhoArray;
    private int size = 0;
    
    /**
     *  Constroi um HashTable com 31 de tamanho.
     */
    public HashTable(){
        tamanhoArray = 31;
        this.datas = new Object[31];
    }
    
    /**
     * Controi um HashTable com o próximo número primo depois do número passado como 
     * paramêtro.
     * @param tamanhoArray Número para achar o número primo que será o tamanho do HashMap.
     */
    public void HashTable(int tamanhoArray){
        this.tamanhoArray = proximoPrimo(tamanhoArray - 1);
        this.datas = new Object[this.tamanhoArray];
    }

    /**
     * Método adiciona uma dado ao HashTable.
     * @param o Dado a ser adicionado.
     * @throws DadoDuplicadoException Casoo dado játenha sido adionado.
     */
    @Override
    public void put(Object o) throws DadoDuplicadoException {
        int i = encontrarPosicao(o);
        
        if(positionIsEmpty(i)){
            datas[i] = o;
            size++;
            
            if(loadFactor() > LOAD_FACTOR){
                resize();
            }
        }
        else{
            throw new DadoDuplicadoException();
        }
    }

    /**
     * Método retorna um dado desejado.
     * @param o Object parecido com que está sendo procurado.
     * @return Dado procurado.
     * @throws DadoNaoEncontradoException Caso o dado procurado não seja encontrado.
     */
    @Override
    public Object get(Object o) throws DadoNaoEncontradoException {
        int i = encontrarPosicao(o);
        
        while(datas[i] != null){
            if(datas[i] != EMPTY && datas[i].equals(o)){
                return datas[i];
            }
            i = (i + 1) % tamanhoArray;
        }
        
        throw new DadoNaoEncontradoException();
    }

    /**
     * Remove um dado do HashTable.
     * @param o Object parecido com que está sendo removido.
     * @return Dado removido.
     * @throws DadoNaoEncontradoException Caso o dado procurado não seja encontrado.
     */
    @Override
    public Object remove(Object o) throws DadoNaoEncontradoException {
        int pos = o.hashCode() % tamanhoArray;
        
        while(datas[pos] != null){
            if(datas[pos] != EMPTY && datas[pos].equals(o)){
                Object temp = datas[pos];
                datas[pos] = EMPTY;
                size--;
                return temp;
            }
            pos = (pos + 1) % tamanhoArray;
        }
        throw new DadoNaoEncontradoException();
    }
    
    /**
     * Método verdadeiro retorna se o HashTable estiver vazio.
     * @return True se o HashTable estiver vazio.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Método retorna o número de entidades no HashTable.
     * @return Número de entidades no HashTable.
     */
    @Override
    public int size() {
        return size;
    }
    
    // Método encontra uma posição para a entidade passada como paramêtro.
    private int encontrarPosicao(Object e){
        int pos = Math.abs(e.hashCode()) % tamanhoArray;
        int primeiroVazio = -1;
        
        while(datas[pos] != null && !datas[pos].equals(e)){
            if(primeiroVazio == -1 && datas[pos].equals(EMPTY)){
                primeiroVazio = pos;
            }
            pos = (pos + 1) % tamanhoArray;
        }
        
        if(datas[pos] == null && primeiroVazio != -1){
            return primeiroVazio;
        }else{
            return pos;
        }
    }
    
    // Método retorna verdadeiro se a posição passada como paramêtro estiver vazia.
    private boolean positionIsEmpty(int pos){
        return datas[pos] == null || datas[pos].equals(EMPTY);
    }
    
    // Método calcula o fator de carregamento doHashMap.
    private double loadFactor(){
        return size / (double) tamanhoArray;
    }
    
    // Método almenta o tamanho máximo do HashMap.
    private void resize() throws DadoDuplicadoException{
        Object[] temp = datas;
        tamanhoArray = proximoPrimo(tamanhoArray * 2);
        datas = new Object[tamanhoArray];
        
        for(Object e: temp){
            if(e != null && !e.equals(EMPTY))
                put(e);
        }
    }
    
    // Método encontro o próximo número primo maior que o número pasado como paramêtro.
    private static int proximoPrimo(int x){
        int z = 0;
        x += 1;
        
        for( ; z != 2; x++){
            z = 0;
            
            for(int i = 1; i <= x; i++){
                if((x % i)==0)
                    z++;
            }
        }
        
        return x;
    }

    /**
     * Método retorna o iterator do HashTable.
     * @return Iterator do HashTable.
     */
    @Override
    public Iterator iterator() {
        LinkedList linkedList = new LinkedList();
        
        for(Object a: datas){
            if(a != null && a != EMPTY){
                linkedList.add(a);
            }
        }
        
        return linkedList.descendingIterator();
    }
}
