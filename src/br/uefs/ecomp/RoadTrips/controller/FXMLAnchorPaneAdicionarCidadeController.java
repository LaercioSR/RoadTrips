package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.util.MaskTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FXMLAnchorPaneAdicionarCidadeController implements Initializable {
    @FXML
    private TextField textFieldLongitude;
    @FXML
    private TextArea textFieldDescricao;
    @FXML
    private TextField textFieldArea;
    @FXML
    private TextField textFieldPopulacao;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldLatitude;
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskTextField.maskNumeroInteiro(textFieldPopulacao);
        MaskTextField.maskNumeroReal(textFieldArea);
    }    

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }

    @FXML
    void adicionarImagem(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Arquivos de Imagem","*.png", "*.jpg", "*.gif", "*.jpeg"));
        fileChooser.setTitle("Selecionar Arquivos de Imagem");
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
    }

    @FXML
    void salvarCidade(ActionEvent event) throws IOException {
        if(validarEntradaDados()){
            try {
                controller.adicionarCidade(textFieldNome.getText(), Double.parseDouble(textFieldArea.getText()), 
                                            Integer.parseInt(textFieldPopulacao.getText()), textFieldDescricao.getText(), 
                                            Double.parseDouble(textFieldLatitude.getText()), Double.parseDouble(textFieldLongitude.getText()));
                controllerTela.carregarAnchorPaneMinhasViagens();
            } catch (DadoDuplicadoException ex) {
                alertarDuplicacao();
            }
        }
    }

    @FXML
    void cancelarAdicionamentoCidade(ActionEvent event) {

    }

    public boolean validarEntradaDados() {
        String msgErro = "";
        if(textFieldNome.getText().equals("")){
            msgErro += "Nome inválido!\n";
        }
        if(textFieldLatitude.getText().equals("")){
            msgErro += "Latitude inválido!\n";
        }
        if(textFieldLongitude.getText().equals("")){
            msgErro += "Longitude inválido!\n";
        }
        if(textFieldPopulacao.getText().equals("")){
            msgErro += "População inválida!\n";
        }
        if(textFieldArea.getText().equals("")){
            msgErro += "Área inválida!\n";
        }
        if(textFieldDescricao.getText().equals("")){
            msgErro += "Descrição inválida!\n";
        }
        
        if (msgErro.length() == 0) {
            return true;
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no salvamento");
            alerta.setHeaderText("Campos inválidos, por favor, corrija...");
            alerta.setContentText(msgErro);
            alerta.show();
            return false;
        }
    }
    
    private void alertarDuplicacao() {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro no salvamento");
        alerta.setHeaderText("Cidade já está cadastrada");
        alerta.show();
    }
}
