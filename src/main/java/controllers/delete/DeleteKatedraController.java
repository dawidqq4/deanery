package controllers.delete;

import java.io.IOException;

import classes.deanery.Katedra;
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

public class DeleteKatedraController {

	private ObservableList<Katedra> data = FXCollections.observableArrayList();
	private Katedra readKatedra = new Katedra();
	private MainController mainController;
	
	@FXML
	private ComboBox<Katedra> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField, adresField, facultyField;
	
	@FXML
	public void chooseData() {
		readKatedra = (Katedra) choose.getValue();
		nameField.setText(readKatedra.getNazwa());
		nameField.setEditable(false);
		adresField.setText(readKatedra.getAdres());
		adresField.setEditable(false);
		facultyField.setText(readKatedra.getNazwaWydzial());
		facultyField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (nameField.getText().length() != 0) && (adresField.getText().length() != 0) && (facultyField.getText().length() != 0) && readKatedra != null ) {	
			Katedra kat = new Katedra(readKatedra.getidKatedra(), readKatedra.getNazwa(), readKatedra.getAdres(), readKatedra.getNazwaWydzial(), readKatedra.getidWydzial());
			try {
				mainController.oos.writeObject("deletekatedra");
				mainController.oos.writeObject(kat);
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
