package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.view.RoadTripsMain;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class FXMLTelaInicialController implements Initializable {

    private RoadTripsMain application;
    private RoadTripsController controller;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setApplication(RoadTripsMain application) {
        this.application = application;
    }

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }
}
