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

/**
 * Classe controla a tela de seleção de um estabelecimento, para edição de dados.
 */
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
        tableViewEstabelecimento.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> estabelecimento = newValue);
    }  
    
    /**
     * Método carrega a TableView com os estabelecimento cadastrados no sistema.
     */
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

    /**
     * Método disparado por um ActionEvent que cancela a seleção de estabelecimento. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de minhas viagens não consiga ser carregada.
     */
    @FXML
    void cancelarEdicaoEstabelecimento(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }  
        
    /**
     * Método disparado por um ActionEvent que carrega a edição do estabelecimento selecionado. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de cadastro de estabelecimento não consiga ser carregada.
     */
    @FXML
    void editarEstabelecimentoSelecionado(ActionEvent event) throws IOException {
        if(estabelecimento != null){
            controllerTela.carregarAnchorPaneCadastrarEstabelecimento(estabelecimento);
        }
    }
}
