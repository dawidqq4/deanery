package classes.deanery;

import java.io.Serializable;

public class Sala implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idSala, idWydzial;
	private String nazwa, nazwaWydzial;
	
	public Sala(String nazwa, int idWydzial) {
		this.nazwa = nazwa;
		this.idWydzial = idWydzial;
	}
	
	public Sala(int idSala, String nazwa, int idWydzial) {
		this.idSala = idSala;
		this.nazwa = nazwa;
		this.idWydzial = idWydzial;
	}
	
	public Sala(int idSala, String nazwa, String nazwaWydzial) {
		this.idSala = idSala;
		this.nazwa = nazwa;
		this.nazwaWydzial = nazwaWydzial;
	}
	
	public Sala(int idSala, String nazwa, String nazwaWydzial, int idWydzial) {
		this.idSala = idSala;
		this.nazwa = nazwa;
		this.nazwaWydzial = nazwaWydzial;
		this.idWydzial = idWydzial;
	}
	
	public Sala() {
	}

	public int getIdSala() {
		return idSala;		
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
		return "Sala: " + getNazwa() + "\n";
	}
}
