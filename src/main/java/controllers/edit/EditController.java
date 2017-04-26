package controllers.edit;

import java.io.IOException;

import controllers.navigation.MainController;
import controllers.navigation.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class EditController {

	private MainController mainController;
	
	@FXML
	public void editDziekan() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditDziekan.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditDziekanController editDziekanController = loader.getController();
		editDziekanController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editDziekanController.wczytajDane();
	}
	
	@FXML
	public void editWydzial() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditWydzial.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditWydzialController editWydzialController = loader.getController();
		editWydzialController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editWydzialController.wczytajDaneDziekan();
		editWydzialController.wczytajDaneWydzial();
	}
	
	@FXML
	public void editKierunek() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditKierunek.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditKierunekController editKierunekController = loader.getController();
		editKierunekController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editKierunekController.wczytajDaneWydzial();
		editKierunekController.wczytajDaneKierunek();
	}
	
	@FXML
	public void editRocznik() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditRocznik.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditRocznikController editRocznikController = loader.getController();
		editRocznikController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editRocznikController.wczytajDaneKierunek();
		editRocznikController.wczytajDaneRok();
	}
	
	@FXML
	public void editGrupa() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditGrupa.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditGrupaController editGrupaController = loader.getController();
		editGrupaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editGrupaController.wczytajDaneRok();
		editGrupaController.wczytajDaneGrupa();
	}
	
	@FXML
	public void editStudent() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditStudent.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditStudentController editStudentController = loader.getController();
		editStudentController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editStudentController.wczytajDaneGrupa();
		editStudentController.wczytajDaneStudent();
	}
	
	@FXML
	public void editWykladowca() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditWykladowca.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditWykladowcaController editWykladowcaController = loader.getController();
		editWykladowcaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editWykladowcaController.wczytajDaneKatedra();
		editWykladowcaController.wczytajDaneWykladowca();
	}
	
	@FXML
	public void editKatedra() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditKatedra.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditKatedraController editKatedraController = loader.getController();
		editKatedraController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editKatedraController.wczytajDaneWydzial();
		editKatedraController.wczytajDaneKatedra();
	}
	
	@FXML
	public void editPrzedmiot() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditPrzedmiot.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditPrzedmiotController editPrzedmiotController = loader.getController();
		editPrzedmiotController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editPrzedmiotController.wczytajDane();
	}
	
	@FXML
	public void editSala() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditSala.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditSalaController editSalaController = loader.getController();
		editSalaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editSalaController.wczytajDaneSala();
		editSalaController.wczytajDaneWydzial();
	}
	
	@FXML
	public void editOcena() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditOcena.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditOcenaController editOcenaController = loader.getController();
		editOcenaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editOcenaController.wczytajDaneStudent();
		editOcenaController.wczytajDanePrzedmiot();
		editOcenaController.wczytajDaneWykladowca();
		editOcenaController.wczytajDaneOcena();
	}
	
	@FXML
	public void editZajecia() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editScreens/EditZajecia.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditZajeciaController editZajeciaController = loader.getController();
		editZajeciaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		editZajeciaController.wczytajDaneZajecia();
		editZajeciaController.wczytajDaneWykladowca();
		editZajeciaController.wczytajDanePrzedmiot();
		editZajeciaController.wczytajDaneSala();
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
