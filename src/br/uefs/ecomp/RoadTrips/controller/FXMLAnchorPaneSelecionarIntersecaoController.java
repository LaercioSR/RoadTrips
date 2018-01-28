package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Intersecao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FXMLAnchorPaneSelecionarIntersecaoController implements Initializable {
    @FXML
    private TableView<Intersecao> tableViewIntersecao;
    @FXML
    private TableColumn<Intersecao, Integer> tableColumnCodigo;
    @FXML
    private TableColumn<Intersecao, String> tableColumnNome;
    @FXML
    private TableColumn<Intersecao, String> tableColumnTipo;
    @FXML
    private TableColumn<Intersecao, Double> tableColumnLatitude;
    @FXML
    private TableColumn<Intersecao, Double> tableColumnLongitude;
    
    private FXMLTelaInicialController controllerTela;
    

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
    @FXML
    void editarIntersecaoSelecionada(ActionEvent event) {

    }

    @FXML
    void cancelarEdicaoIntersecao(ActionEvent event) {

    }
}
