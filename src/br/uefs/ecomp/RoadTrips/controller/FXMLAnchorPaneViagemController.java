package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Viagem;
import br.uefs.ecomp.RoadTrips.model.Parada;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 * Classe controla a tela de exibição de uma viagem
 */
public class FXMLAnchorPaneViagemController implements Initializable {    
    @FXML
    private Label labelNomeViagem;    
    @FXML
    private TabPane tabPaneCidade;
    
    private FXMLTelaInicialController controllerTela;
    private Viagem viagem;
    private int contadorCidades = 0;


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
        
    }   
    
    /**
     * Método carrega a tela para exibir os dados da viagem.
     * @param viagem Viagem que terá seus dados exibidos.
     * @throws IOException Caso a telinha de uma parada não consiga ser carregada.
     */
    public void carregar(Viagem viagem) throws IOException {
        this.viagem = viagem;
        
        labelNomeViagem.setText(viagem.getNome());
        
        Iterator itCidadesViagem = viagem.iteratorParadas();
        
        while(itCidadesViagem.hasNext()){
            Parada cidadeViagem = (Parada) itCidadesViagem.next();
            
            FXMLAnchorPaneParadaController controllerCidade = carregarCidade();
            controllerCidade.setControllerTela(controllerTela);
            controllerCidade.carregar(cidadeViagem);
        }
    }
    
    /**
     * Método carrega uma Tab para exibir uma parada.
     * @return Controller da telinha.
     * @throws IOException Caso a telinha não consiga ser carregada.
     */
    private FXMLAnchorPaneParadaController carregarCidade() throws IOException{
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneParada.fxml"));
        a = loader.load();
        
        String titulo = "Cidade " + ++contadorCidades;
        
        Tab tab = new Tab(titulo, a);
        tabPaneCidade.getTabs().add(tab);
        
        return loader.getController();
    }
    
    /**
     * Método carrega a tela para editar a viagem.
     * @param event Evento que disparou o método. 
     * @throws IOException Caso a tela de nova viagem não consiga ser carregada.
     */
    @FXML
    void editarViagem(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneNovaViagem(viagem);
    }
}
