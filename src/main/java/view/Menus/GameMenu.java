package view.Menus;

import controller.GameController;
import controller.SettingController;
import enums.Phase;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;

import javax.security.auth.login.AppConfigurationEntry;

public class GameMenu extends Application{
    GameController gameController = new GameController();
    public static MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage) throws Exception {
        Pane gamePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/GameMenu.fxml"));

        CenterBall innerBall = new CenterBall();
        CenterBall outerBall = new CenterBall(150);

//        Text phaseNumber = getPhaseNumber(outerBall);

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

        Media media = new Media(GameController.class.getResource("/musics/Music1.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        gameController.createDefaultBalls(gamePane, outerBall);
        initializeGame(gamePane, outerBall, progressBar, button, score, scene);

//        if (CurrentGame.getPhase() == Phase.ONE)
//            initializeGamePhaseOne();

        gamePane.requestFocus();

        stage.setTitle("Game Menu");
        stage.setScene(scene);
        stage.show();
    }

//    private Label generateTimer() {
//        Timeline timeline = new Timeline();
//        Label timerLabel = new Label();
//        final int[] timeSeconds = {120};
//
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.getKeyFrames().add(
//                new KeyFrame(Duration.millis(1000), event -> {
//                    timeSeconds[0]--;
//                    int minutes = timeSeconds[0] / 60;
//                    int seconds = timeSeconds[0] % 60;
//                    String timeString = String.format("%02d:%02d", minutes, seconds);
//                    timerLabel.setText(timeString);
//                    if(timeSeconds[0] <= 0) {
//                        timeline.stop();
//                        try {
//                            GameController.showGameResult(GameController.getGameScore(), true);
//                        } catch (Exception e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                })
//        );
//        timeline.playFromStart();
//        return timerLabel;
//    }

    private void initializeGame(Pane gamePane, CenterBall outerBall, ProgressBar progressBar, Button button, Text score, Scene scene) {
        gameController.setNumberOfBallsLeft(CurrentGame.getNumberOfBalls());
        gameController.resetScore();
        CurrentGame.resetBalls();


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
//                        System.out.println(progressBar.getProgress());
                        if(progressBar.getProgress() >= 1)
                            gameController.freeze(progressBar, scene);
                    }
//                    gameController.checkForIncreaseRadius();
                    if (gameController.getNumberOfBallsLeft() <= CurrentGame.getNumberOfBalls()/4) {
                        if (keyName.equals(CurrentGame.getRightKey()))
                            gameController.moveRight(ball);
                        else if (keyName.equals(CurrentGame.getLeftKey()))
                            gameController.moveLeft(ball);
                    }
                }

            });
    }

//    public Text getPhaseNumber(CenterBall centerBall) {
//        Text phaseNumber = new Text(centerBall.getCenterX() - 18, centerBall.getCenterY() + 18,
//                "" + CurrentGame.getPhase().getPhase());
//        phaseNumber.setFill(Color.WHITE);
//        phaseNumber.setFont(new Font(65));
//        return phaseNumber;
//    }

    public Text getProgressBarText() {
        Text text = new Text("Freeze");
        text.setFill(Color.LIGHTBLUE);
        Font font = Font.font("serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 14);
        text.setFont(font);
        return text;
    }

}
