package classes.deanery;

import java.io.Serializable;

public class Wykladowca implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idWykladowca, idKatedra;
	private String nazwisko, imie, tytul, nazwaKatedra;
	
	public Wykladowca(int idWykladowca, String nazwisko, String imie, String tytul, String nazwaKatedra, int idKatedra) {
		this.idWykladowca = idWykladowca;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.tytul = tytul;
		this.nazwaKatedra = nazwaKatedra;
		this.idKatedra = idKatedra;
	}
	
	public Wykladowca(int idWykladowca, String nazwisko, String imie, String tytul, String nazwaKatedra) {
		this.idWykladowca = idWykladowca;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.tytul = tytul;
		this.nazwaKatedra = nazwaKatedra;
	}
	
	public Wykladowca(String nazwisko, String imie, String tytul, int idKatedra) {
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.tytul = tytul;
		this.idKatedra = idKatedra;
	}
	
	public Wykladowca() {
	}

	public int getIdWykladowca() {
		return idWykladowca;		
	}
	
	public String getNazwisko() {
		return nazwisko;		
	}
	
	public String getImie() {
		return imie;		
	}
	
	public String getTytul() {
		return tytul;		
	}
	
	public String getNazwaKatedra() {
		return nazwaKatedra;		
	}
	
	public int getIdKatedra() {
		return idKatedra;		
	}
		
	@Override
	public String toString() {
		return "Wyk³adowca: " + getNazwisko() + "\n";
	}
}
