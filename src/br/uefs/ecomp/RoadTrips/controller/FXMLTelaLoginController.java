package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import br.uefs.ecomp.RoadTrips.model.Usuario;
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
 * Classe que controla a tela de login da interface gráfica do projeto.
 * @author Laercio
 */
public class FXMLTelaLoginController implements Initializable {
    
    @FXML 
    private Label labelMensagemErro;
    @FXML 
    private PasswordField passwordFieldSenha;
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
                    processarLogin(null);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLTelaCadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        passwordFieldSenha.setOnKeyPressed(textFieldUsuario.getOnKeyPressed());
    }

    /**
     * Método setta a classe {@link br.uefs.ecomp.RoadTrips.view.RoadTripsMain Main} 
     * da aplicação.
     * @param application Classe main da aplicação.
     */
    public void setApplication(RoadTripsMain application) {
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
     * Método chamado por um button na interface gráfica, que caso o login/senha estiverem
     * corretos o usuário é logado.
     * @param event Evento que disparou o método. 
     * @throws IOException Caso a tela de principal do programa não consiga ser carregada.
     */
    @FXML
    void processarLogin(ActionEvent event) throws IOException {
        try {
            Usuario usuario = controller.autenticarLogin(textFieldUsuario.getText(), passwordFieldSenha.getText());
            application.mostrarTelaInicial(usuario);
            
        } catch (DadoNaoEncontradoException ex) {
            labelMensagemErro.setText("Usuário/Senha está(ão) incorretos");
        }
    }

    /**
     * Método disparado por um ActionEvent que abre a tela de cadastro de usuário.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de cadastro não consiga ser carregada.
     */
    @FXML
    void cadastrarUsuario(ActionEvent event) throws IOException {
        application.mostrarTelaCadastro();
    }
}
