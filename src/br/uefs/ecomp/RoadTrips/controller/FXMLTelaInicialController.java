package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Usuario;
import br.uefs.ecomp.RoadTrips.view.RoadTripsMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FXMLTelaInicialController implements Initializable {
    @FXML
    private AnchorPane anchorPaneMenu;
    @FXML
    private MenuButton menuButtonUsuario;
    
    private RoadTripsMain application;
    private RoadTripsController controller;
    private Usuario usuario;
    private Image iconMenuButtonUsuario;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iconMenuButtonUsuario = new Image("/br/uefs/ecomp/RoadTrips/icon/iconUsuario.png");
        // TODO
    }

    public void setApplication(RoadTripsMain application) {
        this.application = application;
    }

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void carregarAnchorPaneMenu() throws IOException {
        AnchorPane a;
        if(usuario.isAdmin()){
            a = FXMLLoader.load(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneMenuAdmin.fxml"));
        } else {
            a = FXMLLoader.load(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneMenu.fxml"));
        }
        anchorPaneMenu.getChildren().setAll(a);
        //menuButtonUsuario.setText(usuario.getLogin());
    }
    
    public void carregarMenuButtonUsuario() {
        menuButtonUsuario.setText(usuario.getLogin());
        menuButtonUsuario.setGraphic(new ImageView(iconMenuButtonUsuario));
    }
    
    @FXML
    void deslogarUsuario(ActionEvent event) throws IOException {
        application.mostrarTelaLogin();
    }
}
