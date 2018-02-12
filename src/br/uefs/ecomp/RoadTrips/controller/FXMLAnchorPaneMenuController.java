package br.uefs.ecomp.RoadTrips.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * Método controla o menu (tanto do ADMIN, quando do usuário comum) da tela 
 * principal, localizado no lado esquerdo da tela.
 */
public class FXMLAnchorPaneMenuController implements Initializable {
        
    private FXMLTelaInicialController controllerTela;

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
     * @param location Paramêtro padrão do JAVA.
     * @param resources Paramêtro padrão do JAVA.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Método carrega a tela de Minhas viagens.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaMinhasViagens(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }

    /**
     * Método carrega a tela de adição de uma nova viagem.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaNovaViagem(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneNovaViagem(null);
    }
    
    /**
     * Método carrega a tela de adição de cidade.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaAdicicaoCidade(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneAdicionarCidade(null);
    }
    
    /**
     * Método carrega a tela de seleção de cidade.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaSelecaoCidade(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneSelecionarCidade();
    }

    /**
     * Método carrega a tela de adição de interseção.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaAdicionarIntersecao(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneAdicionarIntersecao(null);
    }

    /**
     * Método carrega a tela de seleção de interseção.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaSelecionarIntersecao(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneSelecionarIntersecao();
    }

    /**
     * Método carrega a tela de Cadastro de estabelecimento.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaCadastrarEstabelecimento(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneCadastrarEstabelecimento(null);
    }

    /**
     * Método carrega a tela de seleção de estabelecimento.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaSelecionarEstabelecimento(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneSelecionarEstabelecimento();
    }

    /**
     * Método carrega a tela de adição de rota.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaAdicionarRotas(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneAdicionarRotas();
    }

    /**
     * Método carrega a tela de pesquisa de usuário.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    @FXML
    void mostrarTelaPesquisarUsuario(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPanePesquisarUsuario();
    }
}
