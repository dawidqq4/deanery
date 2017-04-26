package classes.JDBC;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentFx {
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty imie;
	private SimpleStringProperty nazwisko;
	private SimpleStringProperty adres;
	private SimpleStringProperty grupa;
	
	public StudentFx(int id, String imie, String nazwisko, String adres, String grupa) {
		this.id = new SimpleIntegerProperty(id);
		this.imie = new SimpleStringProperty(imie);
		this.nazwisko = new SimpleStringProperty(nazwisko);
		this.adres = new SimpleStringProperty(adres);
		this.grupa = new SimpleStringProperty(grupa);
	}
	
	public int getId() {
		return id.get();
	}
	
	public void setId(int id) {
		this.id.set(id);
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
	
	public String getAdres() {
		return adres.get();
	}
	
	public void setAdres(String adres) {
		this.adres.set(adres);
	}
	
	public String getGrupa() {
		return grupa.get();
	}
	
	public void setGrupa(String grupa) {
		this.grupa.set(grupa);
	}
}
