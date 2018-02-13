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

    
    /**
     * Constroi uma cidade com os dados passados.
     * @param nome Nome da cidade.
     * @param codigo Código da cidade.
     * @param area Área da cidade.
     * @param populacao População da cidade.
     * @param descricao Descrição da cidade.
     * @param latitude Latitude da cidade.
     * @param longitude Longitude da cidade.
     * @param imagens Lista com as imagens da cidade.
     */
    public Cidade(String nome, int codigo, double area, int populacao, String descricao, double latitude, 
                  double longitude, LinkedList<Image> imagens) {
        super(codigo, nome, latitude, longitude);
        this.area = area;
        this.populacao = populacao;
        this.descricao = descricao;
        this.imagens = imagens;
        lugaresComer = new HashTable();
    }
    
    /**
     * Constroi uma cidade com os dados passados, usado para leitura de arquivos de texto.
     * @param nome Nome da cidade.
     * @param codigo Código da cidade.
     * @param latitude Latitude da cidade.
     * @param longitude Longitude da cidade.
     */
    public Cidade(String nome, int codigo, double latitude, double longitude){
        super(codigo, nome, latitude, longitude);
    }
    
    /**
     * Constroi uma cidade com os dados passados, usado para buscar a cidade no HashTable.
     * @param nome Nome da cidade.
     */
    public Cidade(String nome){
        super(0, nome, 0.0, 0.0);
    }

    /**
     * Método que retorna área da cidade.
     * @return Área da cidade.
     */
    public double getArea() {
        return area;
    }

    /**
     * Método que retorna a população da cidade.
     * @return População da cidade.
     */
    public int getPopulacao() {
        return populacao;
    }

    /**
     * Método que retorna a descrição da cidade.
     * @return Descrição da cidade.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método que retorna uma lista de imagens da cidade.
     * @return Lista de imagens da cidade.
     */
    public LinkedList<Image> getImagens() {
        return imagens;
    }

    /**
     * Método que modifica a área da cidade.
     * @param area Nova área da cidade.
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Método que modifica a população da cidade.
     * @param populacao Nova população da cidade.
     */
    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    /**
     * Método que modifica a descrição da cidade.
     * @param descricao Nova descrição da cidade.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * Método que adiciona um Lugar para se Comer da cidade.
     * @param estabelecimento Estabelecimento que é um lugar de comer.
     * @throws DadoDuplicadoException Caso o estabelecimento já esteja cadastrado.
     */
    public void addLugarComer(Estabelecimento estabelecimento) throws DadoDuplicadoException {
        lugaresComer.put(estabelecimento);
    }
    
    /**
     * Método que retorna o iterador com todos estabelecimentos da cidade.
     * @return Iterator de todos estabelecimentos da cidade.
     */
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
