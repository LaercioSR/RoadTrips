package br.uefs.ecomp.RoadTrips.model;

/**
 * Classe abstrata implementa os comportamentos de um ponto no mapa.
 * @see Cidade
 * @see Intersecao
 */
public abstract class Ponto {
    private int codigo;
    private double longitude;
    private double latitude;

    public Ponto(int codigo, double longitude, double latitude) {
        this.codigo = codigo;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
