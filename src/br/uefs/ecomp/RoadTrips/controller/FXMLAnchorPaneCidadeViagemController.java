package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Viagem.CidadeViagem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FXMLAnchorPaneCidadeViagemController implements Initializable { 
    @FXML
    private Label labelCidade;   
    @FXML
    private DatePicker datePickerChegada;
    @FXML
    private DatePicker datePickerPartida;
    @FXML
    private ImageView imageViewLugaresComer;
    
    private FXMLTelaInicialController controllerTela;
    private CidadeViagem cidadeViagem;


    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageViewLugaresComer.setImage(new Image("/br/uefs/ecomp/RoadTrips/imagens/lugaresComer.jpg"));
        imageViewLugaresComer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    try {
                        controllerTela.carregarAnchorPaneLugaresComer(cidadeViagem.getCidade());
                    } catch (IOException ex) { }
                }
            }
        });
    }    
    
    public void carregar(CidadeViagem cidadeViagem){
        this.cidadeViagem = cidadeViagem;
        
        labelCidade.setText(cidadeViagem.getCidade().getNome());
        datePickerChegada.setValue(cidadeViagem.getDataChegada());
        datePickerPartida.setValue(cidadeViagem.getDataPartida());
    }
}
