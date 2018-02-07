package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.model.Estabelecimento;
import br.uefs.ecomp.RoadTrips.model.Intersecao;
import br.uefs.ecomp.RoadTrips.model.Usuario;
import br.uefs.ecomp.RoadTrips.model.Viagem;
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

/**
 * Controller da tela principal do programa, sendo usada para carregar as partes 
 * que divide a tela.
 */
public class FXMLTelaInicialController implements Initializable {
    @FXML
    private AnchorPane anchorPaneMenu;  
    @FXML
    private AnchorPane anchorPaneView;
    @FXML
    private MenuButton menuButtonUsuario;
    @FXML
    private ImageView imageViewLogo;
    
    private RoadTripsMain application;
    private RoadTripsController controller;
    private Usuario usuario;
    private Image iconMenuButtonUsuario;
    
    
    /**
     * Método inicializa os dados do FXML.
     * @param url 
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageViewLogo.setImage(new Image("/br/uefs/ecomp/RoadTrips/imagens/logoRoadTrips.png"));
        iconMenuButtonUsuario = new Image("/br/uefs/ecomp/RoadTrips/imagens/iconUsuario.png");
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
     * Método setta o (@link br.uefs.ecomp.RoadTrips.controller.RoadTripsController 
     * controller} principal da aplicação.
     * @param controller Controller principal da aplicação.
     */
    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    /**
     * Método setta o usuário que está logado no sistema.
     * @param usuario Usuário logado.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Método carrega o menu adequado ao sistema, sendo um para os ADMIN's e outro 
     * para os usuário comuns.
     * @throws IOException Caso o menu não consiga ser carregado.
     */
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
    
    /**
     * Método carrega o MenuButton com o nome do usuário logado.
     */
    public void carregarMenuButtonUsuario() {
        menuButtonUsuario.setText(usuario.getLogin());
        menuButtonUsuario.setGraphic(new ImageView(iconMenuButtonUsuario));
    }
    
    /**
     * Método disparado por um ActionEvent que desloga o usuário.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de login não consiga ser carregada.
     */
    @FXML
    void deslogarUsuario(ActionEvent event) throws IOException {
        application.mostrarTelaLogin();
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir as 
     * viagens do usuário logado.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    public void carregarAnchorPaneMinhasViagens() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneMinhasViagens.fxml"));
        a = loader.load();
        FXMLAnchorPaneMinhasViagensController controllerViagens = loader.getController();
        controllerViagens.setController(controller);
        controllerViagens.setControllerTela(this);
        controllerViagens.setUsuario(usuario);
        controllerViagens.carregarListViewViagens();
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir o cadastro 
     * ou edição de uma viagem do usuário.
     * @param viagem Caso necessário, viagem a ser editado.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    public void carregarAnchorPaneNovaViagem(Viagem viagem) throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneNovaViagem.fxml"));
        a = loader.load();
        FXMLAnchorPaneNovaViagemController controllerViagem = loader.getController();
        controllerViagem.setController(controller);
        controllerViagem.setControllerTela(this);
        controllerViagem.setUsuario(usuario);
        if(viagem != null){
            controllerViagem.carregarEdicao(viagem);
        } else{
            controllerViagem.adicionarCidade(null);
        }
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir uma viagem
     * do usuário.
     * @param Viagem Viagem a ser exibida.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    public void carregarAnchorPaneViagem(Viagem Viagem) throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneViagem.fxml"));
        a = loader.load();
        FXMLAnchorPaneViagemController controllerViagem = loader.getController();
        controllerViagem.setController(controller);
        controllerViagem.setControllerTela(this);
        controllerViagem.carregar(Viagem);
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir o cadastro
     * ou edição de uma cidade.
     * @param cidade Caso necessário, cidade a ser editada.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    public void carregarAnchorPaneAdicionarCidade(Cidade cidade) throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneAdicionarCidade.fxml"));
        a = loader.load();
        FXMLAnchorPaneAdicionarCidadeController controllerAdicionarCidade = loader.getController();
        controllerAdicionarCidade.setController(controller);
        controllerAdicionarCidade.setControllerTela(this);
        if(cidade != null){
            controllerAdicionarCidade.carregarEdicao(cidade);
        }
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir os lugares
     * para se comer de uma cidade.
     * @param cidade Cidade que terá seus lugares de comer exibidos.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    public void carregarAnchorPaneLugaresComer(Cidade cidade) throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneLugaresComer.fxml"));
        a = loader.load();
        FXMLAnchorPaneLugaresComerController controllerLugaresComer = loader.getController();
        controllerLugaresComer.setControllerTela(this);
        controllerLugaresComer.setCidade(cidade);
        controllerLugaresComer.carregar();
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir uma tabela
     * das cidades cadastradas, para que possa ser selecionada uma para edição.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
    public void carregarAnchorPaneSelecionarCidade() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneSelecionarCidade.fxml"));
        a = loader.load();
        FXMLAnchorPaneSelecionarCidadeController controllerSelecionarCidade = loader.getController();
        controllerSelecionarCidade.setController(controller);
        controllerSelecionarCidade.setControllerTela(this);
        controllerSelecionarCidade.carregarTableViewCidades();
        anchorPaneView.getChildren().setAll(a);
    }
    
