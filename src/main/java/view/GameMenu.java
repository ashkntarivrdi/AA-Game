package view;

import controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Ball;

public class GameMenu extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane gamePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/GameMenu.fxml"));

        Ball innerBall = createInnerBall();
        Ball outerBall = createOuterBall(gamePane);
        gamePane.getChildren().addAll(outerBall, innerBall);

        Scene scene = new Scene(gamePane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        stage.setTitle("Game Menu");
        stage.setScene(scene);
        stage.show();
    }

    private Ball createInnerBall() {
        //TODO: make y center of the window for two players
        Ball innerBall = new Ball(250, 250, 80, Color.WHEAT);
        return innerBall;
    }

    private Ball createOuterBall(BorderPane gamePane) {
        Ball outerBall = new Ball(250, 250, 100, Color.BLACK);
        return outerBall;
    }
}
