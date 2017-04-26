package controllers.add;

import java.io.IOException;

import classes.deanery.Katedra;
import classes.deanery.Wykladowca;
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

public class AddWykladowcaController {

	private MainController mainController;
	private Katedra readKatedra;
	private ObservableList<Katedra> data = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Katedra> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField imieField, nazwiskoField, tytulField;
	
	@FXML
	public void chooseKatedra() {
		readKatedra = (Katedra) choose.getValue();
	}
	
	@FXML
	public void add() {
		if ( (imieField.getText().length() != 0) && (nazwiskoField.getText().length() != 0) && (tytulField.getText().length() != 0) && readKatedra != null ) {	
			Wykladowca wyk = new Wykladowca(imieField.getText(), nazwiskoField.getText(), tytulField.getText(), readKatedra.getidKatedra());
			try {
				mainController.oos.writeObject("addwykladowca");
				mainController.oos.writeObject(wyk);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Dodano wyk³adowce");
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
					data.add(new Katedra(readKatedra.getidKatedra(), readKatedra.getNazwa(), readKatedra.getAdres(), readKatedra.getNazwaWydzial(), readKatedra.getidWydzial()));
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
