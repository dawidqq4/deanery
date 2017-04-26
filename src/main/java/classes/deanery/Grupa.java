package classes.deanery;

import java.io.Serializable;

public class Grupa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idGrupa, idRok, rocznik;
	private String nazwaGrupa;
	
	public Grupa(int idGrupa, String nazwaGrupa, int rocznik, int idRok) {
		this.idGrupa = idGrupa;
		this.nazwaGrupa = nazwaGrupa;
		this.idRok = idRok;
		this.rocznik = rocznik;
	}
	
	public Grupa(int idGrupa, String nazwaGrupa, int rocznik) {
		this.idGrupa = idGrupa;
		this.nazwaGrupa = nazwaGrupa;
		this.rocznik = rocznik;
	}
	
	public Grupa(String nazwaGrupa, int idRok) {
		this.nazwaGrupa = nazwaGrupa;
		this.idRok = idRok;
	}
	
	public Grupa() {
	}

	public int getidGrupa() {
		return idGrupa;		
	}
	
	public String getnazwaGrupa() {
		return nazwaGrupa;		
	}
	
	public int getRocznik() {
		return rocznik;		
	}
	
	public int getidRok() {
		return idRok;		
	}
		
	@Override
	public String toString() {
		return "Grupa: " + getnazwaGrupa() + "\n";
	}
}
