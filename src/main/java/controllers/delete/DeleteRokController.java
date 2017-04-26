package controllers.delete;

import java.io.IOException;

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

public class DeleteRokController {

	private ObservableList<Rok> data = FXCollections.observableArrayList();
	private Rok readRok = new Rok();
	private MainController mainController;
	
	@FXML
	private ComboBox<Rok> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField rokField, kierunekField;
	
	@FXML
	public void chooseData() {
		readRok = (Rok) choose.getValue();
		rokField.setText(Integer.toString(readRok.getRocznik()));
		rokField.setEditable(false);
		kierunekField.setText(readRok.getNazwaKierunek());
		kierunekField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (rokField.getText().length() != 0) && (kierunekField.getText().length() != 0) && readRok != null ) {	
			Rok r = new Rok(readRok.getidRok(), readRok.getRocznik(), readRok.getidKierunek());
			try {
				mainController.oos.writeObject("deleterok");
				mainController.oos.writeObject(r);
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
