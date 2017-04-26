package controllers.show;

import java.io.IOException;

import classes.JDBC.CathedralFx;
import classes.deanery.Katedra;
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

public class ShowKatedraController {

	private MainController mainController;
	
	private ObservableList<CathedralFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<CathedralFx> tableKatedra;
	
	@FXML
	private TableColumn<String, ?> nazwaColumn, adresColumn, wydzialColumn;
	
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
			mainController.oos.writeObject(new String("showkatedra"));
			tableKatedra.setEditable(true);
			nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
			adresColumn.setCellValueFactory(new PropertyValueFactory<>("Adres"));
			wydzialColumn.setCellValueFactory(new PropertyValueFactory<>("Wydzial"));
			tableKatedra.setItems(data);
			Katedra readObjectKatedra = new Katedra();
			while (true) {
				try {
					readObjectKatedra = (Katedra) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectKatedra == null)
					break;
				else
					data.add( new CathedralFx(readObjectKatedra.getNazwa(), readObjectKatedra.getAdres(), readObjectKatedra.getNazwaWydzial()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
