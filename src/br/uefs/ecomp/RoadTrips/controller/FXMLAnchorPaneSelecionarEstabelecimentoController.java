package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.model.Estabelecimento;
import br.uefs.ecomp.RoadTrips.model.Ponto;
import br.uefs.ecomp.RoadTrips.util.Vertex;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private RoadTripsController controller;
    private Estabelecimento estabelecimento;
    

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewEstabelecimento.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> estabelecimento = newValue);
    }  
    
    public void carregarTableViewEstabelecimentos() {
        Iterator it = controller.iteratorPontos();
        LinkedList<Estabelecimento> estabelecimentos = new LinkedList<>();
        
        while(it.hasNext()){
            Ponto ponto = (Ponto) ((Vertex) it.next()).getData();
            
            if(ponto instanceof Cidade){
                Cidade cidade = (Cidade) ponto;
                Iterator itEstabelecimentos = cidade.iteratorEstabelecimentos();
                
                while(itEstabelecimentos.hasNext()){
                    Estabelecimento estabelecimento = (Estabelecimento) itEstabelecimentos.next();
                    estabelecimentos.add(estabelecimento);
                }
            }
        }
        
        tableColumnNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tableColumnNome.setStyle("-fx-alignment: CENTER;");
        tableColumnTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tableColumnTipo.setStyle("-fx-alignment: CENTER;");
        tableColumnCidade.setCellValueFactory(new PropertyValueFactory("nomeCidade"));
        tableColumnCidade.setStyle("-fx-alignment: CENTER;");
        
        ObservableList<Estabelecimento> observableListEstabelecimentos = FXCollections.observableArrayList(estabelecimentos);
        tableViewEstabelecimento.setItems(observableListEstabelecimentos);
    }

    @FXML
    void cancelarEdicaoEstabelecimento(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }  
        
    @FXML
    void editarEstabelecimentoSelecionado(ActionEvent event) throws IOException {
        if(estabelecimento != null){
            controllerTela.carregarAnchorPaneCadastrarEstabelecimento(estabelecimento);
        }
    }
}
