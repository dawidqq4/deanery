package controllers.navigation;

import java.io.IOException;

import classes.JDBC.UserFx;
import classes.deanery.User;
import controllers.navigation.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ShowUsersController {

	private MainController mainController;
	
	private ObservableList<UserFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<UserFx> tableUser;
	
	@FXML
	private TableColumn<String, ?> loginColumn, passwordColumn;
	
	@FXML
	public void backToAdminMenu() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/navigationScreens/AdminScreen.fxml"));

		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AdminMenuController adminMenuController = loader.getController();
		adminMenuController.setMainController(mainController);
		mainController.setScreen(anchorPane);
	}
	
	public void showData() {
		try {
			mainController.oos.writeObject(new String("showusers"));
			tableUser.setEditable(true);
			loginColumn.setCellValueFactory(new PropertyValueFactory<>("Login"));
			passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Haslo"));
			tableUser.setItems(data);
			User readObjectUser = new User();
			while (true) {
				try {
					readObjectUser = (User) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectUser == null)
					break;
				else
					data.add( new UserFx(readObjectUser.getLogin(), readObjectUser.getPassword()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
