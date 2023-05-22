package view.Menus;

import controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginMenu extends Application {
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        BorderPane borderPane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/LoginMenu.fxml"));
        Scene scene = new Scene(borderPane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Login Menu");
//        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();

    }
}
