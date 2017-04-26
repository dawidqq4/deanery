package controllers.add;

import java.io.IOException;

import classes.deanery.Przedmiot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import controllers.navigation.MainController;
import controllers.navigation.MenuController;

public class AddPrzedmiotController {

	private MainController mainController;
	private int wagaToInt = 0;
	
	@FXML
	private TextField nameField, wagaField;
	
	@FXML
	private Label statusField;
	
	public boolean isStringInt(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	@FXML
	public void add() {
		if (isStringInt(wagaField.getText()) == true)
			wagaToInt = Integer.parseInt(wagaField.getText());
		else {
			statusField.setText("B³êdna waga");
			wagaToInt = -54321;
		}
		if ( (nameField.getText().length() != 0) && (wagaToInt > 0) && (wagaToInt < 10) ) {	
			Przedmiot prz = new Przedmiot( nameField.getText(), Integer.parseInt(wagaField.getText()));
			try {
				mainController.oos.writeObject("addprzedmiot");
				mainController.oos.writeObject(prz);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusField.setText("Dodano przedmiot");
				}
			} catch (IOException e) {
				statusField.setText("B³¹d dodawania");
			}
			backToMenu();
		}
		else
			statusField.setText("B³êdne dane");
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
}
