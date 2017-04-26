package controllers.delete;

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

public class DeleteDziekanController {

	private ObservableList<Dziekan> data = FXCollections.observableArrayList();
	private Dziekan readDziekan = new Dziekan();
	private MainController mainController;
	
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
		surnameField.setEditable(false);
		nameField.setText(readDziekan.getImie());
		nameField.setEditable(false);
		titleField.setText(readDziekan.getTytul());
		titleField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (surnameField.getText().length() != 0) && (nameField.getText().length() != 0) && (titleField.getText().length() != 0) && readDziekan != null ) {	
			Dziekan dz = new Dziekan(readDziekan.getidDziekan(), surnameField.getText(), nameField.getText(), titleField.getText());
			try {
				mainController.oos.writeObject("deletedz");
				mainController.oos.writeObject(dz);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Usuni�to dane");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			backToMenu();
		}
		else
			statusLabel.setText("Podano b��dne dane");
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
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
