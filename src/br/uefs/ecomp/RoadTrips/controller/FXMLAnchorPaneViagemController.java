package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Viagem;
import br.uefs.ecomp.RoadTrips.model.Viagem.CidadeViagem;
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

public class FXMLAnchorPaneViagemController implements Initializable {    
    @FXML
    private Label labelNomeViagem;    
    @FXML
    private TabPane tabPaneCidade;
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    private Viagem viagem;
    private int contadorCidades = 0;

    
    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void carregar(Viagem viagem) throws IOException {
        this.viagem = viagem;
        
        labelNomeViagem.setText(viagem.getNome());
        
        Iterator itCidadesViagem = viagem.iteratorCidadesViagem();
        
        while(itCidadesViagem.hasNext()){
            CidadeViagem cidadeViagem = (CidadeViagem) itCidadesViagem.next();
            
            FXMLAnchorPaneCidadeViagemController controllerCidade = carregarCidade();
            controllerCidade.setControllerTela(controllerTela);
            controllerCidade.carregar(cidadeViagem);
        }
    }
    
    private FXMLAnchorPaneCidadeViagemController carregarCidade() throws IOException{
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneCidadeViagem.fxml"));
        a = loader.load();
        
        String titulo = "Cidade " + ++contadorCidades;
        
        Tab tab = new Tab(titulo, a);
        tabPaneCidade.getTabs().add(tab);
        
        return loader.getController();
    }
    
    @FXML
    void editarViagem(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneNovaViagem(viagem);
    }
}
