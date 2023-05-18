package view;

import controller.Utils.UserUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        if(UserUtils.isGuestPlayer())
            text.setText("You have entered as guest!");
        else
            text.setText("Username: " + UserUtils.getCurrentUsername() +
                     " | Score: " + UserUtils.getCurrentUserScore());

        vBox.setAlignment(Pos.BASELINE_LEFT);
        vBox.getChildren().add(text);
        mainMenuPane.getChildren().add(vBox);

        Scene scene = new Scene(mainMenuPane);
        stage.setScene(scene);
        stage.show();
    }
}
