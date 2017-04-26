package controllers.add;

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

public class AddWydzialController {

	private MainController mainController;
	private Dziekan readDziekan;
	private ObservableList<Dziekan> data = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Dziekan> choose;
	
	@FXML
	private Label statusField;
	
	@FXML
	private TextField nameField, addressField;
	
	@FXML
	public void chooseDziekan() {
		readDziekan = (Dziekan) choose.getValue();
	}
	
	@FXML
	public void add() {
		if ( (nameField.getText().length() != 0) && (addressField.getText().length() != 0) && readDziekan != null ) {	
			Wydzial wy = new Wydzial(nameField.getText(), addressField.getText(), readDziekan.getidDziekan());
			try {
				mainController.oos.writeObject("addwy");
				mainController.oos.writeObject(wy);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusField.setText("Dodano wydzia³");
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
	
	public void wczytajDane() {
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
					data.add(new Dziekan(readDziekan.getidDziekan(),readDziekan.getNazwisko(),readDziekan.getImie(),readDziekan.getTytul()));
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
