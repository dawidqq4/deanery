package controllers.add;

import java.io.IOException;

import classes.deanery.Dziekan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import controllers.navigation.MainController;
import controllers.navigation.MenuController;

public class AddDziekanController {

	private MainController mainController;
	
	@FXML
	private TextField imieField, nazwiskoField, tytulField;
	
	@FXML
	private Label statusField;
	
	@FXML
	public void add() {
		if ( (nazwiskoField.getText().length() != 0) && (imieField.getText().length() != 0) && (tytulField.getText().length() != 0) ) {	
			Dziekan dz = new Dziekan(nazwiskoField.getText(), imieField.getText(), tytulField.getText());
			try {
				mainController.oos.writeObject("adddz");
				mainController.oos.writeObject(dz);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusField.setText("Dodano dziekana");
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
