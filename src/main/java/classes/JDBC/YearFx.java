package classes.JDBC;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class YearFx {

	private SimpleIntegerProperty rok;
	private SimpleStringProperty kierunek;
	
	public YearFx(int rok, String kierunek) {
		this.rok = new SimpleIntegerProperty(rok);
		this.kierunek = new SimpleStringProperty(kierunek);
	}
	
	public int getRok() {
		return rok.get();
	}
	
	public void setRok(int rok) {
		this.rok.set(rok);
	}
	
	public String getKierunek() {
		return kierunek.get();
	}
	
	public void setKierunek(String kierunek) {
		this.kierunek.set(kierunek);
	}
}
