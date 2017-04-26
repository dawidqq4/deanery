package controllers.show;

import java.io.IOException;

import classes.JDBC.SubjectFx;
import classes.deanery.Przedmiot;
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

public class ShowPrzedmiotController {

	private MainController mainController;
	
	@FXML
	private TableView<SubjectFx> tablePrzedmiot;
	
	private ObservableList<SubjectFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableColumn<Integer, ?> wagaColumn;
	
	@FXML
	private TableColumn<String, ?> nazwaColumn;
		
	public void showData() {
		try {
			mainController.oos.writeObject(new String("showprzedmiot"));
			tablePrzedmiot.setEditable(true);
			nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
			wagaColumn.setCellValueFactory(new PropertyValueFactory<>("Waga"));
			tablePrzedmiot.setItems(data);
			Przedmiot readObjectPrzedmiot = new Przedmiot();
			while (true) {
				try {
					readObjectPrzedmiot = (Przedmiot) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectPrzedmiot == null)
					break;
				else
					data.add( new SubjectFx(readObjectPrzedmiot.getNazwa(), readObjectPrzedmiot.getWaga()) );
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
