package br.uefs.ecomp.RoadTrips.model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe {@code Viagem} representa uma viagem que usuário cadastrou, possuindo 
 * todas as paradas e datas dessa viagem.
 * @see Parada
 */
public class Viagem {
    private String nome;
    private LinkedList<CidadeViagem> cidadesViagem;

    public Viagem(String nome) {
        this.nome = nome;
        cidadesViagem = new LinkedList();
    }

    public String getNome() {
        return nome;
    }
    
    public void addCidade(Cidade cidade, LocalDate dataChegada, LocalDate dataPartida) {
        cidadesViagem.add(new CidadeViagem(cidade, dataChegada, dataPartida));
    }
    
    public Iterator iteratorCidadesViagem() {
        return cidadesViagem.descendingIterator();
    }

    public class CidadeViagem{
        private Cidade cidade;
        private LocalDate dataChegada;
        private LocalDate dataPartida;

        public CidadeViagem(Cidade cidade, LocalDate dataChegada, LocalDate dataPartida) {
            this.cidade = cidade;
            this.dataChegada = dataChegada;
            this.dataPartida = dataPartida;
        }

        public Cidade getCidade() {
            return cidade;
        }

        public LocalDate getDataChegada() {
            return dataChegada;
        }

        public LocalDate getDataPartida() {
            return dataPartida;
        }

        public void setCidade(Cidade cidade) {
            this.cidade = cidade;
        }

        public void setDataChegada(LocalDate dataChegada) {
            this.dataChegada = dataChegada;
        }

        public void setDataPartida(LocalDate dataPartida) {
            this.dataPartida = dataPartida;
        }
    }
}
