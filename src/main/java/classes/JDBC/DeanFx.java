package classes.JDBC;

import javafx.beans.property.SimpleStringProperty;

public class DeanFx {
	
	private SimpleStringProperty nazwisko;
	private SimpleStringProperty imie;
	private SimpleStringProperty tytul;
	
	public DeanFx(String nazwisko, String imie, String tytul) {
		this.nazwisko = new SimpleStringProperty(nazwisko);
		this.imie = new SimpleStringProperty(imie);
		this.tytul = new SimpleStringProperty(tytul);
	}
	
	public String getNazwisko() {
		return nazwisko.get();
	}
	
	public void setNazwisko(String nazwisko) {
		this.nazwisko.set(nazwisko);
	}
	
	public String getImie() {
		return imie.get();
	}
	
	public void setImie(String imie) {
		this.imie.set(imie);
	}
	
	public String getTytul() {
		return tytul.get();
	}
	
	public void setTytul(String tytul) {
		this.tytul.set(tytul);
	}
}
