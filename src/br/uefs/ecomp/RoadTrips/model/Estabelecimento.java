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

    public Estabelecimento(String nome, TipoEstabelecimento tipoEstabelecimento, Cidade cidade, LinkedList<Image> imagens) {
        this.nome = nome;
        this.tipoEstabelecimento = tipoEstabelecimento;
        this.cidade = cidade;
        this.imagens = imagens;
    }

    public String getNome() {
        return nome;
    }
    
    public String getNomeCidade() {
        return cidade.getNome();
    }
    
    public String getTipo() {
        return tipoEstabelecimento.name();
    }

    public TipoEstabelecimento getTipoEstabelecimento() {
        return tipoEstabelecimento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public LinkedList<Image> getImagens() {
        return imagens;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
