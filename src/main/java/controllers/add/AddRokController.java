package controllers.add;

import java.io.IOException;

import classes.deanery.Kierunek;
import classes.deanery.Rok;
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

public class AddRokController {

	private MainController mainController;
	private Kierunek readKierunek;
	private ObservableList<Kierunek> data = FXCollections.observableArrayList();
	private int rokToInt = 0;

	@FXML
	private ComboBox<Kierunek> choose;

	@FXML
	private Label statusLabel;

	@FXML
	private TextField rokField;

	public boolean isStringInt(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	@FXML
	public void chooseKierunek() {
		readKierunek = (Kierunek) choose.getValue();
	}

	@FXML
	public void add() {
		if (isStringInt(rokField.getText()) == true)
			rokToInt = Integer.parseInt(rokField.getText());
		else {
			statusLabel.setText("B³êdny rocznik");
			rokToInt = 0;
		}
		if ((rokToInt > 1900) && (rokToInt < 2100) && readKierunek != null) {
			Rok r = new Rok(rokToInt, readKierunek.getidKierunek());
			try {
				mainController.oos.writeObject("addrok");
				mainController.oos.writeObject(r);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Dodano rocznik");
				}
			} catch (IOException e) {
				statusLabel.setText("B³¹d dodawania");
			}
			backToMenu();
		} else
			statusLabel.setText("B³êdne dane");
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

	public void wczytajDane() {
		try {
			mainController.oos.writeObject("showkier");
			while (true) {
				try {
					readKierunek = (Kierunek) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readKierunek == null)
					break;
				else
					data.add(new Kierunek(readKierunek.getidKierunek(), readKierunek.getNazwa(), readKierunek.getNazwaWydzial(), readKierunek.getidWydzial()));
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
