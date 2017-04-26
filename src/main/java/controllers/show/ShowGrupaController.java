package controllers.show;

import java.io.IOException;

import classes.JDBC.GroupFx;
import classes.deanery.Grupa;
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

public class ShowGrupaController {

	private MainController mainController;
	private ObservableList<GroupFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<GroupFx> tableGrupa;
	
	@FXML
	private TableColumn<String, ?> grupaColumn;
	
	@FXML
	private TableColumn<Integer, ?> rokColumn;
	
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
			mainController.oos.writeObject(new String("showgrupa"));
			tableGrupa.setEditable(true);
			grupaColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
			rokColumn.setCellValueFactory(new PropertyValueFactory<>("Rok"));
			tableGrupa.setItems(data);
			Grupa readObjectGrupa = new Grupa();
			while (true) {
				try {
					readObjectGrupa = (Grupa) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectGrupa == null)
					break;
				else
					data.add( new GroupFx(readObjectGrupa.getnazwaGrupa(), readObjectGrupa.getRocznik()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
