package controllers.edit;

import java.io.IOException;

import classes.deanery.Kierunek;
import classes.deanery.Rok;
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

public class EditRocznikController {
	
	private MainController mainController;
	private Kierunek readKierunek;
	private Rok readRok;
	private ObservableList<Kierunek> dataKierunek = FXCollections.observableArrayList();
	private ObservableList<Rok> dataRok = FXCollections.observableArrayList();
	private int rokToInt = 0;
	
	@FXML
	private ComboBox<Rok> chooseRocznik;
	
	@FXML
	private ComboBox<Kierunek> chooseKierunek;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField rokField;
	
	@FXML
	public void chooseDataKierunek() {
		readKierunek = (Kierunek) chooseKierunek.getValue();
	}
	
	@FXML
	public void chooseDataRocznik() {
		readRok = (Rok) chooseRocznik.getValue();
		rokField.setText(Integer.toString(readRok.getRocznik()));
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
		if (isStringInt(rokField.getText()) == true)
			rokToInt = Integer.parseInt(rokField.getText());
		else {
			statusLabel.setText("B³êdny rocznik");
			rokToInt = 0;
		}
		if ((rokToInt > 1900) && (rokToInt < 2100) && readKierunek != null) {
			Rok r = new Rok(readRok.getidRok(), rokToInt, readKierunek.getidKierunek());
			try {
				mainController.oos.writeObject("editrok");
				mainController.oos.writeObject(r);
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
	
	public void wczytajDaneKierunek() {
		try {
			mainController.oos.writeObject("showkier");
			while(true) {
				try {
					readKierunek = (Kierunek) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readKierunek == null)
					break;
				else
					dataKierunek.add(new Kierunek(readKierunek.getidKierunek(), readKierunek.getNazwa(), readKierunek.getNazwaWydzial(), readKierunek.getidWydzial()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseKierunek.setItems(dataKierunek);
	}
	
	public void wczytajDaneRok() {
		try {
			mainController.oos.writeObject("showrok");
			while(true) {
				try {
					readRok = (Rok) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readRok == null)
					break;
				else
					dataRok.add(new Rok(readRok.getidRok(), readRok.getRocznik(), readRok.getidKierunek()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseRocznik.setItems(dataRok);
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
