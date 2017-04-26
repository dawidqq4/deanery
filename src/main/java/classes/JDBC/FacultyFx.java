package classes.JDBC;

import javafx.beans.property.SimpleStringProperty;

public class FacultyFx {
	
	private SimpleStringProperty nazwa;
	private SimpleStringProperty adres;
	private SimpleStringProperty nazwisko;
	
	public FacultyFx(String nazwa, String adres, String nazwisko) {
		this.nazwa = new SimpleStringProperty(nazwa);
		this.adres = new SimpleStringProperty(adres);
		this.nazwisko = new SimpleStringProperty(nazwisko);
	}
	
	public String getNazwa() {
		return nazwa.get();
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa.set(nazwa);
	}
	
	public String getAdres() {
		return adres.get();
	}
	
	public void setAdres(String adres) {
		this.adres.set(adres);
	}
	
	public String getNazwisko() {
		return nazwisko.get();
	}
	
	public void setNazwisko(String nazwisko) {
		this.nazwisko.set(nazwisko);
	}
}
