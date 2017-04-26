package controllers.delete;

import java.io.IOException;

import classes.deanery.Ocena;
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

public class DeleteOcenaController {

	private ObservableList<Ocena> data = FXCollections.observableArrayList();
	private Ocena readOcena = new Ocena();
	private MainController mainController;
	
	@FXML
	private ComboBox<Ocena> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField ocenaField, studentField, przedmiotField, wykladowcaField;
	
	@FXML
	public void chooseData() {
		readOcena = (Ocena) choose.getValue();
		ocenaField.setText(Integer.toString(readOcena.getOcena()));
		ocenaField.setEditable(false);
		studentField.setText(readOcena.getNazwiskoStudent());
		studentField.setEditable(false);
		przedmiotField.setText(readOcena.getNazwaPrzedmiot());
		przedmiotField.setEditable(false);
		wykladowcaField.setText(readOcena.getNazwiskoWykladowca());
		wykladowcaField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (ocenaField.getText().length() != 0) && (studentField.getText().length() != 0) && (przedmiotField.getText().length() != 0) && (wykladowcaField.getText().length() != 0) && readOcena != null ) {	
			Ocena ocena = new Ocena(readOcena.getIdOCena(), readOcena.getOcena(), readOcena.getIdStudent(), readOcena.getNazwiskoStudent(), readOcena.getIdPrzedmiot(), readOcena.getNazwaPrzedmiot(), readOcena.getIdWykladowca(), readOcena.getNazwiskoWykladowca());
			try {
				mainController.oos.writeObject("deleteocena");
				mainController.oos.writeObject(ocena);
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
			mainController.oos.writeObject("showocena");
			while(true) {
				try {
					readOcena = (Ocena) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readOcena == null)
					break;
				else
					data.add(new Ocena(readOcena.getIdOCena(), readOcena.getOcena(), readOcena.getIdStudent(), readOcena.getNazwiskoStudent(), readOcena.getIdPrzedmiot(), readOcena.getNazwaPrzedmiot(), readOcena.getIdWykladowca(), readOcena.getNazwiskoWykladowca()));
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
