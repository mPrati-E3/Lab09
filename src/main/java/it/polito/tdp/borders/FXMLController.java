package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Integer> dropAnno;

    @FXML
    private Button btnCalcolaConfini;

    @FXML
    private ChoiceBox<String> dropStato;

    @FXML
    private Button btnTrovaTuttiVicini;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {

    }

    @FXML
    void doTrovaTuttiVicini(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert dropAnno != null : "fx:id=\"dropAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcolaConfini != null : "fx:id=\"btnCalcolaConfini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert dropStato != null : "fx:id=\"dropStato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTrovaTuttiVicini != null : "fx:id=\"btnTrovaTuttiVicini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel (Model m) {
    	this.model=m;
    	
    	for (int i=1816; i<=2016; i++) {
    		dropAnno.getItems().add(i);
    	}
    	dropAnno.setValue(1816);
    	
    	List<Country> C = this.model.getAllCountry();
    	for (Country c : C) {
    		dropStato.getItems().add(c.getStateNme());
    	}
    	dropStato.setValue(C.get(0).getStateNme());
    	
    }
}
