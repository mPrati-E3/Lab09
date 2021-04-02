package it.polito.tdp.borders.model;

import java.util.List;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao;

	public Model() {
		
		dao = new BordersDAO();
	
	}

	public List<Country> getAllCountry() {
		return dao.loadAllCountries();
	}

}
