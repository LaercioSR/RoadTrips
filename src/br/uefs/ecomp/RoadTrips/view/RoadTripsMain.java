package br.uefs.ecomp.RoadTrips.view;

import br.uefs.ecomp.RoadTrips.controller.FXMLTelaCadastroUsuarioController;
import br.uefs.ecomp.RoadTrips.controller.FXMLTelaInicialController;
import br.uefs.ecomp.RoadTrips.controller.FXMLTelaLoginController;
import br.uefs.ecomp.RoadTrips.controller.RoadTripsController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RoadTripsMain extends Application {
    
    static private Stage stage;
    private RoadTripsController controller;
     
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        
        mostrarTelaLogin();
        stage.setResizable(false);
        stage.show();
    }

    public void mostrarTelaLogin() throws IOException {
        FXMLTelaLoginController controllerLogin = (FXMLTelaLoginController) trocarScene("FXMLTelaLogin.fxml");
        controllerLogin.setApplication(this);
        controllerLogin.setController(controller);
        
        stage.setTitle("Login - Road Trips");
    }
    
    public void mostrarTelaCadastro() throws IOException {
        FXMLTelaCadastroUsuarioController controllerCadastro = (FXMLTelaCadastroUsuarioController) trocarScene("FXMLTelaCadastroUsuario.fxml");
        controllerCadastro.setApplication(this);
        controllerCadastro.setController(controller);
        
        stage.setTitle("Login - Road Trips");
    }
    
    public void mostrarTelaInicial() throws IOException {
        FXMLTelaInicialController controllerTelaInicial = (FXMLTelaInicialController) trocarScene("FXMLTelaInicial.fxml");
        controllerTelaInicial.setApplication(this);
        controllerTelaInicial.setController(controller);
        
        stage.setTitle("Road Trips");
    }
    
    private Initializable trocarScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RoadTripsMain.class.getResource(fxml));
        
        Parent page = loader.load();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.centerOnScreen();
        
        return (Initializable) loader.getController();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
