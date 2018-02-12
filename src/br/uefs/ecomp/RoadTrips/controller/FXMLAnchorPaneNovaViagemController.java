package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Usuario;
import br.uefs.ecomp.RoadTrips.model.Viagem;
import br.uefs.ecomp.RoadTrips.model.Parada;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Classe controla da tela de adição de nova viagem, usada também para editar uma 
 * viagem.
 */
public class FXMLAnchorPaneNovaViagemController implements Initializable {
    @FXML
    private Label labelTitulo;
    @FXML
    private TextField textFieldNomeViagem;
    @FXML
    private TabPane tabPaneViagem;
    @FXML
    private Label labelMensagemErro;
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    private Usuario usuario;
    private Viagem viagem;
    private LinkedList<FXMLAnchorPaneAdicionarParadaController> controllersCidades = new LinkedList();
    private int contadorCidades = 0;
    

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
     * Método define o usuário dono da nova viagem.
     * @param usuario Usuário logado.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Método inicializa os dados do FXML.
     * @param url Paramêtro padrão do JAVA.
     * @param rb Paramêtro padrão do JAVA.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    /**
     * Método é usado para carregar a tela para editar uma viagem.
     * @param viagem Viagem a ser editada.
     */
    public void carregarEdicao(Viagem viagem) throws IOException{
        this.viagem = viagem;
        
        labelTitulo.setText("Editar Viagem");
        labelTitulo.setAlignment(Pos.CENTER);
        textFieldNomeViagem.setText(viagem.getNome());
        
        Iterator itCidadesViagem = viagem.iteratorParadas();
        
        while(itCidadesViagem.hasNext()){
            Parada cidadeViagem = (Parada) itCidadesViagem.next();
            
            adicionarParada(null);
            FXMLAnchorPaneAdicionarParadaController controllerCidade = controllersCidades.peekLast();
            controllerCidade.carregarEdicao(cidadeViagem);
        }
    }

    /**
     * Método disparado por um ActionEvent que possibilita a adição de uma nova parada na viagem.
     * @param event Evento que disparou o método.
     * @throws IOException Caso a telinha de adição de uma parada não consiga ser carregada.
     */
    @FXML
    void adicionarParada(ActionEvent event) throws IOException {AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneAdicionarParada.fxml"));
        a = loader.load();
        FXMLAnchorPaneAdicionarParadaController controllerCidade = loader.getController();
        controllerCidade.setController(controller);
        controllerCidade.setIdController(++contadorCidades);
        controllerCidade.carregarComboBoxCidades();
        
        controllersCidades.add(controllerCidade);
        String titulo = "Cidade " + contadorCidades;
        
        Tab tab = new Tab(titulo, a);
        tabPaneViagem.getTabs().add(tab);
    } 
    
    /**
     * Método disparado por um ActionEvent que salva a viagem. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de minhas viagens ou a tela da viagem não 
     * consiga ser carregada.
     */
    @FXML
    void salvarViagem(ActionEvent event) throws IOException {
        if(viagem == null){
            viagem = controller.criarViagem(textFieldNomeViagem.getText());

            for(FXMLAnchorPaneAdicionarParadaController a: controllersCidades){
                if(a.getCidadeSelecionada() != null && a.getDataChegada() != null && a.getDataPartida() != null){
                    viagem.addParada(a.getCidadeSelecionada(), a.getDataChegada(), a.getDataPartida());
                } else{
                    String mensagemErro = "Cidade " + a.getIdController() + " possui algum campo que não informado";
                    labelMensagemErro.setText(mensagemErro);
                    return;
                }
            }

            controller.adiocionarViagemUsuario(usuario, viagem);
            controllerTela.carregarAnchorPaneMinhasViagens();
        } else{
            for(FXMLAnchorPaneAdicionarParadaController a: controllersCidades){
                if(a.getCidadeSelecionada() != null && a.getDataChegada() != null && a.getDataPartida() != null){
                    a.modificarParada();
                } else{
                    String mensagemErro = "Cidade " + a.getIdController() + " possui algum campo que não informado";
                    labelMensagemErro.setText(mensagemErro);
                    return;
                }
            }
            
            controllerTela.carregarAnchorPaneViagem(viagem);
        }
    }

    /**
     * Método disparado por um ActionEvent que cancela o salvamento da viagem. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de minhas viagens ou a tela da viagem não 
     * consiga ser carregada.
     */
    @FXML
    void cancelarSalvamentoViagem(ActionEvent event) throws IOException {
        if(viagem == null)
            controllerTela.carregarAnchorPaneMinhasViagens();
        else
            controllerTela.carregarAnchorPaneViagem(viagem);
    }
}
