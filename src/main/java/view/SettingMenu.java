package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Set;

public class SettingMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(SettingMenu.class.getResource("/FXML/SettingMenu.fxml"));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.show();
    }
}
