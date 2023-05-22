package view.Menus;

import controller.SettingController;
import controller.Utils.UserUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Pane mainMenuPane = FXMLLoader.load(MainMenu.class.getResource("/FXML/MainMenu.fxml"));

        VBox vBox = new VBox();
        Text text = new Text(360, 50, "");
        text.setFill(Color.BLACK);
        Font font = Font.font("serif", FontWeight.BOLD, FontPosture.ITALIC, 12);
        text.setFont(font);

        if(UserUtils.isGuestPlayer()) {
            text.setText("You have entered as guest!");
            vBox.getChildren().add(text);
        }
        else {
            text.setText("Username: " + UserUtils.getCurrentUsername() +
                    " | Score: " + UserUtils.getCurrentUserScore());
            ImageView imageView = new ImageView(UserUtils.getCurrentUserAvatar());
            imageView.setFitWidth(60);
            imageView.setFitHeight(60);
            vBox.getChildren().addAll(imageView, text);
        }

        vBox.setAlignment(Pos.BASELINE_LEFT);
        mainMenuPane.getChildren().add(vBox);

        Scene scene = new Scene(mainMenuPane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }
}
