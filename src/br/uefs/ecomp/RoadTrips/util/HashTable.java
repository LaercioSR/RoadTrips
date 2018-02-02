package br.uefs.ecomp.RoadTrips.util;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable implements IHashTable {
    private final double LOAD_FACTOR = 0.5;
    private final Object EMPTY = new Object();
    
    Object[] datas;
    private int tamanhoArray;
    private int size = 0;
    
    public HashTable(){
        tamanhoArray = 31;
        this.datas = new Object[31];
    }
    
    public void HashTable(int tamanhoArray){
        this.tamanhoArray = proximoPrimo(tamanhoArray - 1);
        this.datas = new Object[this.tamanhoArray];
    }

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
    
    private int encontrarPosicao(Object e){
        int pos = e.hashCode() % tamanhoArray;
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
    
    private boolean positionIsEmpty(int pos){
        return datas[pos] == null || datas[pos].equals(EMPTY);
    }
    
    private double loadFactor(){
        return size / (double) tamanhoArray;
    }
    
    private void resize() throws DadoDuplicadoException{
        Object[] temp = datas;
        tamanhoArray = proximoPrimo(tamanhoArray * 2);
        datas = new Object[tamanhoArray];
        
        for(Object e: temp){
            if(e != null && !e.equals(EMPTY))
                put(e);
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
        
        for(Object a: datas){
            if(a != null && a != EMPTY){
                linkedList.add(a);
            }
        }
        
        return linkedList.descendingIterator();
    }
}
