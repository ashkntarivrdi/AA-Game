package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProfileMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane profileMenuPane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/ProfileMenu.fxml"));
        Scene scene = new Scene(profileMenuPane);
        stage.setScene(scene);
        stage.show();
    }
}
