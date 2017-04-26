package controllers.delete;

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

public class DeletePrzedmiotController {

	private ObservableList<Przedmiot> data = FXCollections.observableArrayList();
	private Przedmiot readPrzedmiot = new Przedmiot();
	private MainController mainController;
	
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
		nameField.setEditable(false);
		wagaField.setText(Integer.toString(readPrzedmiot.getWaga()));
		wagaField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (nameField.getText().length() != 0) && (wagaField.getText().length() != 0) && readPrzedmiot != null ) {	
			Przedmiot prz = new Przedmiot(readPrzedmiot.getIdPrzedmiot(), nameField.getText(), Integer.parseInt(wagaField.getText()));
			try {
				mainController.oos.writeObject("deleteprzedmiot");
				mainController.oos.writeObject(prz);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Usuniêto dane");
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
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
