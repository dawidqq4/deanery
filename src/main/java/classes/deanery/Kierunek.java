package classes.deanery;

import java.io.Serializable;

public class Kierunek implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idKierunek, idWydzial;
	private String nazwa, nazwaWydzial;
	
	public Kierunek(String nazwa, int idWydzial) {
		this.nazwa = nazwa;
		this.idWydzial = idWydzial;
	}
	
	public Kierunek(int idKierunek, String nazwa, int idWydzial) {
		this.idKierunek = idKierunek;
		this.nazwa = nazwa;
		this.idWydzial = idWydzial;
	}
	
	public Kierunek(int idKierunek, String nazwa, String nazwaWydzial) {
		this.idKierunek = idKierunek;
		this.nazwa = nazwa;
		this.nazwaWydzial = nazwaWydzial;
	}
	
	public Kierunek(int idKierunek, String nazwa, String nazwaWydzial, int idWydzial) {
		this.idKierunek = idKierunek;
		this.nazwa = nazwa;
		this.nazwaWydzial = nazwaWydzial;
		this.idWydzial = idWydzial;
	}
	
	public Kierunek() {
	}

	public int getidKierunek() {
		return idKierunek;		
	}
	
	public String getNazwa() {
		return nazwa;		
	}
	
	public String getNazwaWydzial() {
		return nazwaWydzial;		
	}
	
	public int getidWydzial() {
		return idWydzial;		
	}
		
	@Override
	public String toString() {
		return "Kierunek: " + getNazwa() + "\n";
	}
}
