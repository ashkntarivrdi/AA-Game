package view;

import controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SettingMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(SettingMenu.class.getResource("/FXML/SettingMenu.fxml"));
        Scene scene = new Scene(borderPane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.show();
    }
}
