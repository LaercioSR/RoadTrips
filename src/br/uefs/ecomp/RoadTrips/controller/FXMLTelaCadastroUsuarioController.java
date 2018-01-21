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

public class FXMLTelaCadastroUsuarioController implements Initializable {
    
    @FXML private PasswordField passwordSenha;
    @FXML private Label labelMensagemErro;
    @FXML private TextField textFieldEmail;
    @FXML private Button buttonCancelar;
    @FXML private Button buttonCadastrar;
    @FXML private TextField textFieldUsuario;

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
    
    @FXML
    void cancelarCadastro(ActionEvent event) throws IOException {
        application.mostrarTelaLogin();
        //RoadTripsMain.mostrarTelaLogin();
    }

    @FXML
    void efetuarCadastro(ActionEvent event) {

    }
}
