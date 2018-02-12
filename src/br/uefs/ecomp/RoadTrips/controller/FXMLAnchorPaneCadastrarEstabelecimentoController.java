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

/**
 * Classe controla a tela de adição de um novo estabelecimento ao sistema, 
 * usada também para editar um estabelecimento.
 */
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
    

    /**
     * Método setta o {@link br.uefs.ecomp.RoadTrips.controller.RoadTripsController 
     * controller} principal da aplicação.
     * @param controller Controller principal da aplicação.
     */
    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

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
        listViewImagens.setOrientation(Orientation.HORIZONTAL);
        
        carregarChoiceBoxTipo();
    }   
    
    /**
     * Método é usado para carregar a tela para editar uma estabelecimento.
     * @param estabelecimento Estabelecimento a ser editada.
     */
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
    
    /**
     * Método carregar a ChoiceBox para seleção do tipo do estabelecimento.
     */
    public void carregarChoiceBoxTipo() {
        LinkedList itens = new LinkedList();
        itens.add(TipoEstabelecimento.LugaresParaComer);
        choiceBoxTipo.setTooltip(new Tooltip("Selecione o tipo do estabelecimento"));
        choiceBoxTipo.setItems(FXCollections.observableArrayList(itens));
    }
    
    /**
     * Método carregar a ChoiceBox para seleção da cidade do estabelecimento.
     */
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
       
    /**
     * Método disparado por um ActionEvent que possibilita ao usuário adionar imagens 
     * do estabelecimento.
     * @param event Evento que disparou o método.
     */
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
    
    /**
     * Método carregar o ListView de imagens.
     */
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
    
    /**
     * Método disparado por um ActionEvent que salva o estabelecimento. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de adição ou a de seleção de estabelecimento 
     * não consiga ser carregada.
     */
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

    /**
     * Método disparado por um ActionEvent que cancela o salvamento do estabelecimento. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de minhas viagens ou a de seleção de estabelecimento 
     * não consiga ser carregada.
     */
    @FXML
    void cancelarSalvamentoEstabelecimento(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }
}
