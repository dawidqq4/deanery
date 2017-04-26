package classes.JDBC;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SubjectFx {

	private SimpleStringProperty nazwa;
	private SimpleIntegerProperty waga;
	
	public SubjectFx(String nazwa, int waga) {
		this.nazwa = new SimpleStringProperty(nazwa);
		this.waga = new SimpleIntegerProperty(waga);
	}
	
	public String getNazwa() {
		return nazwa.get();
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa.set(nazwa);
	}
	
	public int getWaga() {
		return waga.get();
	}
	
	public void setWaga(int waga) {
		this.waga.set(waga);
	}
}
