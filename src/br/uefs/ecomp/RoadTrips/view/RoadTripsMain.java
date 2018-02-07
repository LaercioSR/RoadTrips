package br.uefs.ecomp.RoadTrips.view;

import br.uefs.ecomp.RoadTrips.controller.FXMLTelaCadastroUsuarioController;
import br.uefs.ecomp.RoadTrips.controller.FXMLTelaInicialController;
import br.uefs.ecomp.RoadTrips.controller.FXMLTelaLoginController;
import br.uefs.ecomp.RoadTrips.controller.RoadTripsController;
import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.model.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A classe {@code RoadTripsMain} é responsável por chamar e exibir as principais telas
 * do programa, sendo elas a de Login, Cadastro e Tela Principal.
 */

public class RoadTripsMain extends Application {
    
    static private Stage stage;
    private RoadTripsController controller;
     
    /**
     * Método carrega e exibe o Stage passado como paramêtro.
     * @param primaryStage Stage a ser carregado e exibido.
     * @throws IOException Caso o Stage passado não consiga ser carregado.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        controller = new RoadTripsController();
        
        
        try {
            controller.cadastrarUsuario("Laercio", "", "laercio.rios13@hotmail.com");
            controller.cadastrarUsuario("", "", "lesh.rios13@gmail.com");
        } catch (DadoDuplicadoException ex) {
            Logger.getLogger(RoadTripsMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        stage = primaryStage;
        
        mostrarTelaLogin();
        stage.getIcons().add(new Image("/br/uefs/ecomp/RoadTrips/imagens/iconRoadTrips.png"));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Metódo carrega e exibe a tela de login.
     * @throws IOException Caso a tela de login não consiga ser carregado.
     */
    public void mostrarTelaLogin() throws IOException {
        FXMLTelaLoginController controllerLogin = (FXMLTelaLoginController) trocarScene("FXMLTelaLogin.fxml");
        controllerLogin.setApplication(this);
        controllerLogin.setController(controller);
        
        stage.setTitle("Login - Road Trips");
    }
    
    /**
     * Método carrega e exibe a tela de cadastro.
     * @throws IOException Caso a tela de cadastro não consiga ser carregado.
     */
    public void mostrarTelaCadastro() throws IOException {
        FXMLTelaCadastroUsuarioController controllerCadastro = (FXMLTelaCadastroUsuarioController) trocarScene("FXMLTelaCadastroUsuario.fxml");
        controllerCadastro.setApplication(this);
        controllerCadastro.setController(controller);
        
        stage.setTitle("Login - Road Trips");
    }
    
    /**
     * Método carrega e exibe a tela principal do programa.
     * @param usuario Usuário que fez login.
     * @throws IOException Caso a tela principal não consiga ser carregado.
     */
    public void mostrarTelaInicial(Usuario usuario) throws IOException {
        FXMLTelaInicialController controllerTelaInicial = (FXMLTelaInicialController) trocarScene("FXMLTelaInicial.fxml");
        controllerTelaInicial.setApplication(this);
        controllerTelaInicial.setController(controller);
        controllerTelaInicial.setUsuario(usuario);
        controllerTelaInicial.carregarAnchorPaneMenu();
        controllerTelaInicial.carregarMenuButtonUsuario();
        
        stage.setTitle("Road Trips");
    }
    
    // Método que carrega as FXML's e retorna o controller da tela carregada.
    private Initializable trocarScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RoadTripsMain.class.getResource(fxml));
        
        Parent page = loader.load();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.centerOnScreen();
        
        return (Initializable) loader.getController();
    }
    
    /**
     * Método principal do projeto, responsável por iniciar a cascata de chamadas
     * de outros métodos.
     * @param args Paramêtro padrão do java.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
