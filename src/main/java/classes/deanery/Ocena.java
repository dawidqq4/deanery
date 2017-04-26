package classes.deanery;

import java.io.Serializable;

public class Ocena implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idOcena, idStudent, idPrzedmiot, idWykladowca, ocena;
	private String nazwiskoStudent, nazwaPrzedmiot, nazwiskoWykladowca;
	
	public Ocena(int idOcena, int ocena, int idStudent, int idPrzedmiot, int idWykladowca) {
		this.idOcena = idOcena;
		this.ocena = ocena;
		this.idStudent = idStudent;
		this.idPrzedmiot = idPrzedmiot;
		this.idWykladowca = idWykladowca;
	}
	
	public Ocena(int idOcena, int ocena, String nazwiskoStudent, String nazwaPrzedmiot, String nazwiskoWykladowca) {
		this.idOcena = idOcena;
		this.ocena = ocena;
		this.nazwiskoStudent = nazwiskoStudent;
		this.nazwaPrzedmiot = nazwaPrzedmiot;
		this.nazwiskoWykladowca = nazwiskoWykladowca;
	}
	
	public Ocena(int idOcena, int ocena, int idStudent, String nazwiskoStudent, int idPrzedmiot, String nazwaPrzedmiot, int idWykladowca, String nazwiskoWykladowca) {
		this.idOcena = idOcena;
		this.ocena = ocena;
		this.idStudent = idStudent;
		this.nazwiskoStudent = nazwiskoStudent;
		this.idPrzedmiot = idPrzedmiot;
		this.nazwaPrzedmiot = nazwaPrzedmiot;
		this.idWykladowca = idWykladowca;
		this.nazwiskoWykladowca = nazwiskoWykladowca;
	}
	
	public Ocena(int ocena, int idStudent, int idPrzedmiot, int idWykladowca) {
		this.ocena = ocena;
		this.idStudent = idStudent;
		this.idPrzedmiot = idPrzedmiot;
		this.idWykladowca = idWykladowca;
	}
	
	public Ocena(int ocena, String nazwiskoStudent, String nazwaPrzedmiot, String nazwiskoWykladowca) {
		this.ocena = ocena;
		this.nazwiskoStudent = nazwiskoStudent;
		this.nazwaPrzedmiot = nazwaPrzedmiot;
		this.nazwiskoWykladowca = nazwiskoWykladowca;
	}
	
	public Ocena() {
	}

	public int getIdOCena() {
		return idOcena;		
	}
	
	public int getOcena() {
		return ocena;		
	}
	
	public int getIdStudent() {
		return idStudent;		
	}
	
	public String getNazwiskoStudent() {
		return nazwiskoStudent;		
	}
	
	public int getIdPrzedmiot() {
		return idPrzedmiot;		
	}
	
	public String getNazwaPrzedmiot() {
		return nazwaPrzedmiot;		
	}
	
	public int getIdWykladowca() {
		return idWykladowca;		
	}
	
	public String getNazwiskoWykladowca() {
		return nazwiskoWykladowca;		
	}
		
	@Override
	public String toString() {
		return "Ocena: " + getOcena() + " " + getNazwaPrzedmiot() + " " + getNazwiskoStudent() + " " + getNazwiskoWykladowca() + "\n";
	}
}
