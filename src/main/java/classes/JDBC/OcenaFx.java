package classes.JDBC;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OcenaFx {

	private SimpleIntegerProperty ocena;
	private SimpleStringProperty student;
	private SimpleStringProperty przedmiot;
	private SimpleStringProperty wykladowca;
	
	public OcenaFx(int ocena, String student, String przedmiot, String wykladowca) {
		this.ocena = new SimpleIntegerProperty(ocena);
		this.student = new SimpleStringProperty(student);
		this.przedmiot = new SimpleStringProperty(przedmiot);
		this.wykladowca = new SimpleStringProperty(wykladowca);
	}
	
	public int getOcena() {
		return ocena.get();
	}
	
	public void setOcena(int ocena) {
		this.ocena.set(ocena);
	}
	
	public String getStudent() {
		return student.get();
	}
	
	public void setStudent(String student) {
		this.student.set(student);
	}

	public String getPrzedmiot() {
		return przedmiot.get();
	}
	
	public void setPrzedmiot(String przedmiot) {
		this.przedmiot.set(przedmiot);
	}
	
	public String getWykladowca() {
		return wykladowca.get();
	}
	
	public void setWykladowca(String wykladowca) {
		this.wykladowca.set(wykladowca);
	}
}
