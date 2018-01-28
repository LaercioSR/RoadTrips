package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Ponto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FXMLAnchorPaneAdicionarIntersecaoController implements Initializable {
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldLatitude;
    @FXML
    private TextField textFieldLongitude;
    @FXML
    private TextField textFieldDistancia;
    @FXML
    private ChoiceBox<?> choiceBoxTipo;
    @FXML
    private ChoiceBox<?> choiceBoxPontos;
    @FXML
    private TableView<Ponto> tableViewPontos;
    @FXML
    private TableColumn<Ponto, String> tableColumnNome;
    @FXML
    private TableColumn<Ponto, String> tableColumnTipo;
    @FXML
    private TableColumn<Ponto, Integer> tableColumnDistancia;

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
    void adicionarRota(ActionEvent event) {

    }
    
    @FXML
    void salvarIntersecao(ActionEvent event) {

    }

    @FXML
    void cancelarrSalvamentoIntersecao(ActionEvent event) {

    }
}
