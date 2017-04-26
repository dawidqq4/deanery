package controllers.delete;

import java.io.IOException;

import classes.deanery.Grupa;
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

public class DeleteGrupaController {

	private ObservableList<Grupa> data = FXCollections.observableArrayList();
	private Grupa readGrupa = new Grupa();
	private MainController mainController;
	
	@FXML
	private ComboBox<Grupa> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField grupaField, rocznikField;
	
	@FXML
	public void chooseData() {
		readGrupa = (Grupa) choose.getValue();
		grupaField.setText(readGrupa.getnazwaGrupa());
		grupaField.setEditable(false);
		rocznikField.setText(Integer.toString(readGrupa.getRocznik()));
		rocznikField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (grupaField.getText().length() != 0) && (rocznikField.getText().length() != 0) && readGrupa != null ) {	
			Grupa gr = new Grupa(readGrupa.getidGrupa(), readGrupa.getnazwaGrupa(), readGrupa.getRocznik(), readGrupa.getidRok());
			try {
				mainController.oos.writeObject("deletegrupa");
				mainController.oos.writeObject(gr);
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
