package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ScoreBoard extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(ScoreBoard.class.getResource("/FXML/ScoreBoard.fxml"));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("ScoreBoard");
        stage.show();
    }
}
