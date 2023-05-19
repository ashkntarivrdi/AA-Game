package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SignupMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane signupPane = FXMLLoader.load(SignupMenu.class.getResource("/FXML/SignupMenu.fxml"));
        Scene scene = new Scene(signupPane);
        stage.setScene(scene);
        stage.setTitle("Signup Menu");
//        stage.setMaximized(true);
        stage.show();
    }
}
