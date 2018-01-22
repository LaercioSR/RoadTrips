package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.view.RoadTripsMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FXMLTelaCadastroUsuarioController implements Initializable {
    
    @FXML private Label labelMensagemErro;
    @FXML private Button buttonCancelar;
    @FXML private Button buttonCadastrar;
    @FXML private PasswordField passwordFieldSenha;
    @FXML private TextField textFieldEmail;
    @FXML private TextField textFieldUsuario;

    private RoadTripsMain application;
    private RoadTripsController controller;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldUsuario.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    efetuarCadastro(null);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLTelaCadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        passwordFieldSenha.setOnKeyPressed(textFieldUsuario.getOnKeyPressed());
        textFieldEmail.setOnKeyPressed(textFieldUsuario.getOnKeyPressed());
    }    
    public void setApplication(RoadTripsMain application) {
        textFieldUsuario.requestFocus();
        this.application = application;
    }

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }
    
    @FXML
    void cancelarCadastro(ActionEvent event) throws IOException {
        application.mostrarTelaLogin();
    }

    @FXML
    void efetuarCadastro(ActionEvent event) throws IOException {
        try {
            if(textFieldUsuario.getText().equals("") || passwordFieldSenha.getText().equals("") || textFieldEmail.getText().equals("")){
                labelMensagemErro.setText("Erro, algum campo não está preenchido");
            } else{
                controller.cadastrarUsuario(textFieldUsuario.getText(), passwordFieldSenha.getText(), textFieldEmail.getText());
                application.mostrarTelaLogin();
            }
        } catch (DadoDuplicadoException ex) {
            labelMensagemErro.setText("Usuário digitado não está disponível");
        }
    }
}
