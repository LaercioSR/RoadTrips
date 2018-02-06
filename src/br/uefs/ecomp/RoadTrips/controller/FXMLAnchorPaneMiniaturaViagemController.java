package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Viagem;
import br.uefs.ecomp.RoadTrips.model.Viagem.CidadeViagem;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FXMLAnchorPaneMiniaturaViagemController implements Initializable {
    @FXML
    private ImageView imageViewMiniatura;
    @FXML
    private Label labelNomeViagem;
    @FXML
    private Label labelCidadesViagem;
    
    private Viagem viagem;
    private AnchorPane anchorPane;
    

    public Viagem getViagem() {
        return viagem;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageViewMiniatura.setImage(new Image("/br/uefs/ecomp/RoadTrips/imagens/viagem.jpg"));
    }
    
    public void carregar() {
        String cidadesViagem = "";
        Iterator itCidades = viagem.iteratorCidadesViagem();
        
        if(itCidades.hasNext()){
            CidadeViagem cidade = (CidadeViagem) itCidades.next();
            cidadesViagem += cidade.getCidade().getNome(); 
        }
        while(itCidades.hasNext()){
            CidadeViagem cidade = (CidadeViagem) itCidades.next();
            cidadesViagem += " - " + cidade.getCidade().getNome();
        }
        
        labelNomeViagem.setText(viagem.getNome());
        labelCidadesViagem.setText(cidadesViagem);
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof FXMLAnchorPaneMiniaturaViagemController){
            return anchorPane.equals(((FXMLAnchorPaneMiniaturaViagemController)o).getAnchorPane());
        }
        return false;
    }
}
