package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.util.MaskTextField;
import br.uefs.ecomp.RoadTrips.view.RoadTripsMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Classe controla a tela de cadastro de um novo usuário.
 */
public class FXMLTelaCadastroUsuarioController implements Initializable {
    
    @FXML 
    private Label labelMensagemErro;
    @FXML 
    private PasswordField passwordFieldSenha;
    @FXML 
    private TextField textFieldEmail;
    @FXML 
    private TextField textFieldUsuario;
    @FXML
    private ImageView imageViewLogo;

    private RoadTripsMain application;
    private RoadTripsController controller;

    
    /**
     * Método inicializa os dados do FXML.
     * @param url Paramêtro padrão do JAVA.
     * @param rb Paramêtro padrão do JAVA.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageViewLogo.setImage(new Image("/br/uefs/ecomp/RoadTrips/imagens/logoRoadTrips.png"));
        
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
        
        MaskTextField.maskEmail(textFieldEmail);
    } 
    
    /**
     * Método setta a classe {@link br.uefs.ecomp.RoadTrips.view.RoadTripsMain Main} 
     * da aplicação.
     * @param application Classe main da aplicação.
     */
    public void setApplication(RoadTripsMain application) {
        textFieldUsuario.requestFocus();
        this.application = application;
    }

    /**
     * Método setta o {@link br.uefs.ecomp.RoadTrips.controller.RoadTripsController 
     * controller} principal da aplicação.
     * @param controller Controller principal da aplicação.
     */
    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }
    
    /**
     * Método disparado por um ActionEvent que cancela o cadastro e volta para tela de login.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de login não consiga ser carregada.
     */
    @FXML
    void cancelarCadastro(ActionEvent event) throws IOException {
        application.mostrarTelaLogin();
    }

    /**
     * Método disparado por um ActionEvent que cadastra um usuário.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de login não consiga ser carregada.
     */
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
