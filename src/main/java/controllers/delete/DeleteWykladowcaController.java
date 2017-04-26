package controllers.delete;

import java.io.IOException;

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

public class DeleteWykladowcaController {

	private ObservableList<Wykladowca> data = FXCollections.observableArrayList();
	private Wykladowca readWykladowca = new Wykladowca();
	private MainController mainController;
	
	@FXML
	private ComboBox<Wykladowca> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nazwiskoField, imieField, tytulField, katedraField;
	
	@FXML
	public void chooseData() {
		readWykladowca = (Wykladowca) choose.getValue();
		nazwiskoField.setText(readWykladowca.getNazwisko());
		nazwiskoField.setEditable(false);
		imieField.setText(readWykladowca.getImie());
		imieField.setEditable(false);
		tytulField.setText(readWykladowca.getTytul());
		tytulField.setEditable(false);
		katedraField.setText(readWykladowca.getNazwaKatedra());
		katedraField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (nazwiskoField.getText().length() != 0) && (imieField.getText().length() != 0) && (tytulField.getText().length() != 0) && (katedraField.getText().length() != 0) && readWykladowca != null ) {	
			Wykladowca wyk = new Wykladowca(readWykladowca.getIdWykladowca(), readWykladowca.getNazwisko(), readWykladowca.getImie(), readWykladowca.getTytul(), readWykladowca.getNazwaKatedra(), readWykladowca.getIdKatedra());
			try {
				mainController.oos.writeObject("deletewykladowca");
				mainController.oos.writeObject(wyk);
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
			mainController.oos.writeObject("showwykladowca");
			while(true) {
				try {
					readWykladowca = (Wykladowca) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readWykladowca == null)
					break;
				else
					data.add(new Wykladowca(readWykladowca.getIdWykladowca(), readWykladowca.getNazwisko(), readWykladowca.getImie(), readWykladowca.getTytul(), readWykladowca.getNazwaKatedra(), readWykladowca.getIdKatedra()));
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
