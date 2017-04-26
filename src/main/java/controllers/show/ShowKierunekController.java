package controllers.show;

import java.io.IOException;

import classes.JDBC.DirectionFx;
import classes.deanery.Kierunek;
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

public class ShowKierunekController {

	private MainController mainController;
	
	private ObservableList<DirectionFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<DirectionFx> tableKierunek;
	
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
			mainController.oos.writeObject(new String("showkier"));
			tableKierunek.setEditable(true);
			nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
			wydzialColumn.setCellValueFactory(new PropertyValueFactory<>("Wydzial"));
			tableKierunek.setItems(data);
			Kierunek readObjectKierunek = new Kierunek();
			while (true) {
				try {
					readObjectKierunek = (Kierunek) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectKierunek == null)
					break;
				else
					data.add( new DirectionFx(readObjectKierunek.getNazwa(), readObjectKierunek.getNazwaWydzial()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
