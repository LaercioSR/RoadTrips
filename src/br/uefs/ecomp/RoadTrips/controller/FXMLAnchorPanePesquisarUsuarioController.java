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
    

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iconButtonPesquisa = new Image("/br/uefs/ecomp/RoadTrips/icon/iconPesquisa.png");
        buttonPesquisa.setGraphic(new ImageView(iconButtonPesquisa));
    }        
    
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

    @FXML
    void tornarUsuarioAdmin(ActionEvent event) {
        usuario.setTipoUsuario(TipoUsuario.admin);
        labelTipo.setText(usuario.getTipoUsuario().name());
        buttonTipoUsuario.setVisible(false);
    }
}
