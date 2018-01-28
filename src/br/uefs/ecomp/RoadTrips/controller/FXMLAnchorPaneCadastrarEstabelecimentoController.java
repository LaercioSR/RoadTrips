package br.uefs.ecomp.RoadTrips.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FXMLAnchorPaneCadastrarEstabelecimentoController implements Initializable {
    @FXML
    private TextField textFieldNome;
    @FXML
    private ChoiceBox<?> choiceBoxTipo;
    @FXML
    private ChoiceBox<?> choiceBoxCidade;
    @FXML
    private ListView<ImageView> listViewImagens;
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
    @FXML
    void adicionarImagem(ActionEvent event) {

    }
    
    @FXML
    void salvarEstabelecimento(ActionEvent event) {

    }

    @FXML
    void cancelarSalvamentoEstabelecimento(ActionEvent event) {

    }
}
