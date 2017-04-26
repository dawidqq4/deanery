package controllers.delete;

import java.io.IOException;

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

public class DeleteWydzialController {

	private ObservableList<Wydzial> data = FXCollections.observableArrayList();
	private Wydzial readWydzial = new Wydzial();
	private MainController mainController;
	
	@FXML
	private ComboBox<Wydzial> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField, addressField, deanField;
	
	@FXML
	public void chooseData() {
		readWydzial = (Wydzial) choose.getValue();
		nameField.setText(readWydzial.getNazwa());
		nameField.setEditable(false);
		addressField.setText(readWydzial.getAdres());
		addressField.setEditable(false);
		deanField.setText(readWydzial.getNazwiskoDziekan());
		deanField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (nameField.getText().length() != 0) && (addressField.getText().length() != 0) && (deanField.getText().length() != 0) && readWydzial != null ) {	
			Wydzial wy = new Wydzial(readWydzial.getIdWydzial(), readWydzial.getNazwa(), readWydzial.getAdres(), readWydzial.getNazwiskoDziekan(), readWydzial.getIdDziekan());
			try {
				mainController.oos.writeObject("deletewy");
				mainController.oos.writeObject(wy);
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
					data.add(new Wydzial(readWydzial.getIdWydzial(),readWydzial.getNazwa(),readWydzial.getAdres(),readWydzial.getNazwiskoDziekan(), readWydzial.getIdWydzial()));
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
