package br.uefs.ecomp.RoadTrips.view;

import br.uefs.ecomp.RoadTrips.controller.FXMLTelaLoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RoadTripsMain extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTelaInicial.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Road Trips");
        stage.setResizable(false);
        if(showFXMLTelaLogin())
            stage.show();
    }
    
    public boolean showFXMLTelaLogin() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLTelaLogin.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

            // Criando um Estágio de Diálogo (Stage Dialog)
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setResizable(false);
            //Especifica a modalidade para esta fase . Isso deve ser feito antes de fazer o estágio visível. A modalidade pode ser: Modality.NONE , Modality.WINDOW_MODAL , ou Modality.APPLICATION_MODAL 
            //dialogStage.initModality(Modality.WINDOW_MODAL);//WINDOW_MODAL (possibilita minimizar)

            //Especifica a janela do proprietário para esta página, ou null para um nível superior.
            //dialogStage.initOwner(null); //null deixa a Tela Principal livre para ser movida
            //dialogStage.initOwner(this.tableViewClientes.getScene().getWindow()); //deixa a tela de Preenchimento dos dados como prioritária

        Scene scene = new Scene(page);
        loginStage.setScene(scene);

            // Setando o cliente no Controller.
        FXMLTelaLoginController controller = loader.getController();
        controller.setLoginStage(loginStage);
        
            // Mostra o Dialog e espera até que o usuário o feche
        loginStage.showAndWait();
        
        return controller.isButtonClicked();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
