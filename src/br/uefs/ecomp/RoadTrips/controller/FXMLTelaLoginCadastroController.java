package br.uefs.ecomp.RoadTrips.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLTelaLoginCadastroController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            carregarTelaLogin();
        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaLoginCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregarTelaLogin() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(FXMLTelaLoginController.class.getResource("/br/uefs/ecomp/RoadTrips/view/FXMLTelaLogin.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
}
