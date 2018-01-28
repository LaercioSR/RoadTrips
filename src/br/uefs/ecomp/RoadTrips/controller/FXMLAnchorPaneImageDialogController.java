package br.uefs.ecomp.RoadTrips.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLAnchorPaneImageDialogController implements Initializable {
    @FXML
    private ImageView imageView;
    
    private Stage dialogStage;
    private Image image;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public void carregarImagem() {
        imageView.setImage(image);
    }
}
