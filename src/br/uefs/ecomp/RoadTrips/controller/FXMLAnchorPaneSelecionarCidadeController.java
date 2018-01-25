package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Cidade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLAnchorPaneSelecionarCidadeController implements Initializable {
    @FXML
    private TableView<Cidade> tableViewCidades;
    @FXML
    private TableColumn<Cidade, String> tableColumnPopulacao;
    @FXML
    private TableColumn<Cidade, String> tableColumnLatitude;
    @FXML
    private TableColumn<Cidade, String> tableColumnArea;
    @FXML
    private TableColumn<Cidade, String> tableColumnLongitude;
    @FXML
    private TableColumn<Cidade, String> tableColumnCidade;
    
    ObservableList<Cidade> observableListCidades;
    private FXMLTelaInicialController controllerTela;

    
    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewCidades();
    }    
    
    private void carregarTableViewCidades() {
        tableColumnCidade.setCellValueFactory(new PropertyValueFactory("nome"));
        tableColumnPopulacao.setCellValueFactory(new PropertyValueFactory("populacao"));
        tableColumnLatitude.setCellValueFactory(new PropertyValueFactory("latitude"));
        tableColumnLongitude.setCellValueFactory(new PropertyValueFactory("longitude"));
        tableColumnArea.setCellValueFactory(new PropertyValueFactory("area"));
        
        
    }
}
