package classes.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import classes.deanery.*;
 
public class DziekanatDAO {
 
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:D:\\databases\\java.db";
 
    private Connection conn;
	private Statement stat;
 
    public DziekanatDAO() {
        try {
            Class.forName(DziekanatDAO.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
    }
 
 
    // dziekan
    public boolean insertDziekan(Dziekan dziekan) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO dziekan VALUES (NULL, ?, ?, ?);");
            prepStmt.setString(1, dziekan.getNazwisko());
            prepStmt.setString(2, dziekan.getImie());
            prepStmt.setString(3, dziekan.getTytul());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Dziekan> selectDziekan() {
        LinkedList<Dziekan> dziekani = new LinkedList<Dziekan>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM dziekan"
            								  + " WHERE dziekan.id_dziekan > 0;");
            int id;
            String nazwisko, imie, tytul;
            while(result.next()) {
                id = result.getInt("id_dziekan");
                nazwisko = result.getString("nazwisko");
                imie = result.getString("imie");
                tytul = result.getString("tytul");
                dziekani.add(new Dziekan(id,nazwisko, imie, tytul));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return dziekani;
    }
        
    public boolean editDziekan(Dziekan dz) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE dziekan "
        													 + "SET nazwisko = ?, "
        													 + "imie = ?, "
        													 + "tytul = ? "
        													 + "WHERE dziekan.id_dziekan = " + dz.getidDziekan() + ";");
            prepStmt.setString(1, dz.getNazwisko());
            prepStmt.setString(2, dz.getImie());
            prepStmt.setString(3, dz.getTytul());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteDziekan(Dziekan dz) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM dziekan "
        													 + "WHERE dziekan.id_dziekan = " + dz.getidDziekan() + ";");
        	prepStmt.execute();
   
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //wydzial
    public boolean insertWydzial(Wydzial wydzial) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO wydzial VALUES (NULL, ?, ?, ?);");
            prepStmt.setString(1, wydzial.getNazwa());
            prepStmt.setString(2, wydzial.getAdres());
            prepStmt.setInt(3, wydzial.getIdDziekan());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Wydzial> selectWydzial() {
        LinkedList<Wydzial> wydzialy = new LinkedList<Wydzial>();
        try {
            ResultSet result = stat.executeQuery("SELECT wydzial.id_wydzial, wydzial.nazwa, wydzial.adres, dziekan.nazwisko"
            								+ "	  FROM dziekan"
            								+ "   JOIN wydzial"
            								+ "	  ON (dziekan.id_dziekan = wydzial.id_dziekan"
            								+ "	  AND wydzial.id_wydzial > 0);");
            int id;
            String nazwa, adres, dzNazwisko;
            while(result.next()) {
            	id = result.getInt("id_wydzial");
                nazwa = result.getString("nazwa");
                adres = result.getString("adres");
                dzNazwisko = result.getString("nazwisko");
                wydzialy.add(new Wydzial(id,nazwa,adres,dzNazwisko,6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return wydzialy;
    }
    
    public boolean editWydzial(Wydzial w) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE wydzial "
        													 + "SET nazwa = ?, "
        													 + "adres = ?, "
        													 + "id_dziekan = ? "
        													 + "WHERE wydzial.id_wydzial = " + w.getIdWydzial() + ";");
            prepStmt.setString(1, w.getNazwa());
            prepStmt.setString(2, w.getAdres());
            prepStmt.setInt(3, w.getIdDziekan());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteWydzial(Wydzial wy) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM wydzial "
        													 + "WHERE wydzial.id_wydzial = " + wy.getIdWydzial() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //kierunek
    public boolean insertKierunek(Kierunek kierunek) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO kierunek VALUES (NULL, ?, ?);");
            prepStmt.setString(1, kierunek.getNazwa());
            prepStmt.setInt(2, kierunek.getidWydzial());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Kierunek> selectKierunek() {
        LinkedList<Kierunek> kierunki = new LinkedList<Kierunek>();
        try {
            ResultSet result = stat.executeQuery("SELECT kierunek.id_kierunek, kierunek.nazwa, wydzial.nazwa as wn"
            								+ "	  FROM wydzial,kierunek"
            								+ "	  WHERE wydzial.id_wydzial = kierunek.id_wydzial"
            								+ "   AND kierunek.id_kierunek > 0;");
            int id;
            String kierNazwa, wNazwa;
            while(result.next()) {
            	id = result.getInt("id_kierunek");
                kierNazwa = result.getString("nazwa");
                wNazwa = result.getString("wn");
                kierunki.add(new Kierunek(id,kierNazwa,wNazwa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return kierunki;
    }
    
    public boolean editKierunek(Kierunek k) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE kierunek "
        													 + "SET nazwa = ?, "
        													 + "id_wydzial = ? "
        													 + "WHERE kierunek.id_kierunek = " + k.getidKierunek() + ";");
            prepStmt.setString(1, k.getNazwa());
            prepStmt.setInt(2, k.getidWydzial());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteKierunek(Kierunek k) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM kierunek "
        													 + "WHERE kierunek.id_kierunek = " + k.getidKierunek() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
  //rok
    public boolean insertRok(Rok rok) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO rok VALUES (NULL, ?, ?);");
            prepStmt.setInt(1, rok.getRocznik());
            prepStmt.setInt(2, rok.getidKierunek());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Rok> selectRok() {
        LinkedList<Rok> roczniki = new LinkedList<Rok>();
        try {
            ResultSet result = stat.executeQuery("SELECT rok.id_rok, rok.rocznik, kierunek.nazwa"
            								+ "	  FROM kierunek,rok"
            								+ "	  WHERE kierunek.id_kierunek = rok.id_kierunek"
            								+ "	  AND rok.id_rok > 0;");
            int id, rocznik;
            String kNazwa;
            while(result.next()) {
            	id = result.getInt("id_rok");
            	rocznik = result.getInt("rocznik");
                kNazwa = result.getString("nazwa");
                roczniki.add(new Rok(id,rocznik,kNazwa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return roczniki;
    }
    
    public boolean editRok(Rok r) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE rok "
        													 + "SET rocznik = ?, "
        													 + "id_kierunek = ? "
        													 + "WHERE rok.id_rok = " + r.getidRok() + ";");
        	prepStmt.setInt(1, r.getRocznik());
            prepStmt.setInt(2, r.getidKierunek());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteRok(Rok r) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM rok "
        													 + "WHERE rok.id_rok = " + r.getidRok() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //grupa
    public boolean insertGrupa(Grupa grupa) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO grupa VALUES (NULL, ?, ?);");
            prepStmt.setString(1, grupa.getnazwaGrupa());
            prepStmt.setInt(2, grupa.getidRok());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Grupa> selectGrupa() {
        LinkedList<Grupa> grupy = new LinkedList<Grupa>();
        try {
            ResultSet result = stat.executeQuery("SELECT grupa.id_grupa, grupa.nazwa, rok.rocznik"
            								+ "	  FROM rok,grupa "
            								+ "	  WHERE rok.id_rok = grupa.id_rok"
            								+ "   AND grupa.id_grupa > 0;");
            int id, rocznik;
            String grupaNazwa;
            while(result.next()) {
            	id = result.getInt("id_grupa");
            	grupaNazwa = result.getString("nazwa");
            	rocznik = result.getInt("rocznik");
                grupy.add(new Grupa(id,grupaNazwa,rocznik));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return grupy;
    }
    
    public boolean editGrupa(Grupa g) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE grupa "
        													 + "SET nazwa = ?, "
        													 + "id_rok = ? "
        													 + "WHERE grupa.id_grupa = " + g.getidGrupa() + ";");
        	prepStmt.setString(1, g.getnazwaGrupa());
            prepStmt.setInt(2, g.getidRok());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteGrupa(Grupa g) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM grupa "
        													 + "WHERE grupa.id_grupa = " + g.getidGrupa() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
  //student
    public boolean insertStudent(Student student) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO student VALUES (NULL, ?, ?, ?, ?);");
            prepStmt.setString(1, student.getNazwisko());
            prepStmt.setString(2, student.getImie());
            prepStmt.setString(3, student.getAdres());
            prepStmt.setInt(4, student.getidGrupa());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Student> selectStudent() {
        LinkedList<Student> studenci = new LinkedList<Student>();
        try {
            ResultSet result = stat.executeQuery("SELECT student.id_student, student.nazwisko, student.imie, student.adres, grupa.nazwa"
            								+ "	  FROM grupa,student "
            								+ "	  WHERE grupa.id_grupa = student.id_grupa"
            								+ "   AND student.id_student > 0;");
            int id;
            String nazwisko, imie, adres, grupaNazwa;
            while(result.next()) {
            	id = result.getInt("id_student");
            	nazwisko = result.getString("nazwisko");
            	imie = result.getString("imie");
            	adres = result.getString("adres");
            	grupaNazwa = result.getString("nazwa");
            	studenci.add(new Student(id,nazwisko,imie,adres,grupaNazwa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return studenci;
    }
    
    public boolean editStudent(Student s) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE student "
        													 + "SET nazwisko = ?, "
        													 + "imie = ?,"
        													 + "adres = ?,"
        													 + "id_grupa = ? "
        													 + "WHERE student.id_student = " + s.getidStudent() + ";");
        	prepStmt.setString(1, s.getNazwisko());
        	prepStmt.setString(2, s.getImie());
        	prepStmt.setString(3, s.getAdres());
            prepStmt.setInt(4, s.getidGrupa());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
  
    public boolean deleteStudent(Student s) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM student "
        													 + "WHERE student.id_student = " + s.getidStudent() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //katedra
    public boolean insertKatedra(Katedra k) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO katedra VALUES (NULL, ?, ?, ?);");
            prepStmt.setString(1, k.getNazwa());
            prepStmt.setString(2, k.getAdres());
            prepStmt.setInt(3, k.getidWydzial());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Katedra> selectKatedra() {
        LinkedList<Katedra> katedry = new LinkedList<Katedra>();
        try {
            ResultSet result = stat.executeQuery("SELECT katedra.id_katedra, katedra.nazwa, katedra.adres, wydzial.nazwa AS 'nazwa_wydzial' "
            								+ "	  FROM katedra,wydzial "
            								+ "	  WHERE katedra.id_wydzial = wydzial.id_wydzial"
            								+ "   AND katedra.id_katedra > 0;");
            int id;
            String nazwa, adres, wydzialNazwa;
            while(result.next()) {
            	id = result.getInt("id_katedra");
            	nazwa = result.getString("nazwa");
            	adres = result.getString("adres");
            	wydzialNazwa = result.getString("nazwa_wydzial");
            	katedry.add(new Katedra(id,nazwa,adres,wydzialNazwa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return katedry;
    }
    
    public boolean editKatedra(Katedra k) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE katedra "
        													 + "SET nazwa = ?, "
        													 + "adres = ?,"
        													 + "id_wydzial = ? "
        													 + "WHERE katedra.id_katedra = " + k.getidKatedra() + ";");
        	prepStmt.setString(1, k.getNazwa());
        	prepStmt.setString(2, k.getAdres());
        	prepStmt.setInt(3, k.getidWydzial());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteKatedra(Katedra k) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM katedra "
        													 + "WHERE katedra.id_katedra = " + k.getidKatedra() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //przedmiot
    public boolean insertPrzedmiot(Przedmiot prz) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO przedmiot VALUES (NULL, ?, ?);");
            prepStmt.setString(1, prz.getNazwa());
            prepStmt.setInt(2, prz.getWaga());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Przedmiot> selectPrzedmiot() {
        LinkedList<Przedmiot> przedmioty = new LinkedList<Przedmiot>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM przedmiot"
            								  + " WHERE przedmiot.id_przedmiot > 0;");
            int id, waga;
            String nazwa;
            while(result.next()) {
                id = result.getInt("id_przedmiot");
                nazwa = result.getString("nazwa");
                waga = result.getInt("waga");
                przedmioty.add(new Przedmiot(id, nazwa, waga));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return przedmioty;
    }
    
    public boolean editPrzedmiot(Przedmiot prz) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE przedmiot "
        													 + "SET nazwa = ?, "
        													 + "waga = ? "
        													 + "WHERE przedmiot.id_przedmiot = " + prz.getIdPrzedmiot() + ";");
            prepStmt.setString(1, prz.getNazwa());
            prepStmt.setInt(2, prz.getWaga());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deletePrzedmiot(Przedmiot prz) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM przedmiot "
        													 + "WHERE przedmiot.id_przedmiot = " + prz.getIdPrzedmiot() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //wykladowca
    public boolean insertWykladowca(Wykladowca wyk) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO wykladowca VALUES (NULL, ?, ?, ?, ?);");
            prepStmt.setString(1, wyk.getNazwisko());
            prepStmt.setString(2, wyk.getImie());
            prepStmt.setString(3, wyk.getTytul());
            prepStmt.setInt(4, wyk.getIdKatedra());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Wykladowca> selectWykladowca() {
        LinkedList<Wykladowca> wykladowcy = new LinkedList<Wykladowca>();
        try {
            ResultSet result = stat.executeQuery("SELECT wykladowca.id_wykladowca, wykladowca.nazwisko, wykladowca.imie, wykladowca.tytul, katedra.nazwa AS 'katedra_nazwa'"
            								+ "	  FROM wykladowca,katedra "
            								+ "	  WHERE katedra.id_katedra = wykladowca.id_katedra"
            								+ "   AND wykladowca.id_wykladowca > 0;");
            int id;
            String nazwisko, imie, tytul, katedraNazwa;
            while(result.next()) {
            	id = result.getInt("id_wykladowca");
            	nazwisko = result.getString("nazwisko");
            	imie = result.getString("imie");
            	tytul = result.getString("tytul");
            	katedraNazwa = result.getString("katedra_nazwa");
            	wykladowcy.add(new Wykladowca(id,nazwisko,imie,tytul,katedraNazwa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return wykladowcy;
    }
    
    public boolean editWykladowca(Wykladowca wyk) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE wykladowca "
        													 + "SET nazwisko = ?, "
        													 + "imie = ?, "
        													 + "tytul = ?, "
        													 + "id_katedra = ? "
        													 + "WHERE wykladowca.id_wykladowca = " + wyk.getIdWykladowca() + ";");
            prepStmt.setString(1, wyk.getNazwisko());
            prepStmt.setString(2, wyk.getImie());
            prepStmt.setString(3, wyk.getTytul());
            prepStmt.setInt(4, wyk.getIdKatedra());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteWykladowca(Wykladowca wyk) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM wykladowca "
        													 + "WHERE wykladowca.id_wykladowca = " + wyk.getIdWykladowca() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //sala
    public boolean insertSala(Sala sala) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO sala VALUES (NULL, ?, ?);");
            prepStmt.setString(1, sala.getNazwa());
            prepStmt.setInt(2, sala.getidWydzial());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Sala> selectSala() {
        LinkedList<Sala> sale = new LinkedList<Sala>();
        try {
            ResultSet result = stat.executeQuery("SELECT sala.id_sala, sala.nazwa, wydzial.nazwa as wn"
            								+ "	  FROM wydzial,sala"
            								+ "	  WHERE wydzial.id_wydzial = sala.id_wydzial"
            								+ "   AND sala.id_sala > 0;");
            int id;
            String nazwa, wydzialNazwa;
            while(result.next()) {
            	id = result.getInt("id_sala");
            	nazwa = result.getString("nazwa");
            	wydzialNazwa = result.getString("wn");
                sale.add(new Sala(id,nazwa,wydzialNazwa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return sale;
    }
    
    public boolean editSala(Sala sala) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE sala "
        													 + "SET nazwa = ?, "
        													 + "id_wydzial = ? "
        													 + "WHERE sala.id_sala = " + sala.getIdSala() + ";");
            prepStmt.setString(1, sala.getNazwa());
            prepStmt.setInt(2, sala.getidWydzial());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteSala(Sala sala) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM sala "
        													 + "WHERE sala.id_sala = " + sala.getIdSala() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //ocena
    public boolean insertOcena(Ocena ocena) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO ocena VALUES (NULL, ?, ?, ?, ?);");
            prepStmt.setInt(1, ocena.getOcena());
            prepStmt.setInt(2, ocena.getIdStudent());
            prepStmt.setInt(3, ocena.getIdPrzedmiot());
            prepStmt.setInt(4, ocena.getIdWykladowca());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Ocena> selectOcena() {
        LinkedList<Ocena> oceny = new LinkedList<Ocena>();
        try {
            ResultSet result = stat.executeQuery("SELECT ocena.id_ocena, ocena.ocena, student.id_student, student.nazwisko, przedmiot.id_przedmiot ,przedmiot.nazwa, wykladowca.id_wykladowca ,wykladowca.nazwisko AS 'wykladowca_nazwisko'"
            								+ "	  FROM ocena,student,przedmiot,wykladowca "
            								+ "	  WHERE ocena.id_student = student.id_student"
            								+ "   AND ocena.id_przedmiot = przedmiot.id_przedmiot"
            								+ "   AND ocena.id_wykladowca = wykladowca.id_wykladowca;");
            int id, idStudent, idPrzedmiot, idWykladowca, ocena;
            String nazwisko, nazwa, nazwiskoWykladowca;
            while(result.next()) {
            	id = result.getInt("id_ocena");
            	ocena = result.getInt("ocena");
            	idStudent = result.getInt("id_student");
            	nazwisko = result.getString("nazwisko");
            	idPrzedmiot = result.getInt("id_przedmiot");
            	nazwa = result.getString("nazwa");
            	idWykladowca = result.getInt("id_wykladowca");
            	nazwiskoWykladowca = result.getString("wykladowca_nazwisko");
            	oceny.add(new Ocena(id,ocena,idStudent,nazwisko,idPrzedmiot,nazwa,idWykladowca,nazwiskoWykladowca));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return oceny;
    }
    
    public boolean editOcena(Ocena ocena) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE ocena "
        													 + "SET ocena = ?, "
        													 + "id_student = ?, "
        													 + "id_przedmiot = ?, "
        													 + "id_wykladowca = ? "
        													 + "WHERE ocena.id_ocena = " + ocena.getIdOCena() + ";");
        	prepStmt.setInt(1, ocena.getOcena());
            prepStmt.setInt(2, ocena.getIdStudent());
            prepStmt.setInt(3, ocena.getIdPrzedmiot());
            prepStmt.setInt(4, ocena.getIdWykladowca());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteOcena(Ocena ocena) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM ocena "
        													 + "WHERE ocena.id_ocena = " + ocena.getIdOCena() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //zajecia
    public boolean insertZajecia(Zajecia zajecia) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO zajecia VALUES (NULL, ?, ?, ?, ?);");
            prepStmt.setString(1, zajecia.getGodzina());
            prepStmt.setInt(2, zajecia.getIdWykladowca());
            prepStmt.setInt(3, zajecia.getIdPrzedmiot());
            prepStmt.setInt(4, zajecia.getIdSala());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<Zajecia> selectZajecia() {
        LinkedList<Zajecia> zajecia = new LinkedList<Zajecia>();
        try {
            ResultSet result = stat.executeQuery("SELECT zajecia.id_zajecia, zajecia.godzina_rozpoczecia, wykladowca.nazwisko, przedmiot.nazwa, sala.nazwa AS 'sala_nazwa'"
            								+ "	  FROM zajecia, wykladowca, przedmiot, sala"
            								+ "	  WHERE zajecia.id_wykladowca = wykladowca.id_wykladowca"
            								+ "   AND zajecia.id_przedmiot = przedmiot.id_przedmiot"
            								+ "   AND zajecia.id_sala = sala.id_sala;");
            int id;
            String godzina, wykladowcaNazwisko, przedmiotNazwa, salaNazwa;
            while(result.next()) {
            	id = result.getInt("id_zajecia");
            	godzina = result.getString("godzina_rozpoczecia");
            	wykladowcaNazwisko = result.getString("nazwisko");
            	przedmiotNazwa = result.getString("nazwa");
            	salaNazwa = result.getString("sala_nazwa");
            	zajecia.add(new Zajecia(id,godzina,wykladowcaNazwisko,przedmiotNazwa,salaNazwa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return zajecia;
    }
    
    public boolean editZajecia(Zajecia zajecia) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE zajecia "
        													 + "SET godzina_rozpoczecia = ?, "
        													 + "id_wykladowca = ?, "
        													 + "id_przedmiot = ?, "
        													 + "id_sala = ? "
        													 + "WHERE zajecia.id_zajecia = " + zajecia.getIdZajecia() + ";");
        	prepStmt.setString(1, zajecia.getGodzina());
            prepStmt.setInt(2, zajecia.getIdWykladowca());
            prepStmt.setInt(3, zajecia.getIdPrzedmiot());
            prepStmt.setInt(4, zajecia.getIdSala());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteZajecia(Zajecia zajecia) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM zajecia "
        													 + "WHERE zajecia.id_zajecia = " + zajecia.getIdZajecia() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //Users
    public boolean insertUser(User user) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO users VALUES (NULL, ?, ?, 0);");
            prepStmt.setString(1, user.getLogin());
            prepStmt.setString(2, user.getPassword());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu");
            return false;
        }
        return true;
    }
    
    public LinkedList<User> selectUsers() {
        LinkedList<User> users = new LinkedList<User>();
    	try {
            ResultSet result = stat.executeQuery("SELECT *"
            								   + "FROM users;");
            int id, approved;
            String login, password;
            while(result.next()) {
            	id = result.getInt("id_user");
            	login = result.getString("login");
            	password = result.getString("password");
            	approved = result.getInt("approved");
            	users.add(new User(id,login,password,approved));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean confrimUser(User user) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("UPDATE users "
        													 + "SET approved = ? "
        													 + "WHERE users.id_user = " + user.getIdUser() + ";");
            prepStmt.setInt(1, 1);
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteUser(User user) {
        try {
        	PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM users "
        													 + "WHERE users.id_user = " + user.getIdUser() + ";");
        	prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
}