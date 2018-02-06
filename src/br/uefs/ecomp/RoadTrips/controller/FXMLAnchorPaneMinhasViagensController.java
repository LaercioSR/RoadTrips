package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Usuario;
import br.uefs.ecomp.RoadTrips.model.Viagem;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLAnchorPaneMinhasViagensController implements Initializable {    
    @FXML
    private ListView<AnchorPane> listViewViagens;
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    private Usuario usuario;
    private LinkedList<FXMLAnchorPaneMiniaturaViagemController> controllerMiniaturas = new LinkedList();
    

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
        listViewViagens.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    if(listViewViagens.getFocusModel().getFocusedItem() != null){
                        FXMLAnchorPaneMiniaturaViagemController controlCmp = new FXMLAnchorPaneMiniaturaViagemController();
                        controlCmp.setAnchorPane(listViewViagens.getFocusModel().getFocusedItem());
                        int posicao = controllerMiniaturas.indexOf(controlCmp);
                        FXMLAnchorPaneMiniaturaViagemController controllerViagem = controllerMiniaturas.get(posicao);
                        Viagem viagem = controllerViagem.getViagem();
                        
                        try {
                            controllerTela.carregarAnchorPaneViagem(viagem);
                        } catch (IOException ex) { }
                    }
                }
            }
        });
    }
    
    public void carregarListViewViagens() throws IOException {
        LinkedList<AnchorPane> miniaturas = new LinkedList<>();
        Iterator itViagens = usuario.iteratorViagem();
        
        while(itViagens.hasNext()){
            Viagem viagem = (Viagem) itViagens.next();
            
            AnchorPane a;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneMiniaturaViagem.fxml"));
            a = loader.load();
            
            FXMLAnchorPaneMiniaturaViagemController controllerViagem = loader.getController();
            controllerViagem.setAnchorPane(a);
            controllerViagem.setViagem(viagem);
            controllerViagem.carregar();
            
            controllerMiniaturas.add(controllerViagem);
            miniaturas.add(a);
        }
        
        ObservableList<AnchorPane> itens = FXCollections.observableArrayList(miniaturas);
        listViewViagens.setItems(itens);
    }
}
