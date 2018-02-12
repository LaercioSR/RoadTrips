package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import br.uefs.ecomp.RoadTrips.model.Ponto;
import br.uefs.ecomp.RoadTrips.util.MaskTextField;
import br.uefs.ecomp.RoadTrips.util.Vertex;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe controla a tela de adicão de rota ({@link br.uefs.ecomp.RoadTrips.util.Edge aresta}).
 */
public class FXMLAnchorPaneAdicionarRotasController implements Initializable {
    @FXML
    private ChoiceBox<Ponto> choiceBoxPontoA;
    @FXML
    private ChoiceBox<Ponto> choiceBoxPontoB;
    @FXML
    private TextField textFieldDistancia;
    @FXML
    private Label labelMensagemErro;
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    

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
        MaskTextField.maskNumeroReal(textFieldDistancia);
    }   
    
    /**
     * Método carrega os choiceBoxs para seleção dos pontos da aresta.
     */
    public void carregarChoiceBox() {
        LinkedList pontos = new LinkedList();
        Iterator it = controller.iteratorPontos();
        
        while(it.hasNext()){
            Ponto ponto = (Ponto) ((Vertex) it.next()).getData();
            pontos.add(ponto);
        }
        
        choiceBoxPontoA.setItems(FXCollections.observableArrayList(pontos));
        choiceBoxPontoB.setItems(FXCollections.observableArrayList(pontos));
    }
        
    /**
     * Método disparado por um ActionEvent que salva a rota. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de adição rota não consiga ser carregada.
     */
    @FXML
    void salvarRota(ActionEvent event) throws IOException {
        Ponto pontoA = choiceBoxPontoA.getValue();
        Ponto pontoB = choiceBoxPontoB.getValue();
        
        if(pontoA != null && pontoB != null && !textFieldDistancia.getText().equals("")){
            try {
                controller.adicionarRota(pontoA, pontoB, Double.parseDouble(textFieldDistancia.getText()));
            } catch (DadoNaoEncontradoException | DadoDuplicadoException ex) { }
            
            controllerTela.carregarAnchorPaneAdicionarRotas();
        } else {
            labelMensagemErro.setText("Erro, algum campo não informado");
        }
    }

    /**
     * Método disparado por um ActionEvent que cancela o salvamento da rota. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de minhas viagens não consiga ser carregada.
     */
    @FXML
    void cancelarSalvamentoRota(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }
}