     /**
     * Método que carrega e exibe a divisão direita da tela para exibir o cadastro
     * ou edição de uma interseção.
     * @param intersecao Caso necessário, interseção a ser editada.
     * @throws IOException Caso a tela não consiga ser carregado.
     */   
    public void carregarAnchorPaneAdicionarIntersecao(Intersecao intersecao) throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneAdicionarIntersecao.fxml"));
        a = loader.load();
        FXMLAnchorPaneAdicionarIntersecaoController controllerAdicionarIntersecao = loader.getController();
        controllerAdicionarIntersecao.setController(controller);
        controllerAdicionarIntersecao.setControllerTela(this);
        controllerAdicionarIntersecao.carregarChoiceBoxPontos();
        if(intersecao != null){
            controllerAdicionarIntersecao.carregarEdicao(intersecao);
        }
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir uma tabela
     * das interseções cadastradas, para que possa ser selecionada uma para edição.
     * @throws IOException Caso a tela não consiga ser carregado.
     */  
    public void carregarAnchorPaneSelecionarIntersecao() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneSelecionarIntersecao.fxml"));
        a = loader.load();
        FXMLAnchorPaneSelecionarIntersecaoController controllerSelecionarIntersecao = loader.getController();
        controllerSelecionarIntersecao.setController(controller);
        controllerSelecionarIntersecao.setControllerTela(this);
        controllerSelecionarIntersecao.carregarTableViewIntersecao();
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir o cadastro
     * ou edição de um estabelecimento.
     * @param estabelecimento Caso necessário, estabelecimento a ser editada.
     * @throws IOException Caso a tela não consiga ser carregado.
     */   
    public void carregarAnchorPaneCadastrarEstabelecimento(Estabelecimento estabelecimento) throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneCadastrarEstabelecimento.fxml"));
        a = loader.load();
        FXMLAnchorPaneCadastrarEstabelecimentoController controllerCadastrarEstabelecimento = loader.getController();
        controllerCadastrarEstabelecimento.setController(controller);
        controllerCadastrarEstabelecimento.setControllerTela(this);
        controllerCadastrarEstabelecimento.carregarChoiceBoxCidade();
        if(estabelecimento != null){
            controllerCadastrarEstabelecimento.carregarEdicao(estabelecimento);
        }
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir uma tabela
     * dos estabelecimentos cadastrados, para que possa ser selecionada um para edição.
     * @throws IOException Caso a tela não consiga ser carregado.
     */ 
    public void carregarAnchorPaneSelecionarEstabelecimento() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneSelecionarEstabelecimento.fxml"));
        a = loader.load();
        FXMLAnchorPaneSelecionarEstabelecimentoController controllerSelecionarEstabelecimento = loader.getController();
        controllerSelecionarEstabelecimento.setController(controller);
        controllerSelecionarEstabelecimento.setControllerTela(this);
        controllerSelecionarEstabelecimento.carregarTableViewEstabelecimentos();
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir a adição
     * de uma nova rota.
     * @throws IOException Caso a tela não consiga ser carregado.
     */ 
    public void carregarAnchorPaneAdicionarRotas() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneAdicionarRotas.fxml"));
        a = loader.load();
        FXMLAnchorPaneAdicionarRotasController controllerAdicionarRota = loader.getController();
        controllerAdicionarRota.setController(controller);
        controllerAdicionarRota.setControllerTela(this);
        controllerAdicionarRota.carregarChoiceBox();
        anchorPaneView.getChildren().setAll(a);
    }
    
    /**
     * Método que carrega e exibe a divisão direita da tela para exibir a possibilidade
     * de pesquisar um usuário cadastrado.
     * @throws IOException Caso a tela não consiga ser carregado.
     */
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
