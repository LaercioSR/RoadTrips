package br.uefs.ecomp.RoadTrips.model;

/**
 * Classe abstrata implementa os comportamentos de um ponto no mapa.
 * @see Cidade
 * @see Intersecao
 */
public abstract class Ponto {
    private int codigo;
    private String nome;
    private double latitude;
    private double longitude;

    public Ponto(int codigo, String nome, double latitude, double longitude) {
        this.codigo = codigo;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public String getNome() {
        return nome;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    @Override
    public boolean equals(Object a) {
        if(a instanceof Ponto)
            return nome.equals(((Ponto) a).getNome());
        return false;
    }
}
