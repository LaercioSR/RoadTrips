package br.uefs.ecomp.RoadTrips.model;

import br.uefs.ecomp.RoadTrips.util.HashTable;
import java.util.LinkedList;
import javafx.scene.image.Image;

/**
 * Esta classe implementa o comportamento de uma cidade.
 * @see Ponto
 */
public class Cidade {
    private String nome;
    private double area;
    private int populacao;
    private String descricao;
    private double latitude;
    private double longitude;
    private HashTable lugaresComer;
    private LinkedList<Image> imagens;

    public Cidade(String nome, double area, int populacao, String descricao, double latitude, double longitude) {
        this.nome = nome;
        this.area = area;
        this.populacao = populacao;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imagens = new LinkedList<>();
    }
    
    
}
