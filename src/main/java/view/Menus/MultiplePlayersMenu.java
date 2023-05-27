package view.Menus;

import controller.GameController;
import controller.SettingController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;

public class MultiplePlayersMenu extends Application {
    GameController gameController = new GameController();
    public static boolean isMultiplePlayer = false;
    @Override
    public void start(Stage stage) throws Exception {
        GameMenu.gamePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/MultiplePlayers.fxml"));
        GameMenu.pausePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/PauseMenu.fxml"));

        CenterBall innerBall = new CenterBall(250, 250);
        CenterBall outerBall = new CenterBall(250, 300, 150);

        ProgressBar progressBar = new ProgressBar(0);
        Text score = new Text();
        Label timeLabel = gameController.generateTimer();

        VBox vBox = new VBox(GameMenu.getProgressBarText(), progressBar, score, timeLabel);
        vBox.setAlignment(Pos.TOP_LEFT);

        GameMenu.gamePane.getChildren().addAll(innerBall, outerBall, vBox);

        Scene scene = new Scene(GameMenu.gamePane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        if(GameController.rotateAnimation != null)
            GameController.rotateAnimation.play();

        Button button = new Button();
        button.setTranslateX(-50);
        button.setFocusTraversable(false);
        GameMenu.gamePane.getChildren().add(button);

        GameMenu.mediaList.add(new Media(GameController.class.getResource("/musics/Music1.mp3").toExternalForm()));
        GameMenu.mediaList.add(new Media(GameController.class.getResource("/musics/Music2.mp3").toExternalForm()));
        GameMenu.mediaList.add(new Media(GameController.class.getResource("/musics/Music3.mp3").toExternalForm()));

        GameMenu.mediaPlayer = new MediaPlayer(GameMenu.mediaList.get(0));

        if (!SettingController.isMute())
            GameMenu.mediaPlayer.setAutoPlay(true);

        isMultiplePlayer = true;
        gameController.createDefaultBalls(GameMenu.gamePane, outerBall);
        initializeGame(GameMenu.gamePane, outerBall, progressBar, button, score, scene, GameMenu.pausePane);

        GameMenu.gamePane.requestFocus();

        stage.setTitle("Multiple Players");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeGame(Pane gamePane, CenterBall outerBall, ProgressBar progressBar, Button button, Text score, Scene scene, Pane pausePane) {
        gameController.setNumberOfBallsLeft(CurrentGame.getNumberOfBalls());
        gameController.setNumberOfUpperBallsLeft(CurrentGame.getNumberOfBalls());
        gameController.resetScore();
        CurrentGame.resetBalls();
        GameController.isGameOver = false;


        Ball ball = new Ball();
        Ball upperBall = new Ball(250, 50, 10, Color.WHITE);
        gamePane.getChildren().addAll(ball, upperBall);

        gamePane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().getName();
                if(keyName.equals(CurrentGame.getShootKey())) {
                    try {
                        gameController.shoot(ball, gamePane, outerBall, progressBar, button, score);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (keyName.equals(KeyCode.ENTER.getName())) {
                    try {
                        gameController.upperShoot(upperBall, gamePane, outerBall, progressBar, button, score);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(keyName.equals(CurrentGame.getFreezeKey())) {
                    if(progressBar.getProgress() >= 1)
                        gameController.freeze(progressBar, scene);
                }
                else if(keyName.equals(KeyCode.ESCAPE.getName())) {
                    gameController.enterPauseMenu(gamePane, pausePane);
                }
                if (gameController.getNumberOfBallsLeft() <= CurrentGame.getNumberOfBalls()/4) {
                    if (keyName.equals(CurrentGame.getRightKey()))
                        gameController.moveRight(ball);
                    else if (keyName.equals(CurrentGame.getLeftKey()))
                        gameController.moveLeft(ball);
                }
            }

        });
    }
}
