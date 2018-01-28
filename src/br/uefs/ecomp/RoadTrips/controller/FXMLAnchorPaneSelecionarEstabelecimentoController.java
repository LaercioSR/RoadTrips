package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Estabelecimento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FXMLAnchorPaneSelecionarEstabelecimentoController implements Initializable {
    @FXML
    private TableView<Estabelecimento> tableViewEstabelecimento;
    @FXML
    private TableColumn<Estabelecimento, String> tableColumnNome;
    @FXML
    private TableColumn<Estabelecimento, String> tableColumnTipo;
    @FXML
    private TableColumn<Estabelecimento, String> tableColumnCidade;
    
    private FXMLTelaInicialController controllerTela;
    

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    @FXML
    void cancelarEdicaoEstabelecimento(ActionEvent event) {

    }  
        
    @FXML
    void editarEstabelecimentoSelecionado(ActionEvent event) {

    }
}
