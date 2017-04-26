package classes.deanery;

import java.io.Serializable;

public class Katedra implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idKatedra, idWydzial;
	private String nazwa, adres, nazwaWydzial;
	
	public Katedra(String nazwa, String adres, int idWydzial) {
		this.nazwa = nazwa;
		this.adres = adres;
		this.idWydzial = idWydzial;
	}
	
	public Katedra(int idKatedra, String nazwa, String adres, int idWydzial) {
		this.idKatedra = idKatedra;
		this.nazwa = nazwa;
		this.adres = adres;
		this.idWydzial = idWydzial;
	}
	
	public Katedra(int idKatedra, String nazwa, String adres, String nazwaWydzial) {
		this.idKatedra = idKatedra;
		this.nazwa = nazwa;
		this.adres = adres;
		this.nazwaWydzial = nazwaWydzial;
	}
	
	public Katedra(int idKatedra, String nazwa, String adres, String nazwaWydzial, int idWydzial) {
		this.idKatedra = idKatedra;
		this.nazwa = nazwa;
		this.adres = adres;
		this.nazwaWydzial = nazwaWydzial;
		this.idWydzial = idWydzial;
	}
	
	public Katedra() {
	}

	public int getidKatedra() {
		return idKatedra;		
	}
	
	public String getNazwa() {
		return nazwa;		
	}
	
	public String getAdres() {
		return adres;
	}
	
	public String getNazwaWydzial() {
		return nazwaWydzial;		
	}
	
	public int getidWydzial() {
		return idWydzial;		
	}
		
	@Override
	public String toString() {
		return "Katedra: " + getNazwa() + "\n";
	}
}
