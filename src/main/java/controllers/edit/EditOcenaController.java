package controllers.edit;

import java.io.IOException;

import classes.deanery.Ocena;
import classes.deanery.Przedmiot;
import classes.deanery.Student;
import classes.deanery.Wykladowca;
import controllers.navigation.MainController;
import controllers.navigation.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditOcenaController {
	
	private MainController mainController;
	private Student readStudent;
	private Przedmiot readPrzedmiot;
	private Wykladowca readWykladowca;
	private Ocena readOcena;
	private ObservableList<Student> dataStudent = FXCollections.observableArrayList();
	private ObservableList<Przedmiot> dataPrzedmiot = FXCollections.observableArrayList();
	private ObservableList<Wykladowca> dataWykladowca = FXCollections.observableArrayList();
	private ObservableList<Ocena> dataOcena = FXCollections.observableArrayList();
	private int ocenaToInt = 0;
	
	@FXML
	private ComboBox<Ocena> chooseOcena;
	
	@FXML
	private ComboBox<Student> chooseStd;
	
	@FXML
	private ComboBox<Przedmiot> choosePrz;
	
	@FXML
	private ComboBox<Wykladowca> chooseWyk;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField ocenaField;
	
	@FXML
	public void chooseStudent() {
		readStudent = (Student) chooseStd.getValue();
	}
	
	@FXML
	public void choosePrzedmiot() {
		readPrzedmiot = (Przedmiot) choosePrz.getValue();
	}
	
	@FXML
	public void chooseWykladowca() {
		readWykladowca = (Wykladowca) chooseWyk.getValue();
	}
	
	@FXML
	public void chooseDataOcena() {
		readOcena = (Ocena) chooseOcena.getValue();
		ocenaField.setText(Integer.toString(readOcena.getOcena()));
	}
	
	public boolean isStringInt(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	@FXML
	public void edit() {
		if (isStringInt(ocenaField.getText()) == true)
			ocenaToInt = Integer.parseInt(ocenaField.getText());
		else {
			statusLabel.setText("B³êdny rocznik");
			ocenaToInt = 0;
		}
		if ((ocenaToInt > 1) && (ocenaToInt < 6) && readStudent != null && readPrzedmiot != null && readWykladowca != null && readOcena != null) {
			Ocena oc = new Ocena(readOcena.getIdOCena(), ocenaToInt, readStudent.getidStudent(), readPrzedmiot.getIdPrzedmiot(), readWykladowca.getIdWykladowca());
			try {
				mainController.oos.writeObject("editocena");
				mainController.oos.writeObject(oc);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Zmodyfikowano dane");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			backToMenu();
		}
		else
			statusLabel.setText("Podano b³êdne dane");	
	}
	
	@FXML 
	public void backToMenu() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/navigationScreens/MenuScreen.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MenuController menuController = loader.getController();
		menuController.setMainController(mainController);
		mainController.setScreen(anchorPane); 	
	}
	
	public void wczytajDaneOcena() {
		try {
			mainController.oos.writeObject("showocena");
			while(true) {
				try {
					readOcena = (Ocena) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readOcena == null)
					break;
				else
					dataOcena.add(new Ocena(readOcena.getIdOCena(), readOcena.getOcena(), readOcena.getNazwiskoStudent(), readOcena.getNazwaPrzedmiot(), readOcena.getNazwiskoWykladowca()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseOcena.setItems(dataOcena);
	}
	
	public void wczytajDaneStudent() {
		try {
			mainController.oos.writeObject("showstudent");
			while (true) {
				try {
					readStudent = (Student) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readStudent == null)
					break;
				else 
					dataStudent.add(new Student(readStudent.getidStudent(), readStudent.getNazwisko(), readStudent.getImie(), readStudent.getAdres(), readStudent.getNazwaGrupa(), readStudent.getidGrupa()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseStd.setItems(dataStudent);
	}
	
	public void wczytajDanePrzedmiot() {
		try {
			mainController.oos.writeObject("showprzedmiot");
			while (true) {
				try {
					readPrzedmiot = (Przedmiot) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readPrzedmiot == null)
					break;
				else
					dataPrzedmiot.add(new Przedmiot(readPrzedmiot.getIdPrzedmiot(), readPrzedmiot.getNazwa(), readPrzedmiot.getWaga()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		choosePrz.setItems(dataPrzedmiot);
	}

	public void wczytajDaneWykladowca() {
		try {
			mainController.oos.writeObject("showwykladowca");
			while (true) {
				try {
					readWykladowca = (Wykladowca) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readWykladowca == null)
					break;
				else
					dataWykladowca.add(new Wykladowca(readWykladowca.getIdWykladowca(), readWykladowca.getNazwisko(), readWykladowca.getImie(), readWykladowca.getTytul(), readWykladowca.getNazwaKatedra(), readWykladowca.getIdKatedra()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseWyk.setItems(dataWykladowca);
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
