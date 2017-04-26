package controllers.edit;

import java.io.IOException;

import classes.deanery.Kierunek;
import classes.deanery.Wydzial;
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

public class EditKierunekController {

	private MainController mainController;
	private Wydzial readWydzial;
	private Kierunek readKierunek;
	private ObservableList<Wydzial> dataWydzial = FXCollections.observableArrayList();
	private ObservableList<Kierunek> dataKierunek = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Kierunek> chooseKierunek;
	
	@FXML
	private ComboBox<Wydzial> chooseWydzial;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField;
	
	@FXML
	public void chooseDataWydzial() {
		readWydzial = (Wydzial) chooseWydzial.getValue();
	}
	
	@FXML
	public void chooseDataKierunek() {
		readKierunek = (Kierunek) chooseKierunek.getValue();
		nameField.setText(readKierunek.getNazwa());
	}
	
	@FXML
	public void edit() {
		if ( (nameField.getText().length() != 0) && readWydzial != null && readKierunek != null ) {
			Kierunek kier = new Kierunek(readKierunek.getidKierunek(), nameField.getText(), readWydzial.getIdWydzial());
			try {
				mainController.oos.writeObject("editkier");
				mainController.oos.writeObject(kier);
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
	
	public void wczytajDaneWydzial() {
		try {
			mainController.oos.writeObject("showwy");
			while(true) {
				try {
					readWydzial = (Wydzial) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readWydzial == null)
					break;
				else
					dataWydzial.add(new Wydzial(readWydzial.getIdWydzial(), readWydzial.getNazwa(), readWydzial.getAdres(), readWydzial.getIdDziekan()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseWydzial.setItems(dataWydzial);
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
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
