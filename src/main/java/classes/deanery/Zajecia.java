package classes.deanery;

import java.io.Serializable;

public class Zajecia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idZajecia, idSala, idPrzedmiot, idWykladowca;
	private String godzina, nazwaSala, nazwaPrzedmiot, nazwiskoWykladowca;
	
	public Zajecia(int idZajecia, String godzina, int idWykladowca, int idPrzedmiot, int idSala) {
		this.idZajecia = idZajecia;
		this.godzina = godzina;
		this.idWykladowca = idWykladowca;
		this.idPrzedmiot = idPrzedmiot;
		this.idSala = idSala;
	}
	
	public Zajecia(int idZajecia, String godzina, String nazwiskoWykladowca, String nazwaPrzedmiot, String nazwaSala) {
		this.idZajecia = idZajecia;
		this.godzina = godzina;
		this.nazwiskoWykladowca = nazwiskoWykladowca;
		this.nazwaPrzedmiot = nazwaPrzedmiot;
		this.nazwaSala = nazwaSala;
	}
	
	public Zajecia(int idZajecia, String godzina, int idWykladowca, String nazwiskoWykladowca, int idPrzedmiot, String nazwaPrzedmiot, int idSala, String nazwaSala) {
		this.idZajecia = idZajecia;
		this.godzina = godzina;
		this.idWykladowca = idWykladowca;
		this.nazwiskoWykladowca = nazwiskoWykladowca;
		this.idPrzedmiot = idPrzedmiot;
		this.nazwaPrzedmiot = nazwaPrzedmiot;
		this.idSala = idSala;
		this.nazwaSala = nazwaSala;
	}
	
	public Zajecia(String godzina, int idWykladowca, int idPrzedmiot, int idSala ) {
		this.godzina = godzina;
		this.idWykladowca = idWykladowca;
		this.idPrzedmiot = idPrzedmiot;
		this.idSala = idSala;
	}
	
	public Zajecia(String godzina, String nazwiskoWykladowca, String nazwaPrzedmiot, String nazwaSala) {
		this.godzina = godzina;
		this.nazwaPrzedmiot = nazwaPrzedmiot;
		this.nazwiskoWykladowca = nazwiskoWykladowca;
		this.nazwaSala = nazwaSala;
	}
	
	public Zajecia() {
	}

	public int getIdZajecia() {
		return idZajecia;		
	}
	
	public String getGodzina() {
		return godzina;		
	}
	
	public int getIdSala() {
		return idSala;		
	}
	
	public String getNazwaSala() {
		return nazwaSala;		
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
		return "Zajecia: " + getGodzina() + " " + getNazwaSala() +" " + getNazwaPrzedmiot() + " " + getNazwiskoWykladowca() + "\n";
	}
}
