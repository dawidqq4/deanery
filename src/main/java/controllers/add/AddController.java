package controllers.add;

import java.io.IOException;

import controllers.navigation.MainController;
import controllers.navigation.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class AddController {
	
	private MainController mainController;
	
	@FXML 
	public void addDziekan() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddDziekan.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddDziekanController addDziekanController = loader.getController();
		addDziekanController.setMainController(mainController);
		mainController.setScreen(anchorPane);
	}
	
	@FXML
	public void addWydzial() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddWydzial.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddWydzialController addWydzialController = loader.getController();
		addWydzialController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addWydzialController.wczytajDane();
	}
	
	@FXML
	public void addKierunek() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddKierunek.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddKierunekController addKierunekController = loader.getController();
		addKierunekController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addKierunekController.wczytajDane();
	}
	
	@FXML
	public void addRok() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddRocznik.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddRokController addRokController = loader.getController();
		addRokController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addRokController.wczytajDane();
	}
	
	@FXML
	public void addGrupa() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddGrupa.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddGrupaController addGrupaController = loader.getController();
		addGrupaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addGrupaController.wczytajDane();	
	}
	
	@FXML
	public void addStudent() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddStudent.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddStudentController addStudentController = loader.getController();
		addStudentController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addStudentController.wczytajDane();	
	}
	
	@FXML
	public void addWykladowca() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddWykladowca.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddWykladowcaController addWykladowcaController = loader.getController();
		addWykladowcaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addWykladowcaController.wczytajDane();	
	}
	
	@FXML
	public void addPrzedmiot() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddPrzedmiot.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddPrzedmiotController addPrzedmiotController = loader.getController();
		addPrzedmiotController.setMainController(mainController);
		mainController.setScreen(anchorPane);	
	}
	
	@FXML
	public void addKatedra() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddKatedra.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddKatedraController addKatedraController = loader.getController();
		addKatedraController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addKatedraController.wczytajDane();	
	}
	
	@FXML
	public void addSala() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddSala.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddSalaController addSalaController = loader.getController();
		addSalaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addSalaController.wczytajDane();	
	}
	
	@FXML
	public void addOcena() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddOcena.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddOcenaController addOcenaController = loader.getController();
		addOcenaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addOcenaController.wczytajDaneStudent();
		addOcenaController.wczytajDanePrzedmiot();
		addOcenaController.wczytajDaneWykladowca();
	}
	
	@FXML
	public void addZajecia() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/addScreens/AddZajecia.fxml"));
		
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddZajeciaController addZajeciaController = loader.getController();
		addZajeciaController.setMainController(mainController);
		mainController.setScreen(anchorPane);
		addZajeciaController.wczytajDaneWykladowca();
		addZajeciaController.wczytajDanePrzedmiot();
		addZajeciaController.wczytajDaneSala();
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
