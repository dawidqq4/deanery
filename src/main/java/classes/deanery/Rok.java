package classes.deanery;

import java.io.Serializable;

public class Rok implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idRok, idKierunek, rocznik;
	private String nazwaKierunek;
	
	public Rok(int idRok, int rocznik, int idKierunek) {
		this.idRok = idRok;
		this.rocznik = rocznik;
		this.idKierunek = idKierunek;
	}
	
	public Rok(int idRok, int rocznik, String nazwaKierunek) {
		this.idRok = idRok;
		this.rocznik = rocznik;
		this.nazwaKierunek = nazwaKierunek;
	}
	
	public Rok(int idRok, int rocznik, String nazwaKierunek, int idKierunek) {
		this.idRok = idRok;
		this.rocznik = rocznik;
		this.nazwaKierunek = nazwaKierunek;
		this.idKierunek = idKierunek;
	}
	
	public Rok(int rocznik, int idKierunek) {
		this.rocznik = rocznik;
		this.idKierunek = idKierunek;
	}
	
	public Rok() {
	}

	public int getidRok() {
		return idRok;		
	}
	
	public int getRocznik() {
		return rocznik;		
	}
	
	public String getNazwaKierunek() {
		return nazwaKierunek;		
	}
	
	public int getidKierunek() {
		return idKierunek;		
	}
		
	@Override
	public String toString() {
		return "Rocznik: " + getRocznik() + "\n";
	}
}
