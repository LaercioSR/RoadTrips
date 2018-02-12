package br.uefs.ecomp.RoadTrips.model;

import java.util.LinkedList;
import javafx.scene.image.Image;

/**
 * Classe {@code Estabelecimento} representará um certo tipo de estabelecimento 
 * relevante de uma cidade, por enquanto, será usada para representar apenas 
 * lugares onde são vendido comidas (Como restaurantes, bares e fast-foods).
 * @see TipoEstabelecimento
 */
public class Estabelecimento {
    private String nome;
    private TipoEstabelecimento tipoEstabelecimento;
    private Cidade cidade;
    private LinkedList<Image> imagens;

    /**
     * Constroi o estabelecimento com dados passados.
     * @param nome Nome do estabelecimento.
     * @param tipoEstabelecimento Tipo do estabelecimento.
     * @param cidade Cidade do estabelecimento.
     * @param imagens Lista com as imagens do estabelecimento.
     */
    public Estabelecimento(String nome, TipoEstabelecimento tipoEstabelecimento, Cidade cidade, LinkedList<Image> imagens) {
        this.nome = nome;
        this.tipoEstabelecimento = tipoEstabelecimento;
        this.cidade = cidade;
        this.imagens = imagens;
    }

    /**
     * Método retorna o nome do estabelecimento.
     * @return Nome do estabelecimento.
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Método que retorna o nome da cidade do estabelecimento.
     * @return Nome da cidade do estabelecimento.
     */
    public String getNomeCidade() {
        return cidade.getNome();
    }
    
    /**
     * Método retorna o nome do tipo do estabelecimento.
     * @return Nome do tipo do estabelecimento.
     */
    public String getTipo() {
        return tipoEstabelecimento.name();
    }

    /**
     * Método retorna o tipo do estabelecimento.
     * @return Tipo do estabelecimento.
     */
    public TipoEstabelecimento getTipoEstabelecimento() {
        return tipoEstabelecimento;
    }

    /**
     * Método retorna a cidade do estabelecimento.
     * @return Cidade do estabelecimento.
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * Método que retorna a lista de imagens do estabelecimento.
     * @return Lista de imagens do estabelecimento.
     */
    public LinkedList<Image> getImagens() {
        return imagens;
    }

    /**
     * Método que modifica o nome do estabelecimento.
     * @param nome Novo nome do estabelecimento.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
