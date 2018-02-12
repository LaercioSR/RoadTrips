package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Intersecao;
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
 * Classe controla a tela de seleção de uma interseção, para edição de dados.
 */
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
    private RoadTripsController controller;
    private Intersecao intersecao = null;
    

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
        tableViewIntersecao.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> intersecao = newValue);
    } 
    
    /**
     * Método carrega a TableView com as interseções cadastradas no sistema.
     */
    public void carregarTableViewIntersecao() {
        Iterator it = controller.iteratorPontos();
        LinkedList<Intersecao> intersecoes = new LinkedList<>();

        while (it.hasNext()) {
            Ponto ponto = (Ponto) ((Vertex) it.next()).getData();

            if (ponto instanceof Intersecao) {
                intersecoes.add((Intersecao) ponto);
            }
        }
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        tableColumnCodigo.setStyle("-fx-alignment: CENTER;");
        tableColumnNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tableColumnNome.setStyle("-fx-alignment: CENTER;");
        tableColumnTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tableColumnTipo.setStyle("-fx-alignment: CENTER;");
        tableColumnLatitude.setCellValueFactory(new PropertyValueFactory("latitude"));
        tableColumnLatitude.setStyle("-fx-alignment: CENTER;");
        tableColumnLongitude.setCellValueFactory(new PropertyValueFactory("longitude"));
        tableColumnLongitude.setStyle("-fx-alignment: CENTER;");

        ObservableList<Intersecao> observableListIntersecao = FXCollections.observableArrayList(intersecoes);
        tableViewIntersecao.setItems(observableListIntersecao);
    }
        
    /**
     * Método disparado por um ActionEvent que cancela a seleção de interseção. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de minhas viagens não consiga ser carregada.
     */
    @FXML
    void editarIntersecaoSelecionada(ActionEvent event) throws IOException {
        if(intersecao != null){
            controllerTela.carregarAnchorPaneAdicionarIntersecao(intersecao);
        }
    }

    /**
     * Método disparado por um ActionEvent que carrega a edição da interseção selecionada. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de adição de interseção não consiga ser carregada.
     */
    @FXML
    void cancelarEdicaoIntersecao(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }
}
