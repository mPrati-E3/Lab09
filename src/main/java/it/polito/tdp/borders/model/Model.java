package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	//dichiaro gli stati (vertici)
	private List<Country> C;
	private Map<Integer, Country> MapC;
	
	//dichiaro i confini (archi)
	private List<Border> B;
	
	//dichiaro il grafo base
	Graph<Country, DefaultEdge> graph;
	
	//dichiaro il dao
	private BordersDAO dao;

	public Model() {
		
		//definisco il grafo base
		graph = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		
		//definisco il dao
		dao = new BordersDAO();
		
		//carico tutti gli stati nel grafo come vertici e li salvo anche nella mappa
		C = dao.loadAllCountries();
		MapC = new HashMap<Integer, Country>();
		for (Country c : C) {
			MapC.put(c.getCCode(), c);
			graph.addVertex(c);
		}
		

	}

	//restituisce tutti gli stati
	public List<Country> getAllCountry() {
		return C;
	}

	//restituisce tutti i confini in un determinato anno
	public Graph<Country, DefaultEdge> getCalcolaConfini(Integer value) {
		
		//chiedo al database tutti i confini di un detrminato anno e li inserisco nel grafo
		B = dao.loadYearBorder(value, MapC);
		for (Border b : B) {
			graph.addEdge(b.getA(), b.getB());
		}
		
		
		return graph;
	}

	//restituisce tutti gli stati che confinano con uno dato in un determinato anno
	public List<Country> getTuttiVicini(String stato, int year) {
		
		//chiedo al database tutti i confini di un detrminato anno e li inserisco nel grafo
		B = dao.loadYearBorder(year, MapC);
		for (Border b : B) {
			graph.addEdge(b.getA(), b.getB());
		}
		
		//cerco lo stato di interesse dell'utente
		Country SavedCountry = new Country();
		for (Country c : C) {
			if (c.getStateNme().equals(stato)) {
				SavedCountry=c;
				break;
			}
		}
		
		//lista di servizio che poi ritornerò
		List<Country> BOX = new ArrayList<Country>();
		
		//per ogni arco nel grafo, cerco lo stato di interesse dell'utente
		//se lo trovo, aggiungo alla lista il suo collegamento
		for (DefaultEdge de : graph.edgeSet()) {
					
			if (graph.getEdgeSource(de).getCCode()==SavedCountry.getCCode()) {
				BOX.add(graph.getEdgeTarget(de));
			}
			if (graph.getEdgeTarget(de).getCCode()==SavedCountry.getCCode()) {
				BOX.add(graph.getEdgeSource(de));
			}
		}
		
		
		return BOX;

		
	}

}
