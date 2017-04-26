package classes.deanery;

import java.io.Serializable;

public class Wydzial implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idWydzial;
	private String nazwa,adres,nazwiskoDziekan;
	private int idDziekan;
	
	public Wydzial(String nazwa, String adres) {
		this.nazwa = nazwa;
		this.adres = adres;
	}
	
	public Wydzial(int idWydzial, String nazwa, String adres, int idDziekan) {
		this.idWydzial = idWydzial;
		this.nazwa = nazwa;
		this.adres = adres;
		this.idDziekan = idDziekan;
	}
	
	public Wydzial(int idWydzial, String nazwa, String adres, String nazwiskoDziekan, int idDziekan) {
		this.idWydzial = idWydzial;
		this.nazwa = nazwa;
		this.adres = adres;
		this.nazwiskoDziekan = nazwiskoDziekan;
		this.idDziekan = idDziekan;
	}
	
	public Wydzial(String nazwa, String adres, int idDziekan) {
		this.nazwa = nazwa;
		this.adres = adres;
		this.idDziekan = idDziekan;
	}
	
	public Wydzial(String nazwa, String adres, String nazwiskoDziekan) {
		this.nazwa = nazwa;
		this.adres = adres;
		this.nazwiskoDziekan = nazwiskoDziekan;
	}
	
	public Wydzial() {
	}

	public int getIdWydzial() {
		return idWydzial;		
	}
	
	public String getNazwa() {
		return nazwa;		
	}
	
	public String getAdres() {
		return adres;		
	}
	
	public int getIdDziekan() {
		return idDziekan;		
	}
	
	public String getNazwiskoDziekan() {
		return nazwiskoDziekan;		
	}
	
	@Override
	public String toString() {
		return "Wydzial: " + getNazwa() + "\n";
	}
}

