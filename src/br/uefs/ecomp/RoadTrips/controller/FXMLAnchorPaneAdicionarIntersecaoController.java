package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import br.uefs.ecomp.RoadTrips.model.Intersecao;
import br.uefs.ecomp.RoadTrips.model.Ponto;
import br.uefs.ecomp.RoadTrips.model.TipoIntersecao;
import br.uefs.ecomp.RoadTrips.util.Edge;
import br.uefs.ecomp.RoadTrips.util.MaskTextField;
import br.uefs.ecomp.RoadTrips.util.Vertex;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLAnchorPaneAdicionarIntersecaoController implements Initializable {
    @FXML
    private Label labelTitulo;
    @FXML
    private Label labelMensagemErro;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldLatitude;
    @FXML
    private TextField textFieldLongitude;
    @FXML
    private TextField textFieldDistancia;
    @FXML
    private ChoiceBox<TipoIntersecao> choiceBoxTipo;
    @FXML
    private ChoiceBox<Ponto> choiceBoxPontos;
    @FXML
    private TableView<PontoLigado> tableViewPontos;
    @FXML
    private TableColumn<PontoLigado, String> tableColumnNome;
    @FXML
    private TableColumn<PontoLigado, String> tableColumnTipo;
    @FXML
    private TableColumn<PontoLigado, Double> tableColumnDistancia;

    private RoadTripsController controller;
    private FXMLTelaInicialController controllerTela;
    private boolean temDoisOuMaisPontosLigados = false;
    private LinkedList<PontoLigado> pontosLigados = new LinkedList<>();
    private Intersecao intersecao;
    

    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setControllerTela(FXMLTelaInicialController controllerTela) {
        this.controllerTela = controllerTela;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskTextField.maskNumeroReal(textFieldDistancia);
        
        carregarChoiceBoxTipo();
    }
    
    public void carregarEdicao(Intersecao intersecao) {
        this.intersecao = intersecao;
        
        labelTitulo.setText("Editar Interseção");
        labelTitulo.setAlignment(Pos.CENTER);
        textFieldLatitude.setEditable(false);
        textFieldLongitude.setEditable(false);
        
        textFieldNome.setText(intersecao.getNome());
        textFieldLongitude.setText(((Double) intersecao.getLongitude()).toString());
        textFieldLatitude.setText(((Double) intersecao.getLatitude()).toString());
        choiceBoxTipo.setValue(intersecao.getTipoIntersecao());
        
        temDoisOuMaisPontosLigados = true;
        
        Vertex vertex = null;
        try {
            vertex = controller.buscarVerticePonto(intersecao);
        } catch (DadoNaoEncontradoException ex) { }
        Iterator itEdge = vertex.iteratorEdge();
        
        while(itEdge.hasNext()){
            PontoLigado pontoLigado = null;
            Edge e = (Edge) itEdge.next();
            
            if(e.getVertexA().equals(vertex)){
                pontoLigado = new PontoLigado((Ponto) e.getVertexB().getData(), e.getPeso());
            } else{
                pontoLigado = new PontoLigado((Ponto) e.getVertexA().getData(), e.getPeso());
            }
            
            pontoLigado.setRotaCriada(true);
            pontosLigados.add(pontoLigado);
        }
        
        carregarTableViewPontos();
    }
    
    public void carregarChoiceBoxTipo() {
        LinkedList itens = new LinkedList();
        itens.add(TipoIntersecao.cruzamento);
        itens.add(TipoIntersecao.rotula);
        itens.add(TipoIntersecao.semaforo);
        choiceBoxTipo.setTooltip(new Tooltip("Selecione o tipo da interseção"));
        choiceBoxTipo.setItems(FXCollections.observableArrayList(itens));
    }
    
    public void carregarChoiceBoxPontos() {
        LinkedList pontos = new LinkedList();
        Iterator it = controller.iteratorPontos();
        
        while(it.hasNext()){
            Ponto ponto = (Ponto) ((Vertex) it.next()).getData();
            pontos.add(ponto);
        }
        
        choiceBoxPontos.setItems(FXCollections.observableArrayList(pontos));
    }

    private void carregarTableViewPontos() {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tableColumnNome.setStyle("-fx-alignment: CENTER;");
        tableColumnTipo.setCellValueFactory(new PropertyValueFactory("tipo"));        
        tableColumnTipo.setStyle("-fx-alignment: CENTER;");
        tableColumnDistancia.setCellValueFactory(new PropertyValueFactory("distancia"));        
        tableColumnDistancia.setStyle("-fx-alignment: CENTER;");
        
        ObservableList<PontoLigado> observableListPontosLigados = FXCollections.observableArrayList(pontosLigados);
        tableViewPontos.setItems(observableListPontosLigados);
    }
    
    @FXML
    void adicionarRota(ActionEvent event) {
        labelMensagemErro.setText("");
        Ponto ponto = (Ponto) choiceBoxPontos.getValue();
        
        if(ponto == null){
            labelMensagemErro.setText("Ponto não selecionado");
            return;
        } else if(textFieldDistancia.getText().equals("")){
            labelMensagemErro.setText("Distância não foi informada");
            return;
        }
        
        PontoLigado pontoLigado = new PontoLigado(ponto, Double.parseDouble(textFieldDistancia.getText()));
        pontosLigados.add(pontoLigado);
        textFieldDistancia.setText("");
        
        if(!ponto.equals(pontosLigados.get(0).getPonto())){
            temDoisOuMaisPontosLigados = true;
        }
        
        carregarTableViewPontos();
    }
    
    @FXML
    void salvarIntersecao(ActionEvent event) {
        if(validarEntradaDados()){
            if(labelTitulo.getText().equals("Adicionar Interseção")){
                try {
                    intersecao = controller.adicionarIntersecao((TipoIntersecao)choiceBoxTipo.getValue(), textFieldNome.getText(),
                            Double.parseDouble(textFieldLatitude.getText()), Double.parseDouble(textFieldLongitude.getText()));
                    
                } catch (DadoDuplicadoException ex) { }
            } else{
                
            }
            
            for(PontoLigado a: pontosLigados){
                try {
                    if(!a.isRotaCriada()){
                        controller.adicionarRota(intersecao, a.getPonto(), a.getDistancia());
                    }
                } catch (DadoNaoEncontradoException | DadoDuplicadoException ex) { }
            }
            
            try {
                controllerTela.carregarAnchorPaneAdicionarIntersecao(null);
            } catch (IOException ex) { }
        }
    }

    @FXML
    void cancelarSalvamentoIntersecao(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }

    public boolean validarEntradaDados() {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
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
        if(choiceBoxTipo.getValue() == null){
            msgErro += "Tipo inválido!\n";
        }
        
        if (!temDoisOuMaisPontosLigados){
            alerta.setHeaderText("Uma interseção necessita se ligar a pelo menos 2 pontos diferentes");
            alerta.show();
            return false;
        } else if(msgErro.length() == 0){
            return true;
        } else{
            alerta.setTitle("Erro no salvamento");
            alerta.setHeaderText("Campos inválidos, por favor, corrija...");
            alerta.setContentText(msgErro);
            alerta.show();
            return false;
        }
    }
    
    public class PontoLigado{
        private Ponto ponto;
        private double distancia;
        private boolean rotaCriada = false;

        public PontoLigado(Ponto ponto, double distancia) {
            this.ponto = ponto;
            this.distancia = distancia;
        }
        
        public Ponto getPonto() {
            return ponto;
        }

        public double getDistancia() {
            return distancia;
        }
        
        public String getNome() {
            return ponto.getNome();
        }
        
        public String getTipo() {
            return ponto instanceof Intersecao ? "Interseção" : "Cidade";
        }

        public boolean isRotaCriada() {
            return rotaCriada;
        }

        public void setRotaCriada(boolean rotaCriada) {
            this.rotaCriada = rotaCriada;
        }
    }
}
