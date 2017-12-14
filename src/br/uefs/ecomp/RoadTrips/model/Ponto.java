package br.uefs.ecomp.RoadTrips.model;

/**
 * Classe abstrata implementa os comportamentos de um ponto no mapa.
 * @see Cidade
 * @see Intersecao
 */
public abstract class Ponto {
    private int codigo;
    private int longitude;
    private int latitude;

    public Ponto(int codigo, int longitude, int latitude) {
        this.codigo = codigo;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
