package br.uefs.ecomp.RoadTrips.model;

import java.io.Serializable;

/**
 * Classe abstrata implementa os comportamentos de um ponto no mapa.
 * @see Cidade
 * @see Intersecao
 */
public abstract class Ponto implements Serializable {
    private int codigo;
    private String nome;
    private double latitude;
    private double longitude;

    /**
     * Constroi um ponto com os dados passados.
     * @param codigo Código do ponto.
     * @param nome Nome do ponto.
     * @param latitude Latitude do ponto.
     * @param longitude Longitude do ponto.
     */
    public Ponto(int codigo, String nome, double latitude, double longitude) {
        this.codigo = codigo;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Método retorna o código do ponto.
     * @return Código do ponto.
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Método retorna o nome do ponto.
     * @return Nome do ponto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método retorna a longitude do ponto.
     * @return Longitude do ponto.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Método retorna a latitude do ponto.
     * @return Latutude do ponto.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Método modifica o nome do ponto.
     * @param nome Novo nome do ponto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Método retorna texto de impressão na tela da classe {@code Ponto}
     * @return Texto refente á {@code Ponto}
     */
    @Override
    public String toString() {
        return nome;
    }
    
    /**
     * Método compara se dois pontos são iguais pelo nome.
     * @param obj Objeto que será comparada.
     * @return True se os dois pontos são iguais.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ponto)
            return nome.equals(((Ponto) obj).getNome());
        return false;
    }
    
    /**
     * Método retorna o hashCode do ponto.
     * @return HashCode do ponto.
     */
    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
