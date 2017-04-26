package controllers.show;

import java.io.IOException;

import classes.JDBC.StudentFx;
import classes.deanery.Student;
import controllers.navigation.MainController;
import controllers.navigation.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ShowStudentController {

	private MainController mainController;
	private ObservableList<StudentFx> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<StudentFx> tableStudent;
	
	@FXML
	private TableColumn<String, ?> nazwiskoColumn, imieColumn, adresColumn, grupaColumn;
	
	@FXML
	public void showOcena() {
		StudentFx studentFx = tableStudent.getSelectionModel().getSelectedItem();
		Student readObjectStudent = new Student(studentFx.getId(), studentFx.getNazwisko(), studentFx.getImie(), studentFx.getAdres(), studentFx.getGrupa());
		try {
			mainController.oos.writeObject(new String("showocenastudent"));
			mainController.oos.writeObject(readObjectStudent);
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/showScreens/ShowOcenaStudent.fxml"));
			
			AnchorPane anchorPane = null;
			try {
				anchorPane = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ShowOcenaStudentController showOcenaStudentController = loader.getController();
			showOcenaStudentController.setMainController(mainController);
			mainController.setScreen(anchorPane);
			showOcenaStudentController.showData();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void showData() {
		try {
			mainController.oos.writeObject(new String("showstudent"));
			tableStudent.setEditable(true);
			nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
			imieColumn.setCellValueFactory(new PropertyValueFactory<>("Imie"));
			adresColumn.setCellValueFactory(new PropertyValueFactory<>("Adres"));
			grupaColumn.setCellValueFactory(new PropertyValueFactory<>("Grupa"));
			tableStudent.setItems(data);
			Student readObjectStudent = new Student();
			while (true) {
				try {
					readObjectStudent = (Student) mainController.ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (readObjectStudent == null)
					break;
				else
					data.add( new StudentFx(readObjectStudent.getidStudent(), readObjectStudent.getNazwisko(), readObjectStudent.getImie(), readObjectStudent.getAdres(), readObjectStudent.getNazwaGrupa()) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
