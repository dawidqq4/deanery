package controllers.add;

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

public class AddGrupaController {

	private MainController mainController;
	private Rok readRok;
	private ObservableList<Rok> data = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Rok> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField grupaField;
	
	@FXML
	public void chooseRocznik() {
		readRok = (Rok) choose.getValue();
	}
	
	@FXML
	public void add() {
		if ( (grupaField.getText().length() != 0) && readRok != null ) {	
			Grupa gr = new Grupa(grupaField.getText(), readRok.getidRok());
			try {
				mainController.oos.writeObject("addgrupa");
				mainController.oos.writeObject(gr);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Dodano kierunek");
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
					data.add(new Rok(readRok.getidRok(), readRok.getRocznik(), readRok.getNazwaKierunek(), readRok.getidKierunek()));
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
