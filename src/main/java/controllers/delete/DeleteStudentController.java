package controllers.delete;

import java.io.IOException;

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

public class DeleteStudentController {

	private ObservableList<Student> data = FXCollections.observableArrayList();
	private Student readStudent = new Student();
	private MainController mainController;
	
	@FXML
	private ComboBox<Student> choose;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextField nazwiskoField, imieField, adresField, grupaField;
	
	@FXML
	public void chooseData() {
		readStudent = (Student) choose.getValue();
		nazwiskoField.setText(readStudent.getNazwisko());
		nazwiskoField.setEditable(false);
		imieField.setText(readStudent.getImie());
		imieField.setEditable(false);
		adresField.setText(readStudent.getAdres());
		adresField.setEditable(false);
		grupaField.setText(readStudent.getNazwaGrupa());
		grupaField.setEditable(false);
	}
	
	@FXML
	public void delete() {
		if ( (nazwiskoField.getText().length() != 0) && (imieField.getText().length() != 0) && (adresField.getText().length() != 0) && (grupaField.getText().length() != 0) && readStudent != null ) {	
			Student st = new Student(readStudent.getidStudent(), readStudent.getNazwisko(), readStudent.getImie(), readStudent.getAdres(), readStudent.getNazwaGrupa(), readStudent.getidGrupa());
			try {
				mainController.oos.writeObject("deletestudent");
				mainController.oos.writeObject(st);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					statusLabel.setText("Usuniêto dane");
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
	
	public void wczytajDane() {
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
					data.add(new Student(readStudent.getidStudent(), readStudent.getNazwisko(), readStudent.getImie(), readStudent.getAdres(), readStudent.getNazwaGrupa(), readStudent.getidGrupa()));
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
