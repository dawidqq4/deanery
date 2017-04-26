package controllers.edit;

import java.io.IOException;

import classes.deanery.Katedra;
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

public class EditKatedraController {

	private MainController mainController;
	private Wydzial readWydzial;
	private Katedra readKatedra;
	private ObservableList<Wydzial> dataWydzial = FXCollections.observableArrayList();
	private ObservableList<Katedra> dataKatedra = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Katedra> chooseKatedra;
	
	@FXML
	private ComboBox<Wydzial> chooseWydzial;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField, adresField;
	
	@FXML
	public void chooseDataWydzial() {
		readWydzial = (Wydzial) chooseWydzial.getValue();
	}
	
	@FXML
	public void chooseDataKatedra() {
		readKatedra = (Katedra) chooseKatedra.getValue();
		nameField.setText(readKatedra.getNazwa());
		adresField.setText(readKatedra.getAdres());
	}
	
	@FXML
	public void edit() {
		if ( (nameField.getText().length() != 0) && (adresField.getText().length() != 0) && readWydzial != null && readKatedra != null ) {
			Katedra kat = new Katedra(readKatedra.getidKatedra(), nameField.getText(), adresField.getText(), readWydzial.getIdWydzial());
			try {
				mainController.oos.writeObject("editkatedra");
				mainController.oos.writeObject(kat);
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
	
	public void wczytajDaneKatedra() {
		try {
			mainController.oos.writeObject("showkatedra");
			while(true) {
				try {
					readKatedra = (Katedra) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readKatedra == null)
					break;
				else
					dataKatedra.add(new Katedra(readKatedra.getidKatedra(), readKatedra.getNazwa(), readKatedra.getAdres(), readKatedra.getNazwaWydzial(), readKatedra.getidWydzial()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseKatedra.setItems(dataKatedra);
	}
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
