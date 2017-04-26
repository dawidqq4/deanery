package controllers.show;

import java.io.IOException;

import classes.JDBC.LessonFx;
import classes.deanery.Zajecia;
import controllers.navigation.MainController;
import controllers.navigation.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ShowZajeciaController {

	private MainController mainController;
	private ObservableList<LessonFx> data = FXCollections.observableArrayList();
	
	
	@FXML
	private TableView<LessonFx> tableZajecia;
	
	@FXML
	private TableColumn<String, ?> godzinaColumn, wykladowcaColumn, przedmiotColumn, salaColumn;
	
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
	
	public void showData() {
		try {
			mainController.oos.writeObject(new String("showzajecia"));
			tableZajecia.setEditable(true);
			godzinaColumn.setCellValueFactory(new PropertyValueFactory<>("Godzina"));
			wykladowcaColumn.setCellValueFactory(new PropertyValueFactory<>("Wykladowca"));
			przedmiotColumn.setCellValueFactory(new PropertyValueFactory<>("Przedmiot"));
			salaColumn.setCellValueFactory(new PropertyValueFactory<>("Sala"));
			tableZajecia.setItems(data);
			Zajecia readObjectZajecia = new Zajecia();
			while (true) {
				try {
					readObjectZajecia = (Zajecia) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectZajecia == null)
					break;
				else
					data.add( new LessonFx(readObjectZajecia.getGodzina(), readObjectZajecia.getNazwiskoWykladowca(), readObjectZajecia.getNazwaPrzedmiot(), readObjectZajecia.getNazwaSala()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
