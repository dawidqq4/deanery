package controllers.show;

import java.io.IOException;

import classes.JDBC.OcenaFx;
import classes.deanery.Ocena;
import controllers.navigation.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ShowOcenaStudentController {

	private MainController mainController;
	private ObservableList<OcenaFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<OcenaFx> tableOcena;
	
	@FXML
	private TableColumn<Integer, ?> ocenaColumn;
	
	@FXML
	private TableColumn<String, ?> studentColumn;
	
	@FXML
	private TableColumn<String, ?> przedmiotColumn;
	
	@FXML
	private TableColumn<String, ?> wykladowcaColumn;
	
	@FXML
	public void backToStudentView() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowStudent.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowStudentController showStudentController = loader.getController();
		showStudentController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showStudentController.showData();
	}
	
	public void showData() {
		try {
			tableOcena.setEditable(true);
			ocenaColumn.setCellValueFactory(new PropertyValueFactory<>("Ocena"));
			studentColumn.setCellValueFactory(new PropertyValueFactory<>("Student"));
			przedmiotColumn.setCellValueFactory(new PropertyValueFactory<>("Przedmiot"));
			wykladowcaColumn.setCellValueFactory(new PropertyValueFactory<>("Wykladowca"));
			tableOcena.setItems(data);
			Ocena readObjectOcena = new Ocena();
			while (true) {
				try {
					readObjectOcena = (Ocena) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectOcena == null)
					break;
				else
					data.add( new OcenaFx(readObjectOcena.getOcena(), readObjectOcena.getNazwiskoStudent(), readObjectOcena.getNazwaPrzedmiot(), readObjectOcena.getNazwiskoWykladowca()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
