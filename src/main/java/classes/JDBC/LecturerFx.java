package classes.JDBC;

import javafx.beans.property.SimpleStringProperty;

public class LecturerFx {
	
	private SimpleStringProperty imie;
	private SimpleStringProperty nazwisko;
	private SimpleStringProperty tytul;
	private SimpleStringProperty katedra;
	
	public LecturerFx(String imie, String nazwisko, String tytul, String katedra) {
		this.imie = new SimpleStringProperty(imie);
		this.nazwisko = new SimpleStringProperty(nazwisko);
		this.tytul = new SimpleStringProperty(tytul);
		this.katedra = new SimpleStringProperty(katedra);
	}
		
	public String getImie() {
		return imie.get();
	}
	
	public void setImie(String imie) {
		this.imie.set(imie);
	}
	
	public String getNazwisko() {
		return nazwisko.get();
	}
	
	public void setNazwisko(String nazwisko) {
		this.nazwisko.set(nazwisko);
	}
	
	public String getTytul() {
		return tytul.get();
	}
	
	public void setTytul(String tytul) {
		this.tytul.set(tytul);
	}
	
	public String getKatedra() {
		return katedra.get();
	}
	
	public void setKatedra(String katedra) {
		this.katedra.set(katedra);
	}

}
