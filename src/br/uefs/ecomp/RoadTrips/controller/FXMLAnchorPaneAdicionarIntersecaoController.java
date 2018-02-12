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

/**
 * Classe controla a tela de adição de uma nova interseção ao sistema, usada 
 * também para editar uma interseção.
 */
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
        MaskTextField.maskNumeroReal(textFieldDistancia);
        
        carregarChoiceBoxTipo();
    }
    
    /**
     * Método é usado para carregar a tela para editar uma interseção.
     * @param intersecao Intersecao a ser editada.
     */
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
    
    /**
     * Método carregar a ChoiceBox para seleção do tipo de interseção.
     */
    public void carregarChoiceBoxTipo() {
        LinkedList itens = new LinkedList();
        itens.add(TipoIntersecao.cruzamento);
        itens.add(TipoIntersecao.rotula);
        itens.add(TipoIntersecao.semaforo);
        choiceBoxTipo.setTooltip(new Tooltip("Selecione o tipo da interseção"));
        choiceBoxTipo.setItems(FXCollections.observableArrayList(itens));
    }
    
    /**
     * Método carregar a ChoiceBox para seleção de pontos para adicionar uma aresta.
     */
    public void carregarChoiceBoxPontos() {
        LinkedList pontos = new LinkedList();
        Iterator it = controller.iteratorPontos();
        
        while(it.hasNext()){
            Ponto ponto = (Ponto) ((Vertex) it.next()).getData();
            pontos.add(ponto);
        }
        
        choiceBoxPontos.setItems(FXCollections.observableArrayList(pontos));
    }

    /**
     * Método carrega a TableView de arestas da interseção.
     */
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
    
    /**
     * Método disparado por um ActionEvent que adiciona uma aresta a interseção.
     * @param event Evento que disparou o método.
     */
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
    
    /**
     * Método disparado por um ActionEvent que salva a interseção. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de adição ou a de seleção de interseção não consiga ser carregada.
     */
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
    
    /**
     * Método disparado por um ActionEvent que cancela o salvamento da interseção. 
     * @param event Evento que disparou o método.
     * @throws IOException Caso a tela de minhas viagens ou a de seleção de interseção 
     * não consiga ser carregada.
     */
    @FXML
    void cancelarSalvamentoIntersecao(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneMinhasViagens();
    }

    //Método que verifica se os dados da interseção estão preenchidos corretamente para poderem ser salvos.
    private boolean validarEntradaDados() {
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
    
    /**
     * Classe interna de {@code FXMLAnchorPaneAdicionarIntersecaoController} para salvar 
     * e depois criar arestas a interseção.
     */
    public class PontoLigado{
        private Ponto ponto;
        private double distancia;
        private boolean rotaCriada = false;

        /**
         * Constroi um PontoLigado com dados passados.
         * @param ponto
         * @param distancia 
         */
        public PontoLigado(Ponto ponto, double distancia) {
            this.ponto = ponto;
            this.distancia = distancia;
        }
        
        /**
         * Método retorna o ponto da aresta.
         * @return Ponto da aresta.
         */
        public Ponto getPonto() {
            return ponto;
        }

        /**
         * Método retorna a distância (peso) da interseção até o ponto.
         * @return Distância da interseção até o ponto.
         */
        public double getDistancia() {
            return distancia;
        }
        
        /**
         * Método retorna o nome ponto da aresta.
         * @return Nome ponto da aresta.
         */
        public String getNome() {
            return ponto.getNome();
        }
        
        /**
         * Método retorna o tipo ponto da aresta.
         * @return Tipo ponto da aresta.
         */
        public String getTipo() {
            return ponto instanceof Intersecao ? "Interseção" : "Cidade";
        }

        /**
         * Método verifica se a aresta já existe.
         * @return True se a aresta já existe.
         */
        public boolean isRotaCriada() {
            return rotaCriada;
        }

        /**
         * Método modifica o valor de RotaCriada.
         * @param rotaCriada Novo valor de RotaCriada.
         */
        public void setRotaCriada(boolean rotaCriada) {
            this.rotaCriada = rotaCriada;
        }
    }
}
