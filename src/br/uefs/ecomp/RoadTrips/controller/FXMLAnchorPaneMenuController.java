package br.uefs.ecomp.RoadTrips.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FXMLAnchorPaneMenuController implements Initializable {
        
    private FXMLTelaInicialController controller;

    
    public void setController(FXMLTelaInicialController controller) {
        this.controller = controller;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    void mostrarTelaMinhasViagens(ActionEvent event) throws IOException {
        controller.carregarAnchorPaneMinhasViagens();
    }

    @FXML
    void mostrarTelaNovaViagem(ActionEvent event) throws IOException {
        controller.carregarAnchorPaneNovaViagem();
    }
    
    @FXML
    void mostrarTelaAdicicaoCidade(ActionEvent event) throws IOException {
        controller.carregarAnchorPaneAdicionarCidade();
    }
    
    @FXML
    void mostrarTelaSelecaoCidade(ActionEvent event) throws IOException {
        controller.carregarAnchorPaneSelecionarCidade();
    }

    @FXML
    void mostrarTelaAdicionarIntersecao(ActionEvent event) throws IOException {
        controller.carregarAnchorPaneAdicionarIntersecao();
    }

    @FXML
    void mostrarTelaSelecionarIntersecao(ActionEvent event) throws IOException {
        controller.carregarAnchorPaneSelecionarIntersecao();
    }

    @FXML
    void mostrarTelaCadastrarEstabelecimento(ActionEvent event) throws IOException {
        controller.carregarAnchorPaneCadastrarEstabelecimento();
    }

    @FXML
    void mostrarTelaSelecionarEstabelecimento(ActionEvent event) throws IOException {
        controller.carregarAnchorPaneSelecionarEstabelecimento();
    }

    @FXML
    void mostrarTelaAdicionarRotas(ActionEvent event) throws IOException {
        controller.carregarAnchorPaneAdicionarRotas();
    }

    @FXML
    void mostrarTelaPesquisarUsuario(ActionEvent event) throws IOException {
        controller.carregarAnchorPanePesquisarUsuario();
    }
}
