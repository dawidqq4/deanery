package controllers.edit;

import java.io.IOException;

import classes.deanery.Dziekan;
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

public class EditDziekanController {

	private MainController mainController;
	
	private ObservableList<Dziekan> data = FXCollections.observableArrayList();
	private Dziekan readDziekan = new Dziekan();
	
	@FXML
	private ComboBox<Dziekan> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nameField, surnameField, titleField;
	
	@FXML
	public void chooseData() {
		readDziekan = (Dziekan) choose.getValue();
		surnameField.setText(readDziekan.getNazwisko());
		nameField.setText(readDziekan.getImie());
		titleField.setText(readDziekan.getTytul());
	}
	
	@FXML
	public void edit() {
		if ( (surnameField.getText().length() != 0) && (nameField.getText().length() != 0) && (titleField.getText().length() != 0) && readDziekan != null ) {	
			Dziekan dz = new Dziekan(readDziekan.getidDziekan(), surnameField.getText(), nameField.getText(), titleField.getText());
			try {
				mainController.oos.writeObject("editdz");
				mainController.oos.writeObject(dz);
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
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public void wczytajDane() {
		try {
			mainController.oos.writeObject("showdz");
			while(true) {
				try {
					readDziekan = (Dziekan) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readDziekan == null)
					break;
				else
					data.add(new Dziekan(readDziekan.getidDziekan(),readDziekan.getNazwisko(),readDziekan.getImie(),readDziekan.getTytul()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		choose.setItems(data);
	}
}
