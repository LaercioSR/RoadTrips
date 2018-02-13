package br.uefs.ecomp.RoadTrips.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe {@code Viagem} representa uma viagem que usuário cadastrou, possuindo 
 * todas as paradas e datas dessa viagem.
 * @see Parada
 */
public class Viagem implements Serializable {
    private String nome;
    private LinkedList<Parada> paradas;

    /**
     * Constroi uma viagem com nome passado.
     * @param nome Nome da viagem.
     */
    public Viagem(String nome) {
        this.nome = nome;
        paradas = new LinkedList();
    }

    /**
     * Método retorna o nome da viagem.
     * @return Nome da viagem.
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Método adiciona uma parada a viagem.
     * @param cidade Cidade da parada.
     * @param dataChegada Data de chegada da parada.
     * @param dataPartida Data de partida da parada.
     */
    public void addParada(Cidade cidade, LocalDate dataChegada, LocalDate dataPartida) {
        paradas.add(new Parada(cidade, dataChegada, dataPartida));
    }
    
    /**
     * Método retorna o iterador das paradas da viagem.
     * @return Iterator das paradas.
     */
    public Iterator iteratorParadas() {
        return paradas.descendingIterator();
    }
}
