package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import br.uefs.ecomp.RoadTrips.model.TipoUsuario;
import br.uefs.ecomp.RoadTrips.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Classe controla a tela de busca de usuário.
 */
public class FXMLAnchorPanePesquisarUsuarioController implements Initializable {
    @FXML
    private TextField textFieldPesquisa;
    @FXML
    private Button buttonPesquisa;
    @FXML
    private Label labelMensagemErro;
    @FXML
    private GridPane gridPaneUsuario;
    @FXML
    private Label labelUsuario;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelTipo;
    @FXML
    private Button buttonTipoUsuario;
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    private Image iconButtonPesquisa;
    private Usuario usuario = null;
    

    /**
     * Método setta o {@link br.uefs.ecomp.RoadTrips.controller.RoadTripsController 
     * controller} principal da aplicação.
     * @param controller Controller principal da aplicação.
     */
    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    /**
     * Método setta o {@link br.uefs.ecomp.RoadTrips.controller.FXMLTelaInicialController 
     * controller da tela principal} da aplicação, que será usada para trocar a tela do sistema.
     * @param controllerTela Controller da tela principal da aplicação.
     */
    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    /**
     * Método inicializa os dados do FXML.
     * @param url Paramêtro padrão do JAVA.
     * @param rb Paramêtro padrão do JAVA.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iconButtonPesquisa = new Image("/br/uefs/ecomp/RoadTrips/imagens/iconPesquisa.png");
        buttonPesquisa.setGraphic(new ImageView(iconButtonPesquisa));
    }        
    
    /**
     * Método disparado por um ActionEvent que busca o usuário que teve seu login digitado
     * no TextField.
     * @param event Evento que disparou o método.
     */
    @FXML
    void pesquisarUsuario(ActionEvent event) {
            labelMensagemErro.setText("");
        try {
            usuario = controller.buscarUsuario(textFieldPesquisa.getText());
            
            gridPaneUsuario.setVisible(true);
            labelUsuario.setText(usuario.getLogin());
            labelEmail.setText(usuario.getEmail());
            labelTipo.setText(usuario.getTipoUsuario().name());
            
            if(!usuario.isAdmin()){
                buttonTipoUsuario.setVisible(true);
            }
        } catch (DadoNaoEncontradoException ex) {
            labelMensagemErro.setText("Usuário não encontrado");
            gridPaneUsuario.setVisible(false);
            buttonTipoUsuario.setVisible(false);
        }
    }

    /**
     * Método disparado por um ActionEvent que torna o usuário buscado, um ADMIN do sistema.
     * @param event Evento que disparou o método.
     */
    @FXML
    void tornarUsuarioAdmin(ActionEvent event) {
        usuario.setTipoUsuario(TipoUsuario.admin);
        labelTipo.setText(usuario.getTipoUsuario().name());
        buttonTipoUsuario.setVisible(false);
    }
}
