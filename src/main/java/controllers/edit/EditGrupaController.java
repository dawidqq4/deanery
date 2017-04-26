package controllers.edit;

import java.io.IOException;

import classes.deanery.Grupa;
import classes.deanery.Rok;
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

public class EditGrupaController {

	private MainController mainController;
	private Rok readRok;
	private Grupa readGrupa;
	private ObservableList<Rok> dataRok = FXCollections.observableArrayList();
	private ObservableList<Grupa> dataGrupa = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Rok> chooseRocznik;
	
	@FXML
	private ComboBox<Grupa> chooseGrupa;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField;
	
	@FXML
	public void chooseDataGrupa() {
		readGrupa = (Grupa) chooseGrupa.getValue();
		nameField.setText(readGrupa.getnazwaGrupa());
	}
	
	@FXML
	public void chooseDataRocznik() {
		readRok = (Rok) chooseRocznik.getValue();
	}
	
	@FXML
	public void edit() {
		if ( (nameField.getText().length() != 0) && readRok != null && readGrupa != null ) {
			Grupa gr = new Grupa(readGrupa.getidGrupa(), nameField.getText(), readRok.getRocznik(), readRok.getidRok());
			try {
				mainController.oos.writeObject("editgrupa");
				mainController.oos.writeObject(gr);
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
	
	public void wczytajDaneRok() {
		try {
			mainController.oos.writeObject("showrok");
			while(true) {
				try {
					readRok = (Rok) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readRok == null)
					break;
				else
					dataRok.add(new Rok(readRok.getidRok(), readRok.getRocznik(), readRok.getNazwaKierunek(), readRok.getidKierunek()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseRocznik.setItems(dataRok);
	}
	
	public void wczytajDaneGrupa() {
		try {
			mainController.oos.writeObject("showgrupa");
			while(true) {
				try {
					readGrupa = (Grupa) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readGrupa == null)
					break;
				else
					dataGrupa.add(new Grupa(readGrupa.getidGrupa(), readGrupa.getnazwaGrupa(), readGrupa.getRocznik(), readGrupa.getidRok()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseGrupa.setItems(dataGrupa);
	}
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
