package view;

import controller.MainController;
import controller.Utils.UserUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ball;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Pane mainMenuPane = FXMLLoader.load(MainMenu.class.getResource("/FXML/MainMenu.fxml"));

        HBox hBox = new HBox();
        Text text = new Text(360, 50, "");
        text.setFill(Color.BLACK);
        Font font = Font.font("serif", FontWeight.BOLD, FontPosture.ITALIC, 12);
        text.setFont(font);
        if(UserUtils.isGuestPlayer()) {
            text.setText("You have entered as guest!");
            text.setTranslateX(70);
            text.setTranslateY(10);

        }
        else {
            text.setText("Username: " + UserUtils.getCurrentUsername() +
                    '\n' + "Score: " + UserUtils.getCurrentUserScore());
            text.setTranslateX(52);
            text.setTranslateY(16);
        }
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(text);
        mainMenuPane.getChildren().add(hBox);


        Scene scene = new Scene(mainMenuPane);
        stage.setScene(scene);
        stage.show();
    }
}
