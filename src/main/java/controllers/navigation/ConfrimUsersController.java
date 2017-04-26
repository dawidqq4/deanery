package controllers.navigation;

import java.io.IOException;

import classes.deanery.User;
import controllers.navigation.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ConfrimUsersController {

	private MainController mainController;
	
	private ObservableList<User> data = FXCollections.observableArrayList();
	private User readUser = new User();
	
	@FXML
	private ComboBox<User> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField loginField;
	
	@FXML
	public void chooseData() {
		readUser = (User) choose.getValue();
		loginField.setText(readUser.getLogin());
		loginField.setEditable(false);
	}
	
	@FXML
	public void confrim() {
		if (readUser != null ) {	
			try {
				mainController.oos.writeObject("confrimuser");
				mainController.oos.writeObject(readUser);
				backToAdminMenu();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
			statusLabel.setText("Podano b³êdne dane");
	}
	
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
	
	public void wczytajDane() {
		try {
			mainController.oos.writeObject("showusers");
			while(true) {
				try {
					readUser = (User) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readUser == null)
					break;
				if ( (readUser.getApproved() == 9) || (readUser.getApproved() == 1))
					continue;
				else
					data.add(new User(readUser.getIdUser(), readUser.getLogin(), readUser.getPassword(), readUser.getApproved()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		choose.setItems(data);
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
