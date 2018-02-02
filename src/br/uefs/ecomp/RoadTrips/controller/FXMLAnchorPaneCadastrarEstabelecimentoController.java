package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.model.Estabelecimento;
import br.uefs.ecomp.RoadTrips.model.Ponto;
import br.uefs.ecomp.RoadTrips.model.TipoEstabelecimento;
import br.uefs.ecomp.RoadTrips.util.Vertex;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class FXMLAnchorPaneCadastrarEstabelecimentoController implements Initializable {
    @FXML
    private Label labelTitulo;
    @FXML
    private TextField textFieldNome;
    @FXML
    private ChoiceBox<TipoEstabelecimento> choiceBoxTipo;
    @FXML
    private ChoiceBox<Cidade> choiceBoxCidade;
    @FXML
    private ListView<ImageView> listViewImagens;
    @FXML
    private Label labelMensagemErro;
    
    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    private LinkedList<Image> imagensEstabelecimento = new LinkedList<>();
    private Estabelecimento estabelecimento;
    

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listViewImagens.setOrientation(Orientation.HORIZONTAL);
        
        carregarChoiceBoxTipo();
    }   
    
    public void carregarEdicao(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
        imagensEstabelecimento = estabelecimento.getImagens();
        
        labelTitulo.setText("Editar Estabelecimento");
        labelTitulo.setAlignment(Pos.CENTER);
        
        textFieldNome.setText(estabelecimento.getNome());
        choiceBoxTipo.setValue(estabelecimento.getTipoEstabelecimento());
        choiceBoxCidade.setValue(estabelecimento.getCidade());
        choiceBoxTipo.setDisable(true);
        choiceBoxCidade.setDisable(true);
        
        carregarListViewImagens();
    }
    
    public void carregarChoiceBoxTipo() {
        LinkedList itens = new LinkedList();
        itens.add(TipoEstabelecimento.LugaresParaComer);
        choiceBoxTipo.setTooltip(new Tooltip("Selecione o tipo do estabelecimento"));
        choiceBoxTipo.setItems(FXCollections.observableArrayList(itens));
    }
    
    public void carregarChoiceBoxCidade() {
        LinkedList cidades = new LinkedList();
        Iterator it = controller.iteratorPontos();
        
        while (it.hasNext()) {
            Ponto ponto = (Ponto) ((Vertex) it.next()).getData();

            if (ponto instanceof Cidade) {
                cidades.add((Cidade) ponto);
            }
        }
        
        choiceBoxCidade.setItems(FXCollections.observableArrayList(cidades));
    }
        
    @FXML
    void adicionarImagem(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de Imagem","*.png", "*.jpg", "*.gif", "*.jpeg"));
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
            imagensEstabelecimento.add(image);
        });
        
        carregarListViewImagens();
    }
    
    private void carregarListViewImagens() {
        LinkedList<ImageView> imagens = new LinkedList<>();
        for(Image a: imagensEstabelecimento){
            ImageView image = new ImageView(a);
            image.setFitHeight(140);
            image.setFitWidth(250);
            imagens.add(image);
        }
        ObservableList<ImageView> itens = FXCollections.observableArrayList(imagens);
        listViewImagens.setItems(itens);
    }
    
    @FXML
    void salvarEstabelecimento(ActionEvent event) throws IOException {
        TipoEstabelecimento tipoEstabelecimento = choiceBoxTipo.getValue();
        Cidade cidade = choiceBoxCidade.getValue();
        
        if(tipoEstabelecimento != null && cidade != null && !textFieldNome.getText().equals("")){
            if(estabelecimento == null){
                try {
                    controller.adicionarEstabelecimento(textFieldNome.getText(), tipoEstabelecimento, cidade, imagensEstabelecimento);
                } catch (DadoDuplicadoException ex) { }
                controllerTela.carregarAnchorPaneCadastrarEstabelecimento(null);
            }else{
                estabelecimento.setNome(textFieldNome.getText());
                controllerTela.carregarAnchorPaneSelecionarEstabelecimento();
            }
        } else{
            labelMensagemErro.setText("Erro, algum campo não foi informado");
        }
    }

    @FXML
    void cancelarSalvamentoEstabelecimento(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }
}
