package controllers.navigation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class MainController {

	@FXML
	private AnchorPane mainFrame;
	
	//komponenty po³¹czenia tcp
	public static String hostIP = "127.0.0.1";
	public static Socket socket = null;
	public static int port = 54321;
	public ObjectOutputStream oos;
	public ObjectInputStream ois;
	public static boolean connected = false;
	
	@FXML
	void initialize() throws IOException {
		if (!connected) {
			// stworzenie gniazda i powiazanie strumieni
			hostIP = "127.0.0.1";
			try {
				socket = new Socket(hostIP, port);
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				connected = true;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		LoadLoginScreen();
	}

	public void LoadLoginScreen() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/navigationScreens/LoginScreen.fxml"));
		AnchorPane anchorPane = null;
		try {
			anchorPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LoginController loginController = loader.getController();
		loginController.setMainController(this);
		setScreen(anchorPane);
	}

	public void setScreen(AnchorPane anchorPane) {
		mainFrame.getChildren().clear();
		mainFrame.getChildren().add(anchorPane);
	}
}
