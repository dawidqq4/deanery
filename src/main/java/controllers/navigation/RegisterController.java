package controllers.navigation;

import java.io.IOException;

import classes.deanery.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
	
	private MainController mainController;
	private boolean isCorrectLogin, isCorrectPassword, isCorrectConfrim = false;
	
	@FXML
	private TextField loginField;
	
	@FXML
	private PasswordField hasloField, confrimHasloField;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	public void backToMenu() {
		mainController.LoadLoginScreen();
	}
	
	@FXML
	public void register() {
		if (loginField.getText().length() < 6) {
			statusLabel.setText("Login musi siê sk³adaæ z conajmniej 6 znaków");
			isCorrectLogin = false;
		}
		else
			isCorrectLogin = true;
		
		if ( !(hasloField.getText().equals(confrimHasloField.getText())) ) {
			statusLabel.setText("Has³a ró¿ni¹ siê");
			isCorrectPassword = false;
		}
		else
			isCorrectPassword = true;
		
		if (hasloField.getText().length() < 8) {
			statusLabel.setText("Has³o musi siê sk³adaæ z conajmniej 8 znaków");
			isCorrectConfrim = false;
		}
		else
			isCorrectConfrim = true;
		
		if (isCorrectLogin == true && isCorrectPassword == true && isCorrectConfrim == true) {
			try {
				User user = new User(loginField.getText(), hasloField.getText());
				mainController.oos.writeObject("adduser");
				mainController.oos.writeObject(user);
			} catch (IOException e) {
				statusLabel.setText("U¿ytkownik o podanym loginie ju¿ istnieje");
			}
			statusLabel.setText("Wszystko siê zgadza, administrator musi zatwierdziæ proces");
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
