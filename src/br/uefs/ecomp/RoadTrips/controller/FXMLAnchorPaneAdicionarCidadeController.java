package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.util.MaskTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FXMLAnchorPaneAdicionarCidadeController implements Initializable {

    @FXML
    private Label labelTitulo;
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
    @FXML
    private ListView<ImageView> listViewImagens;
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    private LinkedList<Image> imagensCidade = new LinkedList<>();
    private Cidade cidade;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskTextField.maskNumeroInteiro(textFieldPopulacao);
        MaskTextField.maskNumeroReal(textFieldArea);
        
        listViewImagens.setOrientation(Orientation.HORIZONTAL);
        listViewImagens.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    if(!imagensCidade.isEmpty()){
                        Image image = listViewImagens.getFocusModel().getFocusedItem().getImage();
                        carregarTelaImageDialog(image);
                    }
                }
            }
        });
    }    

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }

    public void carregarEdicao(Cidade cidade){
        this.cidade = cidade;
        
        labelTitulo.setText("Editar Cidade");
        labelTitulo.setAlignment(Pos.CENTER);
        textFieldLatitude.setEditable(false);
        textFieldLongitude.setEditable(false);
        
        textFieldLongitude.setText(((Double) cidade.getLongitude()).toString());
        textFieldDescricao.setText(cidade.getDescricao());
        textFieldArea.setText(((Double) cidade.getArea()).toString());
        textFieldPopulacao.setText(((Integer) cidade.getPopulacao()).toString());
        textFieldNome.setText(cidade.getNome());
        textFieldLatitude.setText(((Double) cidade.getLatitude()).toString());
        imagensCidade = cidade.getImagens();
        carregarListViewImagens();
    }
    
    @FXML
    void adicionarImagem(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Arquivos de Imagem","*.png", "*.jpg", "*.gif", "*.jpeg"));
        fileChooser.setTitle("Selecionar Arquivos de Imagem");
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        
        selectedFiles.stream().map((a) -> {
            FileInputStream inputstream = null;
            try {
                inputstream = new FileInputStream(a);
            } catch (FileNotFoundException ex) { }
            return inputstream;            
        }).forEachOrdered((inputstream) -> {
            Image image = new Image(inputstream);
            imagensCidade.add(image);
        });
        
        carregarListViewImagens();
    }
    
    private void carregarListViewImagens() {
        LinkedList<ImageView> imagens = new LinkedList<>();
        for(Image a: imagensCidade){
            ImageView image = new ImageView(a);
            image.setFitHeight(140);
            image.setFitWidth(250);
            imagens.add(image);
        }
        ObservableList<ImageView> itens = FXCollections.observableArrayList(imagens);
        listViewImagens.setItems(itens);
    }

    @FXML
    void salvarCidade(ActionEvent event) throws IOException {
        if(validarEntradaDados()){
            try {
                if(labelTitulo.getText().equals("Adicionar Cidade")){
                    controller.adicionarCidade(textFieldNome.getText(), Double.parseDouble(textFieldArea.getText()), 
                                            Integer.parseInt(textFieldPopulacao.getText()), textFieldDescricao.getText(), 
                                            Double.parseDouble(textFieldLatitude.getText()), 
                                            Double.parseDouble(textFieldLongitude.getText()), imagensCidade);
                    
                controllerTela.carregarAnchorPaneAdicionarCidade(null);
                } else{
                    cidade.setNome(textFieldNome.getText());
                    cidade.setPopulacao(Integer.parseInt(textFieldPopulacao.getText()));
                    cidade.setArea(Double.parseDouble(textFieldArea.getText()));
                    cidade.setDescricao(textFieldDescricao.getText());
                    
                    controllerTela.carregarAnchorPaneSelecionarCidade();
                }
            } catch (DadoDuplicadoException ex) {
                alertarDuplicacao();
            }
        }
    }

    @FXML
    void cancelarSalvamentoCidade(ActionEvent event) throws IOException {
        if(labelTitulo.getText().equals("Adicionar Cidade")){
            controllerTela.carregarAnchorPaneMinhasViagens();
        } else{
            controllerTela.carregarAnchorPaneSelecionarCidade();
        }
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
    
    private void carregarTelaImageDialog(Image image) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/RoadTrips/view/FXMLAnchorPaneImageDialog.fxml"));
        AnchorPane page = null;
        try {
            page = (AnchorPane) loader.load();
        } catch (IOException ex) { }

        Stage dialogStage = new Stage();
        if(cidade != null){
            dialogStage.setTitle("Imagem de " + cidade.getNome());
        } else{
            dialogStage.setTitle("Imagem de " + textFieldNome.getText());
        }
        
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneImageDialogController controllerDialog = loader.getController();
        controllerDialog.setDialogStage(dialogStage);
        controllerDialog.setImage(image);
        controllerDialog.carregarImagem();

        dialogStage.setResizable(false);
        dialogStage.showAndWait();
    }
}
