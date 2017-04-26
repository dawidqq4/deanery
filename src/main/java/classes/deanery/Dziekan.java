package classes.deanery;

import java.io.Serializable;

public class Dziekan implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idDziekan;
	private String nazwisko,imie,tytul;
	
	public Dziekan(int idDziekan, String nazwisko, String imie, String tytul) {
		this.idDziekan = idDziekan;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.tytul = tytul;
	}
	
	public Dziekan(String nazwisko, String imie, String tytul) {
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.tytul = tytul;
	}
	
	public Dziekan() {
	}

	public int getidDziekan() {
		return idDziekan;		
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
	
	@Override
	public String toString() {
		return "Dziekan: " + getNazwisko() + "\n";
	}
}
