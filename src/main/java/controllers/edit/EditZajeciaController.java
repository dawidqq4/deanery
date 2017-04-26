package controllers.edit;

import java.io.IOException;

import classes.deanery.Przedmiot;
import classes.deanery.Sala;
import classes.deanery.Wykladowca;
import classes.deanery.Zajecia;
import controllers.navigation.MainController;
import controllers.navigation.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditZajeciaController {
	
	private MainController mainController;
	private Sala readSala;
	private Przedmiot readPrzedmiot;
	private Wykladowca readWykladowca;
	private Zajecia readZajecia;
	private ObservableList<Wykladowca> dataWykladowca = FXCollections.observableArrayList();
	private ObservableList<Przedmiot> dataPrzedmiot = FXCollections.observableArrayList();
	private ObservableList<Sala> dataSala = FXCollections.observableArrayList();
	private ObservableList<Zajecia> dataZajecia = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Zajecia> chooseZajecia;
	
	@FXML
	private ComboBox<Sala> chooseSl;
	
	@FXML
	private ComboBox<Przedmiot> choosePrz;
	
	@FXML
	private ComboBox<Wykladowca> chooseWyk;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField godzinaField;
		
	@FXML
	public void chooseWykladowca() {
		readWykladowca = (Wykladowca) chooseWyk.getValue();
	}
	@FXML
	public void choosePrzedmiot() {
		readPrzedmiot = (Przedmiot) choosePrz.getValue();
	}

	@FXML
	public void chooseSala() {
		readSala = (Sala) chooseSl.getValue();
	}
	
	@FXML
	public void chooseDataZajecia() {
		readZajecia = (Zajecia) chooseZajecia.getValue();
		godzinaField.setText(readZajecia.getGodzina());
	}
	
	@FXML
	public void edit() {
		if ((godzinaField.getText().length() != 0) && readSala != null && readPrzedmiot != null && readWykladowca != null && readZajecia != null) {
			Zajecia zaj = new Zajecia(readZajecia.getIdZajecia(), godzinaField.getText(), readWykladowca.getIdWykladowca(), readPrzedmiot.getIdPrzedmiot(), readSala.getIdSala());
			try {
				mainController.oos.writeObject("editzajecia");
				mainController.oos.writeObject(zaj);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Zmodyfikowano dane");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			backToMenu();
		}
		else
			statusLabel.setText("Podano b³êdne dane");	
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
	
	public void wczytajDaneZajecia() {
		try {
			mainController.oos.writeObject("showzajecia");
			while(true) {
				try {
					readZajecia = (Zajecia) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readZajecia == null)
					break;
				else
					dataZajecia.add(new Zajecia(readZajecia.getIdZajecia(), readZajecia.getGodzina(), readZajecia.getIdWykladowca(), readZajecia.getNazwiskoWykladowca(), readZajecia.getIdPrzedmiot(), readZajecia.getNazwaPrzedmiot(), readZajecia.getIdSala(), readZajecia.getNazwaSala()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseZajecia.setItems(dataZajecia);
	}
		
	public void wczytajDaneWykladowca() {
		try {
			mainController.oos.writeObject("showwykladowca");
			while (true) {
				try {
					readWykladowca = (Wykladowca) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readWykladowca == null)
					break;
				else
					dataWykladowca.add(new Wykladowca(readWykladowca.getIdWykladowca(), readWykladowca.getNazwisko(), readWykladowca.getImie(), readWykladowca.getTytul(), readWykladowca.getNazwaKatedra(), readWykladowca.getIdKatedra()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseWyk.setItems(dataWykladowca);
	}
	
	public void wczytajDanePrzedmiot() {
		try {
			mainController.oos.writeObject("showprzedmiot");
			while (true) {
				try {
					readPrzedmiot = (Przedmiot) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readPrzedmiot == null)
					break;
				else
					dataPrzedmiot.add(new Przedmiot(readPrzedmiot.getIdPrzedmiot(), readPrzedmiot.getNazwa(), readPrzedmiot.getWaga()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		choosePrz.setItems(dataPrzedmiot);
	}
	
	public void wczytajDaneSala() {
		try {
			mainController.oos.writeObject("showsala");
			while (true) {
				try {
					readSala = (Sala) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readSala == null)
					break;
				else 
					dataSala.add(new Sala(readSala.getIdSala(), readSala.getNazwa(), readSala.getNazwaWydzial(), readSala.getidWydzial()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseSl.setItems(dataSala);
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
