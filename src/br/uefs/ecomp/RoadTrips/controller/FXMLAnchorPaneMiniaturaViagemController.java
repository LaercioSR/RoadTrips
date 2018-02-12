package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Viagem;
import br.uefs.ecomp.RoadTrips.model.Parada;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Classe controla a telinha (ou miniatura) da exibição de uma viagem.
 */
public class FXMLAnchorPaneMiniaturaViagemController implements Initializable {
    @FXML
    private ImageView imageViewMiniatura;
    @FXML
    private Label labelNomeViagem;
    @FXML
    private Label labelCidadesViagem;
    
    private Viagem viagem;
    private AnchorPane anchorPane;
    

    /**
     * Método retorna a viagem da miniatura.
     * @return Viagem da miniatura.
     */
    public Viagem getViagem() {
        return viagem;
    }

    /**
     * Método retorna o AnchorPane da miniatura.
     * @return AnchorPane da miniatura
     */
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    /**
     * Método define a viagem da miniatura.
     * @param viagem Viagem da miniatura.
     */
    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    /**
     * Método define o AnchorPane da miniatura.
     * @param anchorPane AnchorPane da miniatura.
     */
    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    /**
     * Método inicializa os dados do FXML.
     * @param url Paramêtro padrão do JAVA.
     * @param rb Paramêtro padrão do JAVA.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageViewMiniatura.setImage(new Image("/br/uefs/ecomp/RoadTrips/imagens/viagem.jpg"));
    }
    
    /**
     * Método carrega a miniatura com as informações da viagem.
     */
    public void carregar() {
        String cidadesViagem = "";
        Iterator itCidades = viagem.iteratorParadas();
        
        if(itCidades.hasNext()){
            Parada cidade = (Parada) itCidades.next();
            cidadesViagem += cidade.getCidade().getNome(); 
        }
        while(itCidades.hasNext()){
            Parada cidade = (Parada) itCidades.next();
            cidadesViagem += " - " + cidade.getCidade().getNome();
        }
        
        labelNomeViagem.setText(viagem.getNome());
        labelCidadesViagem.setText(cidadesViagem);
    }
    
    /**
     * Método verifica se um Object é igual a esse controller.
     * @param o Object a ser comparado.
     * @return True se são iguais (caso tenham a mesma anchorPane).
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof FXMLAnchorPaneMiniaturaViagemController){
            return anchorPane.equals(((FXMLAnchorPaneMiniaturaViagemController)o).getAnchorPane());
        }
        return false;
    }
}
