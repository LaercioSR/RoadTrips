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

    /**
     * Constroi uma interseção com os dados passsados.
     * @param tipoIntersecao Tipo da interseção.
     * @param codigo Código da interseção.
     * @param nome Nome da cidade.
     * @param latitude Latitude da cidade.
     * @param longitude Longitude da cidade.
     */
    public Intersecao(TipoIntersecao tipoIntersecao, int codigo, String nome, double latitude, double longitude) {
        super(codigo, nome, latitude, longitude);
        this.tipoIntersecao = tipoIntersecao;
    }

    /**
     * Método que retorna o nome do tipo da interseção.
     * @return Nome do tipo da interseção.
     */
    public String getTipo() {
        return tipoIntersecao.name();
    }

    /**
     * Método retorna o tipo da interseção.
     * @return Tipo da interseção.
     */
    public TipoIntersecao getTipoIntersecao() {
        return tipoIntersecao;
    }
}
