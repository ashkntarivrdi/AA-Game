package view;

import controller.SettingController;
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
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Profile Menu");
        stage.show();
    }
}
