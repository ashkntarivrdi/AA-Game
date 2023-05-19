package view;

import controller.Utils.UserUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            imageView.setFitWidth(50);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            HBox hBox = new HBox(text, imageView);
            hBox.setSpacing(310);
            vBox.getChildren().add(hBox);
        }

        vBox.setAlignment(Pos.BASELINE_LEFT);
        mainMenuPane.getChildren().add(vBox);

        Scene scene = new Scene(mainMenuPane);
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }
}
