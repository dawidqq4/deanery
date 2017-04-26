package controllers.show;

import java.io.IOException;

import classes.JDBC.LecturerFx;
import classes.deanery.Wykladowca;
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

public class ShowWykladowcaController {

	private MainController mainController;
	private ObservableList<LecturerFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<LecturerFx> tableWykladowca;
	
	@FXML
	private TableColumn<String, ?> nazwiskoColumn, imieColumn, tytulColumn, katedraColumn;
	
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
			mainController.oos.writeObject(new String("showwykladowca"));
			tableWykladowca.setEditable(true);
			nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
			imieColumn.setCellValueFactory(new PropertyValueFactory<>("Imie"));
			tytulColumn.setCellValueFactory(new PropertyValueFactory<>("Tytul"));
			katedraColumn.setCellValueFactory(new PropertyValueFactory<>("Katedra"));
			tableWykladowca.setItems(data);
			Wykladowca readObjectWykladowca = new Wykladowca();
			while (true) {
				try {
					readObjectWykladowca = (Wykladowca) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectWykladowca == null)
					break;
				else
					data.add( new LecturerFx(readObjectWykladowca.getNazwisko(), readObjectWykladowca.getImie(), readObjectWykladowca.getTytul(), readObjectWykladowca.getNazwaKatedra()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
