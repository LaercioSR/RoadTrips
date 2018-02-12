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

/**
 * Classe controla a tela de seleção de uma cidade, para edição de dados.
 */
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

    /**
     * Método setta o {@link br.uefs.ecomp.RoadTrips.controller.RoadTripsController 
     * controller} principal da aplicação.
     * @param controller Controller principal da aplicação.
     */
    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    /**
     * Método setta o {@link br.uefs.ecomp.RoadTrips.controller.FXMLTelaInicialController 
     * controller da tela principal} da aplicação, que será usada para trocar a tela do sistema.
     * @param controllerTela Controller da tela principal da aplicação.
     */
    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    /**
     * Método inicializa os dados do FXML.
     * @param url Paramêtro padrão do JAVA.
     * @param rb Paramêtro padrão do JAVA.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewCidades.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> cidade = newValue);
    }

    /**
     * Método carrega a TableView com as cidades cadastradas no sistema.
     */
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

    /**
     * Método disparado por um ActionEvent que cancela a seleção de cidade. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de minhas viagens não consiga ser carregada.
     */
    @FXML
    void cancelarEdicaoCidade(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }

    /**
     * Método disparado por um ActionEvent que carrega a edição da cidade selecionada. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de adição de cidade não consiga ser carregada.
     */
    @FXML
    void editarCidadeSelecionada(ActionEvent event) throws IOException {
        if(cidade != null){
            controllerTela.carregarAnchorPaneAdicionarCidade(cidade);
        }
    }
}
