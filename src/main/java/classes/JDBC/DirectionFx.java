package classes.JDBC;

import javafx.beans.property.SimpleStringProperty;

public class DirectionFx {

	private SimpleStringProperty nazwa;
	private SimpleStringProperty wydzial;
	
	public DirectionFx(String nazwa, String wydzial) {
		this.nazwa = new SimpleStringProperty(nazwa);
		this.wydzial = new SimpleStringProperty(wydzial);
	}
	
	public String getNazwa() {
		return nazwa.get();
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa.set(nazwa);
	}
	
	public String getWydzial() {
		return wydzial.get();
	}
	
	public void setWydzial(String wydzial) {
		this.wydzial.set(wydzial);
	}
}
