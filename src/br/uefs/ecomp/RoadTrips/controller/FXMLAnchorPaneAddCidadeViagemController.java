package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.model.Ponto;
import br.uefs.ecomp.RoadTrips.model.Viagem.CidadeViagem;
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
import javafx.scene.layout.AnchorPane;

public class FXMLAnchorPaneAddCidadeViagemController implements Initializable {
    @FXML
    private AnchorPane anchorPaneAdd;
    @FXML
    private ComboBox<Cidade> comboBoxCidades;
    @FXML
    private DatePicker datePickerChegada;
    @FXML
    private DatePicker datePickerPartida;

    private RoadTripsController controller;
    private int idController;
    private CidadeViagem cidadeViagem;


    public void setController(RoadTripsController controller) {
        this.controller = controller;
    }

    public void setIdController(int idController) {
        this.idController = idController;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void carregarEdicao(CidadeViagem cidadeViagem) {
        this.cidadeViagem = cidadeViagem;
        comboBoxCidades.setValue(cidadeViagem.getCidade());
        datePickerChegada.setValue(cidadeViagem.getDataChegada());
        datePickerPartida.setValue(cidadeViagem.getDataPartida());
    }
    
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
    
    public Cidade getCidadeSelecionada() {
        return comboBoxCidades.getValue();
    }
    
    public LocalDate getDataChegada() {
        return datePickerChegada.getValue();
    }
    
    public LocalDate getDataPartida() {
        return datePickerPartida.getValue();
    }
    
    public int getIdController() {
        return idController;
    }
    
    public void modificarCidadeViagem() {
        cidadeViagem.setCidade(comboBoxCidades.getValue());
        cidadeViagem.setDataChegada(datePickerChegada.getValue());
        cidadeViagem.setDataPartida(datePickerPartida.getValue());
    }
}
