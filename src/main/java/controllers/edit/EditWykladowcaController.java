package controllers.edit;

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

public class EditWykladowcaController {

	private MainController mainController;
	private Katedra readKatedra;
	private Wykladowca readWykladowca;
	private ObservableList<Katedra> dataKatedra = FXCollections.observableArrayList();
	private ObservableList<Wykladowca> dataWykladowca = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Katedra> chooseKatedra;
	
	@FXML
	private ComboBox<Wykladowca> chooseWykladowca;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nazwiskoField, imieField, tytulField;
	
	@FXML
	public void chooseDataWykladowca() {
		readWykladowca = (Wykladowca) chooseWykladowca.getValue();
		nazwiskoField.setText(readWykladowca.getNazwisko());
		imieField.setText(readWykladowca.getImie());
		tytulField.setText(readWykladowca.getTytul());
	}
	
	@FXML
	public void chooseDataKatedra() {
		readKatedra = (Katedra) chooseKatedra.getValue();
	}
	
	@FXML
	public void edit() {
		if ( (nazwiskoField.getText().length() != 0) && (imieField.getText().length() != 0) && (tytulField.getText().length() != 0) && readKatedra != null && readWykladowca != null ) {
			Wykladowca wyk = new Wykladowca(readWykladowca.getIdWykladowca(), nazwiskoField.getText(), imieField.getText(), tytulField.getText(), readKatedra.getNazwa(), readKatedra.getidKatedra());
			try {
				mainController.oos.writeObject("editwykladowca");
				mainController.oos.writeObject(wyk);
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
		
	public void wczytajDaneKatedra() {
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
					dataKatedra.add(new Katedra(readKatedra.getidKatedra(), readKatedra.getNazwa(), readKatedra.getAdres(), readKatedra.getNazwaWydzial(), readKatedra.getidWydzial()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseKatedra.setItems(dataKatedra);
	}
	
	public void wczytajDaneWykladowca() {
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
					dataWykladowca.add(new Wykladowca(readWykladowca.getIdWykladowca(), readWykladowca.getNazwisko(), readWykladowca.getImie(), readWykladowca.getTytul(), readWykladowca.getNazwaKatedra(), readWykladowca.getIdKatedra()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseWykladowca.setItems(dataWykladowca);
	}
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
