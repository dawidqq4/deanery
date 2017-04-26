package controllers.show;

import java.io.IOException;

import classes.JDBC.YearFx;
import classes.deanery.Rok;
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

public class ShowRokController {

	private MainController mainController;
	private ObservableList<YearFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<YearFx> tableRocznik;
	
	@FXML
	private TableColumn<Integer, ?> rokColumn;
	
	@FXML
	private TableColumn<String, ?> kierunekColumn;
	
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
			mainController.oos.writeObject(new String("showrok"));
			tableRocznik.setEditable(true);
			rokColumn.setCellValueFactory(new PropertyValueFactory<>("Rok"));
			kierunekColumn.setCellValueFactory(new PropertyValueFactory<>("Kierunek"));
			tableRocznik.setItems(data);
			Rok readObjectRok = new Rok();
			while (true) {
				try {
					readObjectRok = (Rok) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectRok == null)
					break;
				else
					data.add( new YearFx(readObjectRok.getRocznik(), readObjectRok.getNazwaKierunek()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
