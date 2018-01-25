package br.uefs.ecomp.RoadTrips.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class FXMLAnchorPaneMinhasViagensController implements Initializable {
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    

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
    
}
