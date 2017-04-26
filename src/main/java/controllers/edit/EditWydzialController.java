package controllers.edit;

import java.io.IOException;

import classes.deanery.Dziekan;
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

public class EditWydzialController {

	private MainController mainController;
	private Dziekan readDziekan;
	private Wydzial readWydzial;
	private ObservableList<Dziekan> dataDziekan = FXCollections.observableArrayList();
	private ObservableList<Wydzial> dataWydzial = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Dziekan> chooseDziekan;
	
	@FXML
	private ComboBox<Wydzial> chooseWydzial;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField, addressField;
	
	@FXML
	public void chooseDataWydzial() {
		readWydzial = (Wydzial) chooseWydzial.getValue();
		nameField.setText(readWydzial.getNazwa());
		addressField.setText(readWydzial.getAdres());
	}
	
	@FXML
	public void chooseDataDziekan() {
		readDziekan = (Dziekan) chooseDziekan.getValue();
	}
	
	@FXML
	public void edit() {
		if ( (nameField.getText().length() != 0) && (addressField.getText().length() != 0) && readDziekan != null && readWydzial != null ) {
			Wydzial wy = new Wydzial(readWydzial.getIdWydzial(), nameField.getText(), addressField.getText(), readDziekan.getidDziekan() );
			try {
				mainController.oos.writeObject("editwy");
				mainController.oos.writeObject(wy);
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
	
	public void wczytajDaneDziekan() {
		try {
			mainController.oos.writeObject("showdz");
			while(true) {
				try {
					readDziekan = (Dziekan) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readDziekan == null)
					break;
				else
					dataDziekan.add(new Dziekan(readDziekan.getidDziekan(),readDziekan.getNazwisko(),readDziekan.getImie(),readDziekan.getTytul()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseDziekan.setItems(dataDziekan);
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
					dataWydzial.add(new Wydzial(readWydzial.getIdWydzial(),readWydzial.getNazwa(),readWydzial.getAdres(),readWydzial.getIdDziekan()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseWydzial.setItems(dataWydzial);
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
