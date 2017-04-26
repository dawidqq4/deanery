package classes.JDBC;

import javafx.beans.property.SimpleStringProperty;

public class LessonFx {

	private SimpleStringProperty godzina;
	private SimpleStringProperty wykladowca;
	private SimpleStringProperty przedmiot;
	private SimpleStringProperty sala;
	
	public LessonFx(String godzina, String wykladowca, String przedmiot, String sala) {
		this.godzina = new SimpleStringProperty(godzina);
		this.wykladowca = new SimpleStringProperty(wykladowca);
		this.przedmiot = new SimpleStringProperty(przedmiot);
		this.sala = new SimpleStringProperty(sala);
	}
	
	public String getGodzina() {
		return godzina.get();
	}
	
	public void setGodzina(String godzina) {
		this.godzina.set(godzina);
	}
			
	public String getWykladowca() {
		return wykladowca.get();
	}
	
	public void setWykladowca(String wykladowca) {
		this.wykladowca.set(wykladowca);
	}
	
	public String getPrzedmiot() {
		return przedmiot.get();
	}
	
	public void setPrzedmiot(String przedmiot) {
		this.przedmiot.set(przedmiot);
	}
	
	public String getSala() {
		return sala.get();
	}
	
	public void setSala(String sala) {
		this.sala.set(sala);
	}
}
