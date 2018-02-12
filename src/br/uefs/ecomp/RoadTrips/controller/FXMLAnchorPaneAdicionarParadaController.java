package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.model.Ponto;
import br.uefs.ecomp.RoadTrips.model.Parada;
import br.uefs.ecomp.RoadTrips.util.Vertex;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * Classe controla a telinha de adicionar um parada na viagem.
 * <br>
 * <b>OBS.:</b> Poderá ter mais de uma tela de adicionar Parada aberta ao mesmo tempo
 * por isso é importante atribuir um <i>id</i> ao controller.
 */
public class FXMLAnchorPaneAdicionarParadaController implements Initializable {
    @FXML
    private ComboBox<Cidade> comboBoxCidades;
    @FXML
    private DatePicker datePickerChegada;
    @FXML
    private DatePicker datePickerPartida;

    private RoadTripsController controller;
    private int idController;
    private Parada parada;


    /**
     * Método setta o (@link br.uefs.ecomp.RoadTrips.controller.RoadTripsController 
     * controller} principal da aplicação.
     * @param controller Controller principal da aplicação.
     */
    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    /**
     * Método atribui o número de identificação do controller.
     * @param idController ID do controller.
     */
    public void setIdController(int idController) {
        this.idController = idController;
    }
    
    /**
     * Método inicializa os dados do FXML.
     * @param url Paramêtro padrão do JAVA.
     * @param rb Paramêtro padrão do JAVA.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    /**
     * Método é usado para carregar a tela para editar uma parada.
     * @param parada Parada a ser editada.
     */
    public void carregarEdicao(Parada parada) {
        this.parada = parada;
        comboBoxCidades.setValue(parada.getCidade());
        datePickerChegada.setValue(parada.getDataChegada());
        datePickerPartida.setValue(parada.getDataPartida());
    }
    
    /**
     * Método carrega o comboBox de seleção de cidade.
     */
    public void carregarComboBoxCidades() {
        Iterator it = controller.iteratorPontos();
        LinkedList<Cidade> cidades = new LinkedList<>();

        while (it.hasNext()) {
            Ponto ponto = (Ponto) ((Vertex) it.next()).getData();

            if (ponto instanceof Cidade) {
                cidades.add((Cidade) ponto);
            }
        }
        
        ObservableList<Cidade> observableListCidades = FXCollections.observableArrayList(cidades);
        comboBoxCidades.setItems(observableListCidades);
    }
    
    /**
     * Método retorna a cidade selecionada do comboBox.
     * @return Cidade selecionada.
     */
    public Cidade getCidadeSelecionada() {
        return comboBoxCidades.getValue();
    }
    
    /**
     * Método retorna a data de chegada definida no DatePicker.
     * @return Data de chegada definida.
     */
    public LocalDate getDataChegada() {
        return datePickerChegada.getValue();
    }
    
    /**
     * Método retorna a data de partida definida no DatePicker.
     * @return Data de partida definida.
     */
    public LocalDate getDataPartida() {
        return datePickerPartida.getValue();
    }
    
    /**
     * Método retorna o ID do controller.
     * @return ID do controller.
     */
    public int getIdController() {
        return idController;
    }
    
    /**
     * Método para moficar a edição da parada.
     */
    public void modificarParada() {
        parada.setCidade(comboBoxCidades.getValue());
        parada.setDataChegada(datePickerChegada.getValue());
        parada.setDataPartida(datePickerPartida.getValue());
    }
}
