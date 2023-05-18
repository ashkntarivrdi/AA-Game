package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Ball;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane mainMenuPane = FXMLLoader.load(MainMenu.class.getResource("/FXML/MainMenu.fxml"));
        Scene scene = new Scene(mainMenuPane);
        stage.setScene(scene);
        stage.show();
    }
}
