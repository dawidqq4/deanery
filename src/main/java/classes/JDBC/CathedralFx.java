package classes.JDBC;

import javafx.beans.property.SimpleStringProperty;

public class CathedralFx {
	
	private SimpleStringProperty nazwa;
	private SimpleStringProperty adres;
	private SimpleStringProperty wydzial;
	
	public CathedralFx(String nazwa, String adres, String wydzial) {
		this.nazwa = new SimpleStringProperty(nazwa);
		this.adres = new SimpleStringProperty(adres);
		this.wydzial = new SimpleStringProperty(wydzial);
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
	
	public String getWydzial() {
		return wydzial.get();
	}
	
	public void setWydzial(String wydzial) {
		this.wydzial.set(wydzial);
	}
}
