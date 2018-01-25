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
    private AnchorPane anchorPaneView;
    @FXML
    private MenuButton menuButtonUsuario;
    
    private RoadTripsMain application;
    private RoadTripsController controller;
    private Usuario usuario;
    private Image iconMenuButtonUsuario;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iconMenuButtonUsuario = new Image("/br/uefs/ecomp/RoadTrips/icon/iconUsuario.png");
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
        FXMLLoader loader = new FXMLLoader();
        if(usuario.isAdmin()){
            loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneMenuAdmin.fxml"));
        } else {
            loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneMenu.fxml"));
        }
        a = loader.load();
        FXMLAnchorPaneMenuController controllerMenu = loader.getController();
        controllerMenu.setController(this);
        anchorPaneMenu.getChildren().setAll(a);
        carregarAnchorPaneMinhasViagens();
    }
    
    public void carregarMenuButtonUsuario() {
        menuButtonUsuario.setText(usuario.getLogin());
        menuButtonUsuario.setGraphic(new ImageView(iconMenuButtonUsuario));
    }
    
    @FXML
    void deslogarUsuario(ActionEvent event) throws IOException {
        application.mostrarTelaLogin();
    }
    
    public void carregarAnchorPaneMinhasViagens() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneMinhasViagens.fxml"));
        a = loader.load();
        FXMLAnchorPaneMinhasViagensController controllerViagens = loader.getController();
        controllerViagens.setController(controller);
        controllerViagens.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
    
    public void carregarAnchorPaneNovaViagem() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneNovaViagem.fxml"));
        a = loader.load();
        FXMLAnchorPaneNovaViagemController controllerViagem = loader.getController();
        controllerViagem.setController(controller);
        controllerViagem.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
    
    public void carregarAnchorPaneAdicionarCidade() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneAdicionarCidade.fxml"));
        a = loader.load();
        FXMLAnchorPaneAdicionarCidadeController controllerAdicionarCidade = loader.getController();
        controllerAdicionarCidade.setController(controller);
        controllerAdicionarCidade.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
    
    public void carregarAnchorPaneSelecionarCidade() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneSelecionarCidade.fxml"));
        a = loader.load();
        FXMLAnchorPaneSelecionarCidadeController controllerSelecionarCidade = loader.getController();
        controllerSelecionarCidade.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
    
    public void carregarAnchorPaneAdicionarIntersecao() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneAdicionarIntersecao.fxml"));
        a = loader.load();
        FXMLAnchorPaneAdicionarIntersecaoController controllerAdicionarIntersecao = loader.getController();
        controllerAdicionarIntersecao.setController(controller);
        controllerAdicionarIntersecao.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
    
    public void carregarAnchorPaneSelecionarIntersecao() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneSelecionarIntersecao.fxml"));
        a = loader.load();
        FXMLAnchorPaneSelecionarIntersecaoController controllerSelecionarIntersecao = loader.getController();
        controllerSelecionarIntersecao.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
    
    public void carregarAnchorPaneCadastrarEstabelecimento() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneCadastrarEstabelecimento.fxml"));
        a = loader.load();
        FXMLAnchorPaneCadastrarEstabelecimentoController controllerCadastrarEstabelecimento = loader.getController();
        controllerCadastrarEstabelecimento.setController(controller);
        controllerCadastrarEstabelecimento.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
    
    public void carregarAnchorPaneSelecionarEstabelecimento() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneSelecionarEstabelecimento.fxml"));
        a = loader.load();
        FXMLAnchorPaneSelecionarEstabelecimentoController controllerSelecionarEstabelecimento = loader.getController();
        controllerSelecionarEstabelecimento.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
    
    public void carregarAnchorPaneAdicionarRotas() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneAdicionarRotas.fxml"));
        a = loader.load();
        FXMLAnchorPaneAdicionarRotasController controllerAdicionarRota = loader.getController();
        controllerAdicionarRota.setController(controller);
        controllerAdicionarRota.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
    
    public void carregarAnchorPanePesquisarUsuario() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPanePesquisarUsuario.fxml"));
        a = loader.load();
        FXMLAnchorPanePesquisarUsuarioController controllerPesquisarUsuario = loader.getController();
        controllerPesquisarUsuario.setController(controller);
        controllerPesquisarUsuario.setControllerTela(this);
        anchorPaneView.getChildren().setAll(a);
    }
}
