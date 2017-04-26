package classes.JDBC;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GroupFx {

	private SimpleStringProperty nazwa;
	private SimpleIntegerProperty rok;
	
	public GroupFx(String nazwa, int rok) {
		this.nazwa = new SimpleStringProperty(nazwa);
		this.rok = new SimpleIntegerProperty(rok);
	}
		
	public String getNazwa() {
		return nazwa.get();
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa.set(nazwa);
	}
	
	public int getRok() {
		return rok.get();
	}
	
	public void setRok(int rok) {
		this.rok.set(rok);
	}
}
