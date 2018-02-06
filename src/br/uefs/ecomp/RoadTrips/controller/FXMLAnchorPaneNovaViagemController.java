package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Usuario;
import br.uefs.ecomp.RoadTrips.model.Viagem;
import br.uefs.ecomp.RoadTrips.model.Viagem.CidadeViagem;
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
    private LinkedList<FXMLAnchorPaneAddCidadeViagemController> controllersCidades = new LinkedList();
    private int contadorCidades = 0;
    

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void carregarEdicao(Viagem viagem) throws IOException{
        this.viagem = viagem;
        
        labelTitulo.setText("Editar Viagem");
        labelTitulo.setAlignment(Pos.CENTER);
        textFieldNomeViagem.setText(viagem.getNome());
        
        Iterator itCidadesViagem = viagem.iteratorCidadesViagem();
        
        while(itCidadesViagem.hasNext()){
            CidadeViagem cidadeViagem = (CidadeViagem) itCidadesViagem.next();
            
            adicionarCidade(null);
            FXMLAnchorPaneAddCidadeViagemController controllerCidade = controllersCidades.peekLast();
            controllerCidade.carregarEdicao(cidadeViagem);
        }
    }

    @FXML
    void adicionarCidade(ActionEvent event) throws IOException {AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneAddCidadeViagem.fxml"));
        a = loader.load();
        FXMLAnchorPaneAddCidadeViagemController controllerCidade = loader.getController();
        controllerCidade.setController(controller);
        controllerCidade.setIdController(++contadorCidades);
        controllerCidade.carregarComboBoxCidades();
        
        controllersCidades.add(controllerCidade);
        String titulo = "Cidade " + contadorCidades;
        
        Tab tab = new Tab(titulo, a);
        tabPaneViagem.getTabs().add(tab);
    } 
    
    @FXML
    void salvarViagem(ActionEvent event) throws IOException {
        if(viagem == null){
            viagem = controller.criarViagem(textFieldNomeViagem.getText());

            for(FXMLAnchorPaneAddCidadeViagemController a: controllersCidades){
                if(a.getCidadeSelecionada() != null && a.getDataChegada() != null && a.getDataPartida() != null){
                    viagem.addCidade(a.getCidadeSelecionada(), a.getDataChegada(), a.getDataPartida());
                } else{
                    String mensagemErro = "Cidade " + a.getIdController() + " possui algum campo que não informado";
                    labelMensagemErro.setText(mensagemErro);
                    return;
                }
            }

            controller.adiocionarViagemUsuario(usuario, viagem);
            controllerTela.carregarAnchorPaneMinhasViagens();
        } else{
            for(FXMLAnchorPaneAddCidadeViagemController a: controllersCidades){
                if(a.getCidadeSelecionada() != null && a.getDataChegada() != null && a.getDataPartida() != null){
                    a.modificarCidadeViagem();
                } else{
                    String mensagemErro = "Cidade " + a.getIdController() + " possui algum campo que não informado";
                    labelMensagemErro.setText(mensagemErro);
                    return;
                }
            }
            
            controllerTela.carregarAnchorPaneViagem(viagem);
        }
    }

    @FXML
    void cancelarSalvamentoViagem(ActionEvent event) {

    }
    
}
