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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;

import java.util.ArrayList;

public class GameMenu extends Application{
    GameController gameController = new GameController();
    public static MediaPlayer mediaPlayer;
    public static ArrayList<Media> mediaList = new ArrayList<>();
    public static int currentMediaIndex = 0;
    public static Pane gamePane;
    public static Pane pausePane;
    @Override
    public void start(Stage stage) throws Exception {
        gamePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/GameMenu.fxml"));
        pausePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/PauseMenu.fxml"));

        CenterBall innerBall = new CenterBall(250, 250);
        CenterBall outerBall = new CenterBall(250, 250, 150);

        ProgressBar progressBar = new ProgressBar(0);
        Text score = new Text();
        Label timeLabel = gameController.generateTimer();

        VBox vBox = new VBox(getProgressBarText(), progressBar, score, timeLabel);
        vBox.setAlignment(Pos.TOP_LEFT);

        gamePane.getChildren().addAll(innerBall, outerBall, vBox);

        Scene scene = new Scene(gamePane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        if(GameController.rotateAnimation != null)
            GameController.rotateAnimation.play();

        Button button = new Button();
        button.setTranslateX(-50);
        button.setFocusTraversable(false);
        gamePane.getChildren().add(button);

        mediaList.add(new Media(GameController.class.getResource("/musics/Music1.mp3").toExternalForm()));
        mediaList.add(new Media(GameController.class.getResource("/musics/Music2.mp3").toExternalForm()));
        mediaList.add(new Media(GameController.class.getResource("/musics/Music3.mp3").toExternalForm()));

        mediaPlayer = new MediaPlayer(mediaList.get(0));

        if (!SettingController.isMute())
            mediaPlayer.setAutoPlay(true);

        gameController.createDefaultBalls(gamePane, outerBall);
        initializeGame(gamePane, outerBall, progressBar, button, score, scene, pausePane);

        gamePane.requestFocus();

        stage.setTitle("Game Menu");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeGame(Pane gamePane, CenterBall outerBall, ProgressBar progressBar, Button button, Text score, Scene scene, Pane pausePane) {
        gameController.setNumberOfBallsLeft(CurrentGame.getNumberOfBalls());
        gameController.resetScore();
        CurrentGame.resetBalls();
        GameController.isGameOver = false;


        Ball ball = new Ball();
        gamePane.getChildren().add(ball);

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

    public static Text getProgressBarText() {
        Text text = new Text("Freeze");
        text.setFill(Color.LIGHTBLUE);
        Font font = Font.font("serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 14);
        text.setFont(font);
        return text;
    }

}
