package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	//restituisce tutti gli stati
	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Country c = new Country (
						rs.getString("StateAbb"), 
						rs.getInt("ccode"),  
						rs.getString("StateNme"));
				
				result.add(c);
				
				
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	//restituisce tutti i confini di un determinato stato in un determinato anno
	public List<Border> loadYearBorder(Integer value, Map<Integer, Country> Map) {
		
		String sql = "SELECT state1no, state2no\r\n"
				+ "FROM countries.contiguity\r\n"
				+ "WHERE countries.contiguity.year=?";
		
		List<Border> B = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, value);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Border b = new Border(
						Map.get(rs.getInt("state1no")),
						Map.get(rs.getInt("state2no")));
				
				B.add(b);
				
				
			}
			
			conn.close();
			return B;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
