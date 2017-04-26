package controllers.navigation;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class AdminMenuController {

	private MainController mainController;

	@FXML
	public void confrimUsers() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/navigationScreens/ConfrimScreen.fxml"));

		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ConfrimUsersController confrimUsersController = loader.getController();
		confrimUsersController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		confrimUsersController.wczytajDane();
	}
	
	@FXML
	public void showUsers() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/navigationScreens/ShowUsers.fxml"));

		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowUsersController showUsersController = loader.getController();
		showUsersController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showUsersController.showData();
	}
	
	@FXML
	public void delete() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/navigationScreens/DeleteUserScreen.fxml"));

		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteUsersController deleteUsersController = loader.getController();
		deleteUsersController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteUsersController.wczytajDane();
	}
	
	@FXML
	public void openWorkMenu() {
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
	public void exitServer() {
		try {
			mainController.oos.writeObject("exitserver");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void logout() {
		mainController.LoadLoginScreen();
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
