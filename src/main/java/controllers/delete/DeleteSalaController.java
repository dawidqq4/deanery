package controllers.delete;

import java.io.IOException;

import classes.deanery.Sala;
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

public class DeleteSalaController {

	private ObservableList<Sala> data = FXCollections.observableArrayList();
	private Sala readSala = new Sala();
	private MainController mainController;
	
	@FXML
	private ComboBox<Sala> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField, facultyField;
	
	@FXML
	public void chooseData() {
		readSala = (Sala) choose.getValue();
		nameField.setText(readSala.getNazwa());
		nameField.setEditable(false);
		facultyField.setText(readSala.getNazwaWydzial());
		facultyField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (nameField.getText().length() != 0) && (facultyField.getText().length() != 0) && readSala != null ) {	
			Sala sala = new Sala(readSala.getIdSala(), readSala.getNazwa(), readSala.getNazwaWydzial(), readSala.getidWydzial());
			try {
				mainController.oos.writeObject("deletesala");
				mainController.oos.writeObject(sala);
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
			mainController.oos.writeObject("showsala");
			while(true) {
				try {
					readSala = (Sala) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readSala == null)
					break;
				else
					data.add(new Sala(readSala.getIdSala(), readSala.getNazwa(), readSala.getNazwaWydzial(), readSala.getidWydzial()));
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
