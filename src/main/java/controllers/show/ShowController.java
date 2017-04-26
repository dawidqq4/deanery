package controllers.show;

import java.io.IOException;

import controllers.navigation.MainController;
import controllers.navigation.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ShowController {

	private MainController mainController;
	
	@FXML
	public void showDziekan() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowDziekan.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowDziekanController showDziekanController = loader.getController();
		showDziekanController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showDziekanController.showData();
	}
	
	@FXML
	public void showWydzial() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowWydzial.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowWydzialController showWydzialController = loader.getController();
		showWydzialController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showWydzialController.showData();
	}
	
	@FXML
	public void showKierunek() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowKierunek.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowKierunekController showKierunekController = loader.getController();
		showKierunekController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showKierunekController.showData();
	}
	
	@FXML
	public void showRocznik() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowRocznik.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowRokController showRokController = loader.getController();
		showRokController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showRokController.showData();
	}	
	
	@FXML
	public void showGrupa() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowGrupa.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowGrupaController showGrupaController = loader.getController();
		showGrupaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showGrupaController.showData();
	}
	
	@FXML
	public void showStudent() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowStudent.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowStudentController showStudentController = loader.getController();
		showStudentController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showStudentController.showData();
	}
	
	@FXML
	public void showWykladowca() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowWykladowca.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowWykladowcaController showWykladowcaController = loader.getController();
		showWykladowcaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showWykladowcaController.showData();
	}
	
	@FXML
	public void showKatedra() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowKatedra.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowKatedraController showKatedraController = loader.getController();
		showKatedraController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showKatedraController.showData();
	}
	
	@FXML
	public void showPrzedmiot() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowPrzedmiot.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowPrzedmiotController showPrzedmiotController = loader.getController();
		showPrzedmiotController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showPrzedmiotController.showData();
	}
	
	@FXML
	public void showSala() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowSala.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowSalaController showSalaController = loader.getController();
		showSalaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showSalaController.showData();
	}	
	
	@FXML
	public void showOcena() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowOcena.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowOcenaController showOcenaController = loader.getController();
		showOcenaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showOcenaController.showData();
	}
	
	@FXML
	public void showZajecia() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowZajecia.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShowZajeciaController showZajeciaController = loader.getController();
		showZajeciaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		showZajeciaController.showData();
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
