package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Cidade;
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

public class FXMLAnchorPaneSelecionarCidadeController implements Initializable {

    @FXML
    private TableView<Cidade> tableViewCidades;
    @FXML
    private TableColumn<Cidade, String> tableColumnCodigo;
    @FXML
    private TableColumn<Cidade, String> tableColumnCidade;
    @FXML
    private TableColumn<Cidade, String> tableColumnPopulacao;
    @FXML
    private TableColumn<Cidade, String> tableColumnArea;
    @FXML
    private TableColumn<Cidade, String> tableColumnLatitude;
    @FXML
    private TableColumn<Cidade, String> tableColumnLongitude;

    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    private Cidade cidade = null;

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewCidades.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> cidade = newValue);
    }

    public void carregarTableViewCidades() {
        Iterator it = controller.iteratorPontos();
        LinkedList<Cidade> cidades = new LinkedList<>();

        while (it.hasNext()) {
            Ponto ponto = (Ponto) ((Vertex) it.next()).getData();

            if (ponto instanceof Cidade) {
                cidades.add((Cidade) ponto);
            }
        }
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        tableColumnCodigo.setStyle("-fx-alignment: CENTER;");
        tableColumnCidade.setCellValueFactory(new PropertyValueFactory("nome"));
        tableColumnCidade.setStyle("-fx-alignment: CENTER;");
        tableColumnPopulacao.setCellValueFactory(new PropertyValueFactory("populacao"));
        tableColumnPopulacao.setStyle("-fx-alignment: CENTER;");
        tableColumnLatitude.setCellValueFactory(new PropertyValueFactory("latitude"));
        tableColumnLatitude.setStyle("-fx-alignment: CENTER;");
        tableColumnLongitude.setCellValueFactory(new PropertyValueFactory("longitude"));
        tableColumnLongitude.setStyle("-fx-alignment: CENTER;");
        tableColumnArea.setCellValueFactory(new PropertyValueFactory("area"));
        tableColumnArea.setStyle("-fx-alignment: CENTER;");

        ObservableList<Cidade> observableListCidades = FXCollections.observableArrayList(cidades);
        tableViewCidades.setItems(observableListCidades);
    }

    @FXML
    void cancelarEdicaoCidade(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }

    @FXML
    void editarCidadeSelecionada(ActionEvent event) throws IOException {
        if(cidade != null){
            controllerTela.carregarAnchorPaneAdicionarCidade(cidade);
        }
    }
}
