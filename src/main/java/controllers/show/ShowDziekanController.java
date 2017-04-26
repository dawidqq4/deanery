package controllers.show;

import java.io.IOException;

import classes.JDBC.DeanFx;
import classes.deanery.Dziekan;
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

public class ShowDziekanController {

	private MainController mainController;
	private boolean isDisplayed = false;
	
	@FXML
	private TableView<DeanFx> tableDziekan;
	
	private ObservableList<DeanFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableColumn<String, ?> nazwiskoColumn, imieColumn, tytulColumn;
		
	public void showData() {
		if (!isDisplayed) {
			isDisplayed = true;
			try {
				mainController.oos.writeObject(new String("showdz"));
				tableDziekan.setEditable(true);
				nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
				imieColumn.setCellValueFactory(new PropertyValueFactory<>("Imie"));
				tytulColumn.setCellValueFactory(new PropertyValueFactory<>("Tytul"));
				tableDziekan.setItems(data);
				Dziekan readObjectDziekan = new Dziekan();
				while (true) {
					try {
						readObjectDziekan = (Dziekan) mainController.ois.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					if (readObjectDziekan == null)
						break;
					else
						data.add( new DeanFx(readObjectDziekan.getNazwisko(), readObjectDziekan.getImie(), readObjectDziekan.getTytul()) );
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
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
