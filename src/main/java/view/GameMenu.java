package view;

import controller.GameController;
import controller.SettingController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;

import javax.swing.*;

public class GameMenu extends Application{
    GameController gameController = new GameController();
    @Override
    public void start(Stage stage) throws Exception {
        Pane gamePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/GameMenu.fxml"));

        CenterBall innerBall = new CenterBall();
        CenterBall outerBall = new CenterBall(150);

        Text phaseNumber = getPhaseNumber(outerBall);

        gamePane.getChildren().addAll(innerBall, phaseNumber);

        Scene scene = new Scene(gamePane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        initializeGame(gamePane, outerBall);

        gamePane.requestFocus();
        stage.setTitle("Game Menu");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeGame(Pane gamePane, CenterBall outerBall) {
        Ball ball = new Ball();
        gamePane.getChildren().add(ball);

            gamePane.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    String keyName = event.getCode().getName();
                    if(keyName.equals("Space")) {
                        gameController.shoot(ball, gamePane, outerBall);
                    }
                }

            });
    }

    public Text getPhaseNumber(CenterBall centerBall) {
        Text phaseNumber = new Text(centerBall.getCenterX() - 18, centerBall.getCenterY() + 18,
                "" + CurrentGame.getPhase().getPhase());
        phaseNumber.setFill(Color.WHITE);
        phaseNumber.setFont(new Font(65));
        return phaseNumber;
    }

}
