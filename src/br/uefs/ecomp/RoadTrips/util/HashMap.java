package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.util.Iterator;
import java.util.LinkedList;

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

    public HashMap(int tamanhoArray) {
        this.tamanhoArray = proximoPrimo(tamanhoArray - 1);
        this.entries = new Entry[this.tamanhoArray];
    }

    public HashMap() {
        tamanhoArray = 31;
        this.entries = new Entry[31];
    }
    
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
    
    @Override
    public int size(){
        return size;
    }
    
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    
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
    
    private boolean positionIsEmpty(int pos){
        return entries[pos] == null || entries[pos].equals(EMPTY);
    }
    
    private double loadFactor(){
        return size / (double) tamanhoArray;
    }
    
    private void resize() throws DadoDuplicadoException{
        Entry[] temp = entries;
        tamanhoArray = proximoPrimo(tamanhoArray * 2);
        entries = new Entry[tamanhoArray];
        
        for(Entry e: temp){
            if(e != null && !e.equals(EMPTY))
                put(e.getKey(), e.getValue());
        }
    }
    
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
