package controllers.show;

import java.io.IOException;

import classes.JDBC.FacultyFx;
import classes.deanery.Wydzial;
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

public class ShowWydzialController {

	private MainController mainController;
	
	private ObservableList<FacultyFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<FacultyFx> tableWydzial;
	
	@FXML
	private TableColumn<String, ?> nazwaColumn, adresColumn, nazwiskoColumn;
	
	public void showData() {
		try {
			mainController.oos.writeObject(new String("showwy"));
			tableWydzial.setEditable(true);
			nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
			adresColumn.setCellValueFactory(new PropertyValueFactory<>("Adres"));
			nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
			tableWydzial.setItems(data);
			Wydzial readObjectWydzial = new Wydzial();
			while (true) {
				try {
					readObjectWydzial = (Wydzial) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectWydzial == null)
					break;
				else
					data.add( new FacultyFx(readObjectWydzial.getNazwa(), readObjectWydzial.getAdres(), readObjectWydzial.getNazwiskoDziekan()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
}
