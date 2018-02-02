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
    

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskTextField.maskNumeroReal(textFieldDistancia);
    }   
    
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

    @FXML
    void cancelarSalvamentoRota(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }
}
