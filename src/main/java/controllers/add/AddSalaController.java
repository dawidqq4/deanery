package controllers.add;

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

public class AddSalaController {

	private MainController mainController;
	private Wydzial readWydzial;
	private ObservableList<Wydzial> data = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Wydzial> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField;
	
	@FXML
	public void chooseWydzial() {
		readWydzial = (Wydzial) choose.getValue();
	}
	
	@FXML
	public void add() {
		if ( (nameField.getText().length() != 0) && readWydzial != null ) {	
			Sala sala = new Sala(nameField.getText(), readWydzial.getIdWydzial());
			try {
				mainController.oos.writeObject("addsala");
				mainController.oos.writeObject(sala);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Dodano sale");
				}
			} catch (IOException e) {
				statusLabel.setText("B³¹d dodawania");
			}
			backToMenu();
		}
		else
			statusLabel.setText("B³êdne dane"); 
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
					data.add(new Wydzial(readWydzial.getIdWydzial(), readWydzial.getNazwa(), readWydzial.getAdres(), readWydzial.getIdDziekan()));
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
