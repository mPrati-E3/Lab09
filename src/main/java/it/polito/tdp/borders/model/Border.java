package it.polito.tdp.borders.model;

//javabean che descrive un confine
public class Border {
	
	private Country A;
	private Country B;
	
	public Country getA() {
		return A;
	}
	public void setA(Country a) {
		A = a;
	}
	public Country getB() {
		return B;
	}
	public void setB(Country b) {
		B = b;
	}

	public Border(Country a, Country b) {
		super();
		A = a;
		B = b;
	}
	
	
	
	

}
