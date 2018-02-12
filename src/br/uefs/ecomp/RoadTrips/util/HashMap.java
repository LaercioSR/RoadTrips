package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe {@code HashMap} malipula e armazena dados no formato (chave, valor).
 */
public class HashMap implements IHashMap {
    private final double LOAD_FACTOR = 0.5;
    private final Entry EMPTY = new Entry(new Object(), new Object());
    
    private Entry[] entries;
    private int tamanhoArray;
    private int size = 0;
    
    private class Entry{
        private Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
        
        @Override
        public boolean equals(Object o){
            if(o instanceof Entry)
                return (key == null && ((Entry) o).getKey() == null) || (key != null && key.equals(((Entry) o).getKey()));
            return false;
        }
    }

    /**
     * Controi um HashMap com o próximo número primo depois do número passado como 
     * paramêtro.
     * @param tamanhoArray Número para achar o número primo que será o tamanho do HashMap.
     */
    public HashMap(int tamanhoArray) {
        this.tamanhoArray = proximoPrimo(tamanhoArray - 1);
        this.entries = new Entry[this.tamanhoArray];
    }

    /**
     * Constroi um HashMap com 31 de tamanho.
     */
    public HashMap() {
        tamanhoArray = 31;
        this.entries = new Entry[31];
    }
    
    /**
     * Adiciona uma entidade ao HashMap.
     * @param key Chave do valor a ser adicionado.
     * @param value Valor a ser adicionado.
     * @throws DadoDuplicadoException Caso, no HashMap, já exista uma chave (Key) igual a passada como paramêtro.
     */
    @Override
    public void put(Object key, Object value) throws DadoDuplicadoException {
        Entry novo = new Entry(key, value);
        int i = encontrarPosicao(novo);
        
        if(positionIsEmpty(i)){
            entries[i] = novo;
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
     * Método retorna o valor da Chave (Key) desejada.
     * @param key Chave do valor que deseja ser recuperado.
     * @return Valor referente a Key passada como paramêtro.
     * @throws DadoNaoEncontradoException Caso não encontre a chave passada no HashMap.
     */
    @Override
    public Object get(Object key) throws DadoNaoEncontradoException {
        int pos = Math.abs(key.hashCode()) % tamanhoArray;
        
        while(entries[pos] != null){
            if(entries[pos] != EMPTY && entries[pos].getKey().equals(key)){
                return entries[pos].getValue();
            }
            pos = (pos + 1) % tamanhoArray;
        }
        
        throw new DadoNaoEncontradoException();
    }
    
    /**
     * Método remove o valor da Chave (Key) desejada.
     * @param key Chave do valor que deseja ser removida.
     * @throws DadoNaoEncontradoException Caso não encontre a chave passada no HashMap.
     */
    @Override
    public void removeKey(Object key) throws DadoNaoEncontradoException {
        int pos = Math.abs(key.hashCode()) % tamanhoArray;
        
        while(entries[pos] != null){
            if(entries[pos] != EMPTY && entries[pos].getKey().equals(key)){
                entries[pos] = EMPTY;
                size--;
                return;
            }
            pos = (pos + 1) % tamanhoArray;
        }
        throw new DadoNaoEncontradoException();
    }
    
    /**
     * Método remove o valor que foi passado como paramêtro.
     * @param value Valor a ser removido.
     * @throws DadoNaoEncontradoException Caso não encontre o valor passada do HashMap. 
     */
    @Override
    public void removeValue(Object value) throws DadoNaoEncontradoException {
        for(int i = 0; i < tamanhoArray; i++){
            if(entries[i] != null && entries[i] != EMPTY && entries[i].getValue().equals(value)){
                entries[i] = EMPTY;
                size--;
                return;
            }
        }
        throw new DadoNaoEncontradoException();
    }
    
    /**
     * Método retorna o número de entidades no HashMap.
     * @return Número de entidades no HashMap.
     */
    @Override
    public int size(){
        return size;
    }
    
    /**
     * Método verdadeiro retorna se o HashMap estiver vazio.
     * @return True se o HashMap estiver vazio.
     */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    
    // Método encontra uma posição para a entidade passada como paramêtro.
    private int encontrarPosicao(Entry e){
        int pos = Math.abs(e.getKey().hashCode()) % tamanhoArray;
        int primeiroVazio = -1;
        
        while(entries[pos] != null && !entries[pos].equals(e)){
            if(primeiroVazio == -1 && entries[pos].equals(EMPTY)){
                primeiroVazio = pos;
            }
            pos = (pos + 1) % tamanhoArray;
        }
        
        if(entries[pos] == null && primeiroVazio != -1){
            return primeiroVazio;
        }else{
            return pos;
        }
    }
    
    // Método retorna verdadeiro se a posição passada como paramêtro estiver vazia.
    private boolean positionIsEmpty(int pos){
        return entries[pos] == null || entries[pos].equals(EMPTY);
    }
    
    // Método calcula o fator de carregamento doHashMap.
    private double loadFactor(){
        return size / (double) tamanhoArray;
    }
    
    // Método almenta o tamanho máximo do HashMap.
    private void resize() throws DadoDuplicadoException{
        Entry[] temp = entries;
        tamanhoArray = proximoPrimo(tamanhoArray * 2);
        entries = new Entry[tamanhoArray];
        
        for(Entry e: temp){
            if(e != null && !e.equals(EMPTY))
                put(e.getKey(), e.getValue());
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
     * Método retorna um iterator com os valores do HashMap.
     * @return Iterator dos valores do HashMap.
     */
    @Override
    public Iterator iterator() {
        LinkedList linkedList = new LinkedList();
        
        for(Entry a: entries){
            if(a != null && a != EMPTY){
                linkedList.add(a.getValue());
            }
        }
        
        return linkedList.descendingIterator();
    }
}
