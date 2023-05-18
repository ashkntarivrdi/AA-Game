package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginMenu extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        BorderPane borderPane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/LoginMenu.fxml"));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
//        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }
}
