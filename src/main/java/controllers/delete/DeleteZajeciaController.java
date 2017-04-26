package controllers.delete;

import java.io.IOException;

import classes.deanery.Zajecia;
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

public class DeleteZajeciaController {

	private ObservableList<Zajecia> data = FXCollections.observableArrayList();
	private Zajecia readZajecia = new Zajecia();
	private MainController mainController;
	
	
	@FXML
	private ComboBox<Zajecia> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField godzinaField, wykladowcaField, przedmiotField, salaField;
	
	@FXML
	public void chooseData() {
		readZajecia = (Zajecia) choose.getValue();
		godzinaField.setText(readZajecia.getGodzina());
		godzinaField.setEditable(false);
		wykladowcaField.setText(readZajecia.getNazwiskoWykladowca());
		wykladowcaField.setEditable(false);
		przedmiotField.setText(readZajecia.getNazwaPrzedmiot());
		przedmiotField.setEditable(false);
		salaField.setText(readZajecia.getNazwaSala());
		salaField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (godzinaField.getText().length() != 0) && (wykladowcaField.getText().length() != 0) && (przedmiotField.getText().length() != 0) && (salaField.getText().length() != 0) && readZajecia != null ) {	
			Zajecia zajecia = new Zajecia(readZajecia.getIdZajecia(), readZajecia.getGodzina(), readZajecia.getIdWykladowca(), readZajecia.getIdPrzedmiot(), readZajecia.getIdSala());
			try {
				mainController.oos.writeObject("deletezajecia");
				mainController.oos.writeObject(zajecia);
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
			mainController.oos.writeObject("showzajecia");
			while(true) {
				try {
					readZajecia = (Zajecia) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readZajecia == null)
					break;
				else
					data.add(new Zajecia(readZajecia.getIdZajecia(), readZajecia.getGodzina(), readZajecia.getIdWykladowca(), readZajecia.getNazwiskoWykladowca(), readZajecia.getIdPrzedmiot(), readZajecia.getNazwaPrzedmiot(), readZajecia.getIdSala(), readZajecia.getNazwaSala()));
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
