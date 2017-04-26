package controllers.navigation;

import java.io.IOException;
import java.util.LinkedList;

import classes.deanery.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {

	private MainController mainController;

	@FXML
	private TextField loginField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label statusLabel;

	@FXML
	public void login() {
		try {
			User user = new User(loginField.getText(), passwordField.getText());
			mainController.oos.writeObject("showusers");
			LinkedList<User> users = new LinkedList<User>();
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
					users.add(new User(readObjectUser.getIdUser(), readObjectUser.getLogin(), readObjectUser.getPassword(), readObjectUser.getApproved()));
			}
			User tmpUser = new User();
			for (int i = 0; i < users.size(); i++) {
				tmpUser = new User(users.get(i).getLogin(), users.get(i).getPassword());
				if ((user.getLogin().equals(tmpUser.getLogin()) == true) && (user.getPassword().equals(tmpUser.getPassword()) == true))
					if (users.get(i).getApproved() == 9) 
						LoadAdminMenuScreen();
					else 
						if (users.get(i).getApproved() == 1) 
							LoadMenuScreen();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void LoadAdminMenuScreen() {
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
	
	public void LoadMenuScreen() {
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

	@FXML
	public void register() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/navigationScreens/RegisterScreen.fxml"));

		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		RegisterController registerController = loader.getController();
		registerController.setMainController(mainController);
		mainController.setScreen(anchorPane);
	}

	@FXML
	public void exitFrame() {
		Platform.exit();
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
