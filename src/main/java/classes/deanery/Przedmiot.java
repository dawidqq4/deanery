package classes.deanery;

import java.io.Serializable;

public class Przedmiot implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idPrzedmiot, waga;
	private String nazwa;

	public Przedmiot(int idPrzedmiot, String nazwa, int waga) {
		this.idPrzedmiot = idPrzedmiot;
		this.nazwa = nazwa;
		this.waga = waga;
	}

	public Przedmiot(String nazwa, int waga) {
		this.nazwa = nazwa;
		this.waga = waga;
	}
	
	public Przedmiot() {
	}

	public int getIdPrzedmiot() {
		return idPrzedmiot;
	}

	public String getNazwa() {
		return nazwa;
	}

	public int getWaga() {
		return waga;
	}

	@Override
	public String toString() {
		return "Przedmiot: " + getNazwa() + "\n";
	}
}
