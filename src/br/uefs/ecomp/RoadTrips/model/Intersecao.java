package br.uefs.ecomp.RoadTrips.model;

/**
 * Classe implementa o comportamento de interseção entre duas ou mais cidades 
 * em um mapa, esse ponto possui um tipo, podendo ser um semáforo, cruzamente, 
 * etc.
 * @see Ponto
 * @see TipoIntersecao
 */
public class Intersecao extends Ponto {
    private TipoIntersecao tipoIntersecao;

    public Intersecao(TipoIntersecao tipoIntersecao, int codigo, double longitude, double latitude) {
        super(codigo, longitude, latitude);
        this.tipoIntersecao = tipoIntersecao;
    }
}
