package controllers.show;

import java.io.IOException;

import classes.JDBC.RoomFx;
import classes.deanery.Sala;
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

public class ShowSalaController {

	private MainController mainController;
	
	private ObservableList<RoomFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<RoomFx> tableSala;
	
	@FXML
	private TableColumn<String, ?> nazwaColumn, wydzialColumn;
	
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
			mainController.oos.writeObject(new String("showsala"));
			tableSala.setEditable(true);
			nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
			wydzialColumn.setCellValueFactory(new PropertyValueFactory<>("Wydzial"));
			tableSala.setItems(data);
			Sala readObjectSala = new Sala();
			while (true) {
				try {
					readObjectSala = (Sala) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectSala == null)
					break;
				else
					data.add( new RoomFx(readObjectSala.getNazwa(), readObjectSala.getNazwaWydzial()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
