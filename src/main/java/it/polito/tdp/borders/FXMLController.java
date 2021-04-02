package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

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
    
    //stampa tutti i confini esistenti nell'anno dato
    private void StampaCalcolaConfini (Graph<Country, DefaultEdge> graph) {
    	
    	txtResult.clear();
    	txtResult.appendText("Borders in the year: "+dropAnno.getValue()+"\n\n");
    	for (DefaultEdge de : graph.edgeSet()) {
    		txtResult.appendText(graph.getEdgeSource(de).getStateNme()+" - ");
    		txtResult.appendText(graph.getEdgeTarget(de).getStateNme()+"\n");
    	}
    	
    }
    
    //stampa tutti gli stati che confinano con un determinato stato nell'anno dato
    private void StampaTuttiVicini (List<Country> C) {
    	
    	txtResult.clear();
    	
    	if (C.isEmpty()) {
    		txtResult.appendText("No countries near "+dropStato.getValue()+
        			" in year "+dropAnno.getValue()+" \n\n");
    		return;
    	}
    	
    	txtResult.appendText("List of countries near "+dropStato.getValue()+
    			" in year "+dropAnno.getValue()+" \n\n");
    	for (Country c : C) {
    		txtResult.appendText(c.getStateNme()+"\n");
    	}

    	
    }

    //chiama il model per creare un grafo che rappresenti i confini in un determinato anno
    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	Graph<Country, DefaultEdge> graph = this.model.getCalcolaConfini(dropAnno.getValue());
    	
    	if (dropAnno.getValue()==null) {
    		txtResult.appendText("Year value can not be null! \n");
    		return;
    	} 
    	
    	if (graph==null) {
    		txtResult.appendText("The graph is empty! \n");
    		return;
    	} 
    	
    	this.StampaCalcolaConfini(graph);

    }

    //chiama il model per ottenere una lista di stati che confinano con uno stato dato in un determinato anno
    @FXML
    void doTrovaTuttiVicini(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	List <Country> C = this.model.getTuttiVicini(dropStato.getValue(),dropAnno.getValue());
    	
    	if (dropStato.getValue()==null) {
    		txtResult.appendText("Country value can not be null! \n");
    		return;
    	}
    	
    	if (dropAnno.getValue()==null) {
    		txtResult.appendText("Year value can not be null! \n");
    		return;
    	} 
    	
    	if (C==null) {
    		txtResult.appendText("Country list is empty! \n");
    		return;
    	}
    	
    	this.StampaTuttiVicini(C);

    }

    @FXML
    void initialize() {
        assert dropAnno != null : "fx:id=\"dropAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcolaConfini != null : "fx:id=\"btnCalcolaConfini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert dropStato != null : "fx:id=\"dropStato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTrovaTuttiVicini != null : "fx:id=\"btnTrovaTuttiVicini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    //setto il model e inserisco i valori nelle drop
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
