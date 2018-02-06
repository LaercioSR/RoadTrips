package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.model.Estabelecimento;
import br.uefs.ecomp.RoadTrips.model.TipoEstabelecimento;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class FXMLAnchorPaneLugaresComerController implements Initializable {    
    @FXML
    private Label labelTitulo;    
    @FXML
    private ListView<AnchorPane> listViewLugaresComer;

    private Cidade cidade;
    private FXMLTelaInicialController controllerTela;


    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
    
    public void carregar() throws IOException{
        String titulo = "Lugares para Comer em " + cidade.getNome();
        labelTitulo.setText(titulo);
        labelTitulo.setAlignment(Pos.CENTER);
        
        LinkedList<AnchorPane> anchorPanes = new LinkedList<>();
        Iterator itEstabelecimento = cidade.iteratorEstabelecimentos();
        
        while(itEstabelecimento.hasNext()){
            Estabelecimento estabelecimento = (Estabelecimento) itEstabelecimento.next();
            
            if(estabelecimento.getTipoEstabelecimento() == TipoEstabelecimento.LugaresParaComer){
                AnchorPane a;
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneEstabelecimento.fxml"));
                a = loader.load();

                FXMLAnchorPaneEstabelecimentoController controllerEstabelecimento = loader.getController();
                controllerEstabelecimento.carregar(estabelecimento);

                anchorPanes.add(a);
            }
        }
        
        ObservableList<AnchorPane> itens = FXCollections.observableArrayList(anchorPanes);
        listViewLugaresComer.setItems(itens);
    }
}
