package controllers.navigation;

import java.io.IOException;

import controllers.add.AddController;
import controllers.delete.DeleteController;
import controllers.edit.EditController;
import controllers.show.ShowController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class MenuController {

	private MainController mainController;

	@FXML
	AnchorPane menuFrame;
	
	@FXML
	public void openAddMenu() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddScreen.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AddController addController = loader.getController();
		addController.setMainController(mainController);
		mainController.setScreen(anchorPane); 
	}
	
	@FXML
	public void openEditMenu() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditScreen.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditController editController = loader.getController();
		editController.setMainController(mainController);
		mainController.setScreen(anchorPane);
	}
	
	@FXML
	public void openShowMenu() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowScreen.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ShowController showController = loader.getController();
		showController.setMainController(mainController);
		mainController.setScreen(anchorPane); 
	}
	
	@FXML
	public void openDeleteMenu() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteScreen.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DeleteController deleteController = loader.getController();
		deleteController.setMainController(mainController);
		mainController.setScreen(anchorPane); 
	}
	
	@FXML
	public void backToLoginFrame() {
		mainController.LoadLoginScreen();;
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
