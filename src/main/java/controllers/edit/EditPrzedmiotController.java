package controllers.edit;

import java.io.IOException;

import classes.deanery.Przedmiot;
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

public class EditPrzedmiotController {

	private MainController mainController;
	
	private ObservableList<Przedmiot> data = FXCollections.observableArrayList();
	private Przedmiot readPrzedmiot = new Przedmiot();
	private int wagaToInt = 0;
	
	@FXML
	private ComboBox<Przedmiot> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField, wagaField;
	
	@FXML
	public void chooseData() {
		readPrzedmiot = (Przedmiot) choose.getValue();
		nameField.setText(readPrzedmiot.getNazwa());
		wagaField.setText(Integer.toString(readPrzedmiot.getWaga()));
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
		if (isStringInt(wagaField.getText()) == true)
			wagaToInt = Integer.parseInt(wagaField.getText());
		else {
			statusLabel.setText("B³êdna waga");
			wagaToInt = -54321;
		}
		if ( (nameField.getText().length() != 0) && (wagaToInt > 0)  && (wagaToInt < 10) && readPrzedmiot != null ) {	
			Przedmiot prz = new Przedmiot(readPrzedmiot.getIdPrzedmiot(), nameField.getText(), wagaToInt);
			try {
				mainController.oos.writeObject("editprzedmiot");
				mainController.oos.writeObject(prz);
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
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public void wczytajDane() {
		try {
			mainController.oos.writeObject("showprzedmiot");
			while(true) {
				try {
					readPrzedmiot = (Przedmiot) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readPrzedmiot == null)
					break;
				else
					data.add(new Przedmiot(readPrzedmiot.getIdPrzedmiot(), readPrzedmiot.getNazwa(), readPrzedmiot.getWaga()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		choose.setItems(data);
	}
}
