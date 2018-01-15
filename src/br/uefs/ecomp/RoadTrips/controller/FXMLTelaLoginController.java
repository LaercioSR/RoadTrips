package br.uefs.ecomp.RoadTrips.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLTelaLoginController implements Initializable {
    @FXML
    private Label labelMensagemErro;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button buttonCadastrar;

    @FXML
    private Button buttonEntrar;
    
    private Stage loginStage;
    private boolean buttonClicked = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean isButtonClicked() {
        return buttonClicked;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    @FXML
    void ProcessarLogin(ActionEvent event) {
        buttonClicked = true;
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) throws IOException {
        /*
        showFXMLTelaCadastroUsuario();
        AnchorPane a = (AnchorPane) FXMLLoader.load(FXMLTelaCadastroUsuarioController.class.getResource("/br/uefs/ecomp/RoadTrips/view/FXMLTelaCadastroUsuario.fxml"));
        anchorPane.getChildren().setAll(a);
        */
        buttonClicked = true;
    }
    
    private void showFXMLTelaCadastroUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLTelaCadastroUsuarioController.class.getResource("/br/uefs/ecomp/RoadTrips/view/FXMLTelaCadastroUsuario.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage cadastroStage = new Stage();
        cadastroStage.setTitle("Cadastro");
        cadastroStage.setResizable(false);
        
        Scene scene = new Scene(page);
        cadastroStage.setScene(scene);
        
        cadastroStage.showAndWait();
    }
}
