package br.uefs.ecomp.RoadTrips.model;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.util.HashTable;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.scene.image.Image;

/**
 * Esta classe implementa o comportamento de uma cidade.
 * @see Ponto
 */
public class Cidade extends Ponto {
    private double area;
    private int populacao;
    private String descricao;
    private HashTable lugaresComer;
    private LinkedList<Image> imagens;

    
    public Cidade(String nome, int codigo, double area, int populacao, String descricao, double latitude, 
                  double longitude, LinkedList<Image> imagens) {
        super(codigo, nome, latitude, longitude);
        this.area = area;
        this.populacao = populacao;
        this.descricao = descricao;
        this.imagens = imagens;
        lugaresComer = new HashTable();
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

    public void setArea(double area) {
        this.area = area;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void addLugarComer(Estabelecimento estabelecimento) throws DadoDuplicadoException {
        lugaresComer.put(estabelecimento);
    }
    
    public Iterator iteratorEstabelecimentos() {
        LinkedList<Estabelecimento> estabelecimentos = new LinkedList<>();
        Iterator it;
        
        it = lugaresComer.iterator();
        while(it.hasNext()){
            estabelecimentos.add((Estabelecimento)it.next());
        }
        
        return estabelecimentos.descendingIterator();
    }
}
