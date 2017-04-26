package classes.deanery;

import java.io.Serializable;

public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idStudent, idGrupa;
	private String nazwisko, imie, adres, nazwaGrupa;
	
	public Student(int idStudent, String nazwisko, String imie, String adres, String nazwaGrupa, int idGrupa) {
		this.idStudent = idStudent;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.adres = adres;
		this.nazwaGrupa = nazwaGrupa;
		this.idGrupa = idGrupa;
	}
	
	public Student(int idStudent, String nazwisko, String imie, String adres, String nazwaGrupa) {
		this.idStudent = idStudent;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.adres = adres;
		this.nazwaGrupa = nazwaGrupa;
	}
	
	public Student(String nazwisko, String imie, String adres, int idGrupa) {
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.adres = adres;
		this.idGrupa = idGrupa;
	}
	
	public Student() {
	}

	public int getidStudent() {
		return idStudent;		
	}
	
	public String getNazwisko() {
		return nazwisko;		
	}
	
	public String getImie() {
		return imie;		
	}
	
	public String getAdres() {
		return adres;		
	}
	
	public String getNazwaGrupa() {
		return nazwaGrupa;		
	}
	
	public int getidGrupa() {
		return idGrupa;		
	}
		
	@Override
	public String toString() {
		return "Student: " + getNazwisko() + " " + getImie() + " " + getAdres() + "\n";
	}
}
