package view;

import controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SettingMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(SettingMenu.class.getResource("/FXML/SettingMenu.fxml"));
        Scene scene = new Scene(borderPane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        RadioButton mute = new RadioButton("Mute Sound");
        if (SettingController.isMute())
            mute.setSelected(true);
        mute.setOnAction(response -> {
            SettingController.setMute(!SettingController.isMute());
        });

        RadioButton darkMode = new RadioButton("Dark Mode");
        if (SettingController.isDarkMode())
            darkMode.setSelected(true);
        darkMode.setOnAction(response -> {
            SettingController.setDarkMode(!SettingController.isDarkMode());
            try {
                new SettingMenu().start(LoginMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        HBox hBox = new HBox(mute, darkMode);
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.TOP_LEFT);
        borderPane.setTop(hBox);

        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.show();
    }
}
