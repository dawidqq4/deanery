package controllers.add;

import java.io.IOException;

import classes.deanery.Grupa;
import classes.deanery.Student;
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

public class AddStudentController {

	private MainController mainController;
	private Grupa readGrupa;
	private ObservableList<Grupa> data = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Grupa> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField imieField, nazwiskoField, adresField;
	
	@FXML
	public void chooseGrupa() {
		readGrupa = (Grupa) choose.getValue();
	}
	
	@FXML
	public void add() {
		if ( (imieField.getText().length() != 0) && (nazwiskoField.getText().length() != 0) && (adresField.getText().length() != 0) && readGrupa != null ) {	
			Student st = new Student(imieField.getText(), nazwiskoField.getText(), adresField.getText(), readGrupa.getidGrupa());
			try {
				mainController.oos.writeObject("addstudent");
				mainController.oos.writeObject(st);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Dodano studenta");
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
					data.add(new Grupa(readGrupa.getidGrupa(), readGrupa.getnazwaGrupa(), readGrupa.getRocznik(), readGrupa.getidRok()));
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
