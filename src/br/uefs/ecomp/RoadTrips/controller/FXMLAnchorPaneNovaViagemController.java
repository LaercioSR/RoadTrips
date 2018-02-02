package br.uefs.ecomp.RoadTrips.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class FXMLAnchorPaneNovaViagemController implements Initializable {
    @FXML
    private Label labelTitulo;
    @FXML
    private TabPane tabPaneViagem;
    
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
        
    }       

    @FXML
    void adicionarCidade(ActionEvent event) {

    } 
    
    @FXML
    void salvarViagem(ActionEvent event) {

    }

    @FXML
    void cancelarSalvamentoViagem(ActionEvent event) {

    }
    
}
