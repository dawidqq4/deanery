package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/navigationScreens/MainScreen.fxml"));
		AnchorPane loginPane = loader.load();
		Scene scene = new Scene(loginPane);
		scene.getStylesheets().add(this.getClass().getResource("/css/fxStyles.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Dziekanat TCP");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}
