package controllers.edit;

import java.io.IOException;

import classes.deanery.Grupa;
import classes.deanery.Student;
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

public class EditStudentController {

	private MainController mainController;
	private Grupa readGrupa;
	private Student readStudent;
	private ObservableList<Grupa> dataGrupa = FXCollections.observableArrayList();
	private ObservableList<Student> dataStudent = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Grupa> chooseGrupa;
	
	@FXML
	private ComboBox<Student> chooseStudent;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nazwiskoField, imieField, adresField;
	
	@FXML
	public void chooseDataStudent() {
		readStudent = (Student) chooseStudent.getValue();
		nazwiskoField.setText(readStudent.getNazwisko());
		imieField.setText(readStudent.getImie());
		adresField.setText(readStudent.getAdres());
	}
	
	@FXML
	public void chooseDataGrupa() {
		readGrupa = (Grupa) chooseGrupa.getValue();
	}
	
	@FXML
	public void edit() {
		if ( (nazwiskoField.getText().length() != 0) && (imieField.getText().length() != 0) && (adresField.getText().length() != 0) && readGrupa != null && readStudent != null ) {
			Student st = new Student(readStudent.getidStudent(), nazwiskoField.getText(), imieField.getText(), adresField.getText(), readGrupa.getnazwaGrupa(), readGrupa.getidGrupa());
			try {
				mainController.oos.writeObject("editstudent");
				mainController.oos.writeObject(st);
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
		
	public void wczytajDaneGrupa() {
		try {
			mainController.oos.writeObject("showgrupa");
			while(true) {
				try {
					readGrupa = (Grupa) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readGrupa == null)
					break;
				else
					dataGrupa.add(new Grupa(readGrupa.getidGrupa(), readGrupa.getnazwaGrupa(), readGrupa.getRocznik(), readGrupa.getidRok()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseGrupa.setItems(dataGrupa);
	}
	
	public void wczytajDaneStudent() {
		try {
			mainController.oos.writeObject("showstudent");
			while(true) {
				try {
					readStudent = (Student) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readStudent == null)
					break;
				else
					dataStudent.add(new Student(readStudent.getidStudent(), readStudent.getNazwisko(), readStudent.getImie(), readStudent.getAdres(), readStudent.getNazwaGrupa(), readStudent.getidGrupa()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooseStudent.setItems(dataStudent);
	}
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
