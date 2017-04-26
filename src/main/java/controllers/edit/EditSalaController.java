package controllers.edit;

import java.io.IOException;

import classes.deanery.Sala;
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

public class EditSalaController {

	private MainController mainController;
	private Wydzial readWydzial;
	private Sala readSala;
	private ObservableList<Wydzial> dataWydzial = FXCollections.observableArrayList();
	private ObservableList<Sala> dataSala = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Sala> chooseSala;
	
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
	public void chooseDataSala() {
		readSala = (Sala) chooseSala.getValue();
		nameField.setText(readSala.getNazwa());
	}
	
	@FXML
	public void edit() {
		if ( (nameField.getText().length() != 0) && readWydzial != null && readSala != null ) {
			Sala sala = new Sala(readSala.getIdSala(), nameField.getText(), readWydzial.getIdWydzial());
			try {
				mainController.oos.writeObject("editsala");
				mainController.oos.writeObject(sala);
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
	
	public void wczytajDaneSala() {
		try {
			mainController.oos.writeObject("showsala");
			while(true) {
				try {
					readSala = (Sala) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readSala == null)
					break;
				else
					dataSala.add(new Sala(readSala.getIdSala(), readSala.getNazwa(), readSala.getNazwaWydzial(), readSala.getidWydzial()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseSala.setItems(dataSala);
	}
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
