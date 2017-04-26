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
			statusLabel.setText("Login musi si� sk�ada� z conajmniej 6 znak�w");
			isCorrectLogin = false;
		}
		else
			isCorrectLogin = true;
		
		if ( !(hasloField.getText().equals(confrimHasloField.getText())) ) {
			statusLabel.setText("Has�a r�ni� si�");
			isCorrectPassword = false;
		}
		else
			isCorrectPassword = true;
		
		if (hasloField.getText().length() < 8) {
			statusLabel.setText("Has�o musi si� sk�ada� z conajmniej 8 znak�w");
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
				statusLabel.setText("U�ytkownik o podanym loginie ju� istnieje");
			}
			statusLabel.setText("Wszystko si� zgadza, administrator musi zatwierdzi� proces");
		}
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
