package controllers.delete;

import java.io.IOException;

import controllers.navigation.MainController;
import controllers.navigation.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class DeleteController {
	
	private MainController mainController;
	
	@FXML
	public void deleteDziekan() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteDziekan.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteDziekanController deleteDziekanController = loader.getController();
		deleteDziekanController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteDziekanController.wczytajDane();
	}
	
	@FXML
	public void deleteWydzial() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteWydzial.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteWydzialController deleteWydzialController = loader.getController();
		deleteWydzialController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteWydzialController.wczytajDane();
	}
	
	@FXML
	public void deleteKierunek() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteKierunek.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteKierunekController deleteKierunekController = loader.getController();
		deleteKierunekController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteKierunekController.wczytajDane();	
	}
	
	@FXML
	public void deleteRocznik() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteRok.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteRokController deleteRokController = loader.getController();
		deleteRokController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteRokController.wczytajDane();		
	}
	
	@FXML
	public void deleteGrupa() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteGrupa.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteGrupaController deleteGrupaController = loader.getController();
		deleteGrupaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteGrupaController.wczytajDane();	
	}
	
	@FXML
	public void deleteStudent() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteStudent.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteStudentController deleteStudentController = loader.getController();
		deleteStudentController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteStudentController.wczytajDane();
	}
	
	@FXML
	public void deleteKatedra() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteKatedra.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteKatedraController deleteKatedraController = loader.getController();
		deleteKatedraController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteKatedraController.wczytajDane();
	}
	
	@FXML
	public void deletePrzedmiot() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeletePrzedmiot.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeletePrzedmiotController deletePrzedmiotController = loader.getController();
		deletePrzedmiotController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deletePrzedmiotController.wczytajDane();
	}
	
	@FXML
	public void deleteWykladowca() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteWykladowca.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteWykladowcaController deleteWykladowcaController = loader.getController();
		deleteWykladowcaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteWykladowcaController.wczytajDane();
	}
	
	@FXML
	public void deleteSala() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteSala.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteSalaController deleteSalaController = loader.getController();
		deleteSalaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteSalaController.wczytajDane();
	}
	
	@FXML
	public void deleteOcena() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteOcena.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteOcenaController deleteOcenaController = loader.getController();
		deleteOcenaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteOcenaController.wczytajDane();
	}
	
	@FXML
	public void deleteZajecia() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/deleteScreens/DeleteZajecia.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DeleteZajeciaController deleteZajeciaController = loader.getController();
		deleteZajeciaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		deleteZajeciaController.wczytajDane();
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
