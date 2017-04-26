package controllers.delete;

import java.io.IOException;

import classes.deanery.Kierunek;
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

public class DeleteKierunekController {

	private ObservableList<Kierunek> data = FXCollections.observableArrayList();
	private Kierunek readKierunek = new Kierunek();
	private MainController mainController;
	
	@FXML
	private ComboBox<Kierunek> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField, facultyField;
	
	@FXML
	public void chooseData() {
		readKierunek = (Kierunek) choose.getValue();
		nameField.setText(readKierunek.getNazwa());
		nameField.setEditable(false);
		facultyField.setText(readKierunek.getNazwaWydzial());
		facultyField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (nameField.getText().length() != 0) && (facultyField.getText().length() != 0) && readKierunek != null ) {	
			Kierunek kier = new Kierunek(readKierunek.getidKierunek(), readKierunek.getNazwa(), readKierunek.getidWydzial());
			try {
				mainController.oos.writeObject("deletekier");
				mainController.oos.writeObject(kier);
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
			mainController.oos.writeObject("showkier");
			while(true) {
				try {
					readKierunek = (Kierunek) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readKierunek == null)
					break;
				else
					data.add(new Kierunek(readKierunek.getidKierunek(), readKierunek.getNazwa(), readKierunek.getNazwaWydzial(), readKierunek.getidWydzial()));
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
