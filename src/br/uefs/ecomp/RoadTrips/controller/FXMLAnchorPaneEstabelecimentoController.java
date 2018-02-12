package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Estabelecimento;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe controla a telinha que exibe um estabelecimento.
 */
public class FXMLAnchorPaneEstabelecimentoController implements Initializable {
    @FXML
    private Label labelNome;
    @FXML
    private ListView<ImageView> listViewImagens;

    /**
     * Método inicializa os dados do FXML.
     * @param url Paramêtro padrão do JAVA.
     * @param rb Paramêtro padrão do JAVA.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listViewImagens.setOrientation(Orientation.HORIZONTAL);
    }    
    
    /**
     * Método carrega a tela com as informações do estabelecimento.
     * @param estabelecimento Estabelecimento para carregar as informações.
     */
    public void carregar(Estabelecimento estabelecimento) {
        labelNome.setText(estabelecimento.getNome());
        labelNome.setAlignment(Pos.CENTER);
        
        LinkedList<ImageView> imagens = new LinkedList<>();
        for(Image a: estabelecimento.getImagens()){
            ImageView image = new ImageView(a);
            image.setFitHeight(200);
            image.setFitWidth(280);
            imagens.add(image);
        }
        
        ObservableList<ImageView> itens = FXCollections.observableArrayList(imagens);
        listViewImagens.setItems(itens);
    }
}
