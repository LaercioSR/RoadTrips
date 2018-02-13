package br.uefs.ecomp.RoadTrips.model;

import java.io.Serializable;

/**
 * Enumeração representando os possíveis tipo de uma interseção.
 * @see Intersecao
 */
public enum TipoIntersecao implements Serializable {
    /**
     * Rótula.
     */
    rotula,
    /**
     * Cruzamento.
     */
    cruzamento, 
    /**
     * Semaforo.
     */
    semaforo;
}
