package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import br.uefs.ecomp.RoadTrips.model.Usuario;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLTelaLoginController implements Initializable {
    
    @FXML private Label labelMensagemErro;
    @FXML private AnchorPane anchorPane;
    @FXML private PasswordField passwordFieldSenha;
    @FXML private Button buttonCadastrar;
    @FXML private Button buttonEntrar;
    @FXML private TextField textFieldUsuario;
    @FXML
    private ImageView imageViewLogo;
    
    private RoadTripsMain application;
    private RoadTripsController controller;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageViewLogo.setImage(new Image("/br/uefs/ecomp/RoadTrips/imagens/logoRoadTrips.png"));
        
        textFieldUsuario.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    processarLogin(null);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLTelaCadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        passwordFieldSenha.setOnKeyPressed(textFieldUsuario.getOnKeyPressed());
        
        
        textFieldUsuario.setText("Laercio");
    }

    public void setApplication(RoadTripsMain application) {
        this.application = application;
    }

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    @FXML
    void processarLogin(ActionEvent event) throws IOException {
        try {
            Usuario usuario = controller.autenticarLogin(textFieldUsuario.getText(), passwordFieldSenha.getText());
            application.mostrarTelaInicial(usuario);
            
        } catch (DadoNaoEncontradoException ex) {
            labelMensagemErro.setText("Usuário/Senha está(ão) incorretos");
        }
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) throws IOException, DadoDuplicadoException {
        application.mostrarTelaCadastro();
    }
}
