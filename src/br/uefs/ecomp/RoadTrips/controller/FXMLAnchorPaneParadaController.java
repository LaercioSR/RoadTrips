package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Parada;
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

/**
 * Classe que controla a telinha que exibe uma parada de uma viagem.
 */
public class FXMLAnchorPaneParadaController implements Initializable { 
    @FXML
    private Label labelCidade;   
    @FXML
    private DatePicker datePickerChegada;
    @FXML
    private DatePicker datePickerPartida;
    @FXML
    private ImageView imageViewLugaresComer;
    
    private FXMLTelaInicialController controllerTela;
    private Parada cidadeViagem;


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
    
    /**
     * Método carrega a tela com as informações da parada.
     * @param parada Parada para carregar as informações.
     */
    public void carregar(Parada parada){
        this.cidadeViagem = parada;
        
        labelCidade.setText(parada.getCidade().getNome());
        datePickerChegada.setValue(parada.getDataChegada());
        datePickerPartida.setValue(parada.getDataPartida());
    }
}
