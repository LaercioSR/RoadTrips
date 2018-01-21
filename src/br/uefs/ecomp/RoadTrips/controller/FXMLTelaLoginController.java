package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.view.RoadTripsMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLTelaLoginController implements Initializable {
    
    @FXML private Label labelMensagemErro;
    @FXML private AnchorPane anchorPane;
    @FXML private PasswordField passwordFieldSenha;
    @FXML private Button buttonCadastrar;
    @FXML private Button buttonEntrar;
    @FXML private TextField textFieldUsuario;
        
    private Stage loginStage;
    private RoadTripsMain application;
    private RoadTripsController controller;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setApplication(RoadTripsMain application) {
        this.application = application;
    }

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    @FXML
    void processarLogin(ActionEvent event) throws IOException {
        application.mostrarTelaInicial();
        //RoadTripsMain.mostrarTelaInicial();
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) throws IOException {
        application.mostrarTelaCadastro();
        //RoadTripsMain.mostrarTelaCadastro();
    }
}
