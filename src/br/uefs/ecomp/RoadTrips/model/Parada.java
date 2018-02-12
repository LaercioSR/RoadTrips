package br.uefs.ecomp.RoadTrips.model;

import java.time.LocalDate;

/**
 * Classe {@code Parad} representa a parada de uma viagem, contendo a cidade e as 
 * datas de chegada e saída dessa parada.
 * @see Viagem
 */
public class Parada {
    private Cidade cidade;
    private LocalDate dataChegada;
    private LocalDate dataPartida;

    /**
     * Controi uma parada com os dados passados.
     * @param cidade
     * @param dataChegada
     * @param dataPartida 
     */
    public Parada(Cidade cidade, LocalDate dataChegada, LocalDate dataPartida) {
        this.cidade = cidade;
        this.dataChegada = dataChegada;
        this.dataPartida = dataPartida;
    }

    /**
     * Método retorna a cidade da parada.
     * @return Cidade da parada
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * Método retorna a data de chegada da parada.
     * @return Data de chegada.
     */
    public LocalDate getDataChegada() {
        return dataChegada;
    }

    /**
     * Método retorna a data de partida da parada.
     * @return Data de partida.
     */
    public LocalDate getDataPartida() {
        return dataPartida;
    }

    /**
     * Método modifica a cidade da parada.
     * @param cidade Nove cidade da parada.
     */
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    /**
     * Método modifica a data de chegada da parada.
     * @param dataChegada Nova data de chegada.
     */
    public void setDataChegada(LocalDate dataChegada) {
        this.dataChegada = dataChegada;
    }

    /**
     * Método modifica a data de partida da parada.
     * @param dataPartida Nova data de partida.
     */
    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }
}
