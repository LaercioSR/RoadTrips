package br.uefs.ecomp.RoadTrips.model;

import br.uefs.ecomp.RoadTrips.util.HashTable;
import java.util.LinkedList;
import javafx.scene.image.Image;

/**
 * Esta classe implementa o comportamento de uma cidade.
 * @see Ponto
 */
public class Cidade extends Ponto {
    private String nome;
    private double area;
    private int populacao;
    private String descricao;
    private HashTable lugaresComer;
    private LinkedList<Image> imagens;

    
    public Cidade(String nome, int codigo, double area, int populacao, String descricao, double latitude, 
                  double longitude, LinkedList<Image> imagens) {
        super(codigo, longitude, latitude);
        this.nome = nome;
        this.area = area;
        this.populacao = populacao;
        this.descricao = descricao;
        this.imagens = imagens;
    }
    
    public String getNome() {
        return nome;
    }

    public double getArea() {
        return area;
    }

    public int getPopulacao() {
        return populacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public LinkedList<Image> getImagens() {
        return imagens;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
