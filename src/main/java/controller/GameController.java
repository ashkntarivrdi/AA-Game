package controller;

import controller.Utils.UserUtils;
import enums.Phase;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;
import model.Database;
import view.Animations.RotateAnimation;
import view.Animations.ShootAnimation;
import view.Menus.GameMenu;
import view.Menus.LoginMenu;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    public static RotateAnimation rotateAnimation = new RotateAnimation(new CenterBall(150));
    public static Timeline freezeTimeLine;
    public static Timeline increaseRadiusTimeLine;
    public static Timeline reverseRotateTimeLine;
    public static Timeline timerTimeLine;
//    public static Timeline invisibleTimeLine;

    public static Button button = new Button();
    public static Pane pane;
    public static Timer visibilityTimer;
//    public static Timer reverseRotateTimer;
    public static Boolean visibility = true;
    public static Boolean isPauseMenu = false;
    public static Boolean isGameOver = false;
//    public static ArrayList<RotateAnimation> animations = new ArrayList<>();
//    public static ArrayList<Timeline> timelines = new ArrayList<>();
//    private static ArrayList<Ball> defaultBalls = new ArrayList<>();
    private static int numberOfBallsLeft;
    private static int score;
    private static Scene scene;

//    {
//        timelines.add(freezeTimeLine);
//        animations.add(rotateAnimation);
//    }

    public int getNumberOfBallsLeft() {
        return numberOfBallsLeft;
    }

    public void setNumberOfBallsLeft(int numberOfBallsLeft) {
        GameController.numberOfBallsLeft = numberOfBallsLeft;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }

    public void setScore(int score) {
        GameController.score = score;
    }

    private void decreaseNumberOfBallsLeft() {
        numberOfBallsLeft--;
    }

    public int getCurrentGamePhase() {
        return CurrentGame.getPhase().getPhase();
    }

    public String getCurrentGameRightKey() {
        return CurrentGame.getRightKey();
    }

    public String getCurrentGameLeftKey() {
        return CurrentGame.getLeftKey();
    }

    public String getCurrentGameShootKey() {
        return CurrentGame.getShootKey();
    }

    public String getCurrentGameFreezeKey() {
        return CurrentGame.getFreezeKey();
    }

    public void shoot(Ball ball, Pane gamePane, CenterBall outerBall, ProgressBar progressBar, Button button, Text score) throws Exception {
        GameController.button = button;
        GameController.pane = gamePane;

        if(numberOfBallsLeft > 0) {
            Ball shootedBall = new Ball(ball.getCenterX(), ball.getCenterY(), ball.getRadius(), Color.BLACK);

            Text ballsNumber = getTextForNumber(shootedBall);
            Line line = new Line();
            line.setStartX(outerBall.getCenterX());
            line.setStartY(outerBall.getCenterY());

            gamePane.getChildren().addAll(shootedBall, ballsNumber);

            ShootAnimation shootingAnimation = new ShootAnimation(gamePane, shootedBall, outerBall, ballsNumber, progressBar, line, score);
            shootingAnimation.play();

            if (numberOfBallsLeft <= (CurrentGame.getNumberOfBalls() * 3)/4) {
                CurrentGame.setPhase(Phase.TW0);
                increaseRadius();
                reverseRotate();
                if (numberOfBallsLeft <= (CurrentGame.getNumberOfBalls()/2)) {
                    CurrentGame.setPhase(Phase.THREE);
                    if (visibility) {
                        invisibleEffect();
                        visibility = false;
                    }
                    if (numberOfBallsLeft <= CurrentGame.getNumberOfBalls()/4) {
                        CurrentGame.setPhase(Phase.FOUR);
                    }
                }
            }

            decreaseNumberOfBallsLeft();
            if(numberOfBallsLeft == 0) {
                gamePane.getChildren().remove(ball);
//                showGameResult(getGameScore());
//                CurrentGame.decreaseNumberOfBallsInEachPhase();
            }
        }
//        if (numberOfBallsLeft == 0)
//            showGameResult(getGameScore());
    }

    public Text getTextForNumber(Ball shootedBall) {
        Text ballsNumber = new Text("" + numberOfBallsLeft);
        ballsNumber.setFill(Color.WHITE);
        ballsNumber.setX(shootedBall.getCenterX() - 7);
        ballsNumber.setY(shootedBall.getCenterY() + 5);
        return ballsNumber;
    }

    public static void rotate(Ball ball, Line line, Text number) throws Exception {
        ball.getTransforms().add(rotateAnimation.getRotationForDelay());
        line.getTransforms().add(rotateAnimation.getRotationForDelay());
        number.getTransforms().add(rotateAnimation.getRotationForDelay());

        ball.getTransforms().add(rotateAnimation.getRotate());
        line.getTransforms().add(rotateAnimation.getRotate());
        number.getTransforms().add(rotateAnimation.getRotate());

        rotateAnimation.play();

        boolean isIntersect = false;
        for (int i = 0; i < CenterBall.getBalls().size() - 1; i++) {
            if (CenterBall.getBalls().get(i).getBoundsInParent().intersects(ball.getLayoutBounds())) {
                isIntersect = true;
                break;
            }
        }
        if(isIntersect)
            showGameResult(getGameScore(), isIntersect);

        if(numberOfBallsLeft == 0)
            showGameResult(getGameScore(), isIntersect);
    }

    public static int getGameScore() {
        return score;
    }

    public static void increaseRadius() throws Exception {
        for (Ball ball : CenterBall.getBalls()) {
            ball.setRadius(12);
        }

        //TODO: intersect between balls on the circle has bug
//        boolean isIntersect = false;
//        outerLoop:
//        for (Ball ball : CenterBall.getBalls()) {
//            for (Ball secondBall : CenterBall.getBalls()) {
//                if (!ball.equals(secondBall)) {
//                    if (ball.getBoundsInParent().intersects(secondBall.getLayoutBounds())) {
//                       isIntersect = true;
//                       break outerLoop;
//                    }
//                }
//            }
//        }
//        if (isIntersect)
//            showGameResult(getGameScore());

        increaseRadiusTimeLine = new Timeline(new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Ball ball : CenterBall.getBalls()) {
                    ball.setRadius(10);
                }
            }
        }));
        increaseRadiusTimeLine.setCycleCount(1);
        increaseRadiusTimeLine.play();
    }

    public static void reverseRotate() throws Exception{
        rotateAnimation.setRotateFrequency(-CurrentGame.getDifficultyRate().getSpeedRate());
        reverseRotateTimeLine = new Timeline(new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rotateAnimation.setRotateFrequency(CurrentGame.getDifficultyRate().getSpeedRate());
            }
        }));
        reverseRotateTimeLine.setCycleCount(0);
        reverseRotateTimeLine.play();
    }

    public static void invisibleEffect() {
        TimerTask timerTask = new TimerTask() {
            boolean isVisible = true;
            @Override
            public void run() {
                for (Ball ball : CenterBall.getBalls()) {
                    ball.setVisible(!isVisible);
                }
                for (Line line : CenterBall.getLines()) {
                    line.setVisible(!isVisible);
                }
                for (Text text : CenterBall.getTexts()) {
                    text.setVisible(!isVisible);
                }
                isVisible = !isVisible; // toggle visibility
            }
        };
        visibilityTimer = new Timer();
        visibilityTimer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public static void makeEveryThingVisible() {
        for (Ball ball : CenterBall.getBalls()) {
            ball.setVisible(true);
        }
        for (Line line : CenterBall.getLines()) {
            line.setVisible(true);
        }
        for (Text text : CenterBall.getTexts()) {
            text.setVisible(true);
        }
    }

    public static void showGameResult(int score, boolean isIntersect) throws Exception{
        //TODO: score and username must added
//        visibility = true;
//        if (visibilityTimer != null)  {
//            visibilityTimer.cancel();
//            makeEveryThingVisible();
//        }
//        if (reverseRotateTimer != null) reverseRotateTimer.cancel();
//
//        rotateAnimation.pause();
//        button.requestFocus();
//        CurrentGame.setPhase(Phase.ONE);
        resetEverything();
        isGameOver = true;
        if(!UserUtils.isGuestPlayer()) {
            Database.getUserByUsername(Database.getCurrentUser().getName()).setScore(score);
            Database.getCurrentUser().setScore(score);
            Database.saveUsers();
        }



        Text text = getResultText(isIntersect);

        if (isIntersect) pane.setStyle("-fx-background-color: #ff0000");
        else pane.setStyle("-fx-background-color: #32cd32");

        pane.getChildren().add(text);
//        pane.setStyle("-fx-background-color: #ff0000");
        //        new GameResult().start(LoginMenu.stage);
    }

    public static Text getResultText(boolean isIntersect) {
        Text text = new Text();

        text.setFill(Color.WHITE);
        Font font = Font.font("serif", FontWeight.BOLD, FontPosture.REGULAR, 40);
        text.setFont(font);
        text.setTranslateX(300);
        text.setTranslateY(400);
        if (isIntersect) text.setText("You Lost\nScore : " + score);
        else text.setText("You Won\nScore : " + score);

        return text;
    }

    public static void resetEverything() {
        visibility = true;
        isPauseMenu = false;
        if (visibilityTimer != null)  {
            visibilityTimer.cancel();
            makeEveryThingVisible();
        }
        rotateAnimation.pause();
        button.requestFocus();
        timerTimeLine.pause();
        GameMenu.mediaPlayer.pause();
        CurrentGame.setPhase(Phase.ONE);
    }

//    public static void createRotationAnimation(CenterBall outerBall) {
//        rotateAnimation = new RotateAnimation(outerBall);
//    }

//    public static void addBall(Ball ball) {
//        CenterBall.addBallToArray(ball);
//    }

//    public static ArrayList<Ball> getBall() {
//        return CenterBall.getBalls();
//    }

//    public static void addDegree(double degree) {
//        CenterBall.addBallDegreeToArray(degree);
//    }

//    public static double getBallsAngle(Ball ball, CenterBall centerBall) {
//        double deltaX = ball.getCenterX() - centerBall.getCenterX();
//        double deltaY = ball.getCenterY() - centerBall.getCenterY();
//
//        double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 /Math.PI;
//        if (angleInDegrees < 0) angleInDegrees += 360;
//        return angleInDegrees;
//    }

    public void freeze(ProgressBar progressBar, Scene scene) {
        rotateAnimation.setRotateFrequency(0.5);
        freezeTimeLine = new Timeline(new KeyFrame(Duration.millis(CurrentGame.getDifficultyRate().getFreezeTime() * 1000),
                event -> rotateAnimation.setRotateFrequency(CurrentGame.getDifficultyRate().getSpeedRate())));
        freezeTimeLine.setCycleCount(0);
        freezeTimeLine.play();

//        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
//        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());
//        Image image = new Image(getClass().getResource("/images/freeze.png").toExternalForm());
//        ImageView imageView = new ImageView(image);
//        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        Background background = new Background(backgroundImage);
//        pane.setBackground(background);
//        scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/Freeze.css").toExternalForm());

        scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/freeze.css").toExternalForm());
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(CurrentGame.getDifficultyRate().getFreezeTime() * 1000),
                event -> scene.getStylesheets().remove(LoginMenu.class.getResource("/CSS/freeze.css").toExternalForm())));

        timeline.setCycleCount(0);
        timeline.play();

        progressBar.setProgress(0);
    }

    public static void rotateBackToNormal() {
        rotateAnimation.setRotateFrequency(CurrentGame.getDifficultyRate().getSpeedRate());
    }

    public void createDefaultBalls(Pane gamePane, CenterBall outerBall) {
        Ball ball1 = new Ball(outerBall.getCenterX() + 50, outerBall.getCenterY() + 140, 10, Color.BLACK);
        Ball ball2 = new Ball(outerBall.getCenterX() - 50, outerBall.getCenterY() + 140, 10, Color.BLACK);
        Ball ball3 = new Ball(outerBall.getCenterX(), outerBall.getCenterY() - 150, 10, Color.BLACK);
        Ball ball4 = new Ball(outerBall.getCenterX() - 150, outerBall.getCenterY(), 10, Color.BLACK);
        Ball ball5 = new Ball(outerBall.getCenterX() + 150, outerBall.getCenterY(), 10, Color.BLACK);
        Ball ball6 = new Ball(outerBall.getCenterX() + 50, outerBall.getCenterY() - 140, 10, Color.BLACK);
        Ball ball7 = new Ball(outerBall.getCenterX() - 50, outerBall.getCenterY() - 140, 10, Color.BLACK);
//        defaultBalls.addAll(List.of(ball1, ball2, ball3, ball4, ball5));
        gamePane.getChildren().addAll(ball1, ball2, ball3, ball4, ball5);

        if (CurrentGame.getDefaultBallsCount() > 5) {
            gamePane.getChildren().add(ball6);
            ball6.getTransforms().add(rotateAnimation.getRotate());
            CenterBall.addBallToArray(ball6);
            if (CurrentGame.getDefaultBallsCount() > 6) {
                gamePane.getChildren().add(ball7);
                ball7.getTransforms().add(rotateAnimation.getRotate());
                CenterBall.addBallToArray(ball7);
            }
        } else {
            gamePane.getChildren().remove(ball6);
            gamePane.getChildren().remove(ball7);
        }

        createDefaultLines(gamePane, outerBall);

        ball1.getTransforms().add(rotateAnimation.getRotate());
        ball2.getTransforms().add(rotateAnimation.getRotate());
        ball3.getTransforms().add(rotateAnimation.getRotate());
        ball4.getTransforms().add(rotateAnimation.getRotate());
        ball5.getTransforms().add(rotateAnimation.getRotate());

        CenterBall.addBallToArray(ball1);
        CenterBall.addBallToArray(ball2);
        CenterBall.addBallToArray(ball3);
        CenterBall.addBallToArray(ball4);
        CenterBall.addBallToArray(ball5);

    }

    public void createDefaultLines(Pane gamePane, CenterBall outerBall) {
        Line line1 = new Line(outerBall.getCenterX(), outerBall.getCenterY(), outerBall.getCenterX() + 50, outerBall.getCenterY() + 140);
        Line line2 = new Line(outerBall.getCenterX(), outerBall.getCenterY(), outerBall.getCenterX() - 50, outerBall.getCenterY() + 140);
        Line line3 = new Line(outerBall.getCenterX(), outerBall.getCenterY(), outerBall.getCenterX(), outerBall.getCenterY() - 150);
        Line line4 = new Line(outerBall.getCenterX(), outerBall.getCenterY(), outerBall.getCenterX() - 150, outerBall.getCenterY());
        Line line5 = new Line(outerBall.getCenterX(), outerBall.getCenterY(), outerBall.getCenterX() + 150, outerBall.getCenterY());
        Line line6 = new Line(outerBall.getCenterX(), outerBall.getCenterY(), outerBall.getCenterX() + 50, outerBall.getCenterY() - 140);
        Line line7 = new Line(outerBall.getCenterX(), outerBall.getCenterY(), outerBall.getCenterX() - 50, outerBall.getCenterY() - 140);


        gamePane.getChildren().add(0, line1);
        gamePane.getChildren().add(0, line2);
        gamePane.getChildren().add(0, line3);
        gamePane.getChildren().add(0, line4);
        gamePane.getChildren().add(0, line5);

        if (CurrentGame.getDefaultBallsCount() > 5) {
            gamePane.getChildren().add(line6);
            line6.getTransforms().add(rotateAnimation.getRotate());
            CenterBall.addLineToArray(line6);
            if (CurrentGame.getDefaultBallsCount() > 6) {
                gamePane.getChildren().add(line7);
                line7.getTransforms().add(rotateAnimation.getRotate());
                CenterBall.addLineToArray(line7);
            }
        } else {
            gamePane.getChildren().remove(line6);
            gamePane.getChildren().remove(line7);
        }

        line1.getTransforms().add(rotateAnimation.getRotate());
        line2.getTransforms().add(rotateAnimation.getRotate());
        line3.getTransforms().add(rotateAnimation.getRotate());
        line4.getTransforms().add(rotateAnimation.getRotate());
        line5.getTransforms().add(rotateAnimation.getRotate());

        CenterBall.addLineToArray(line1);
        CenterBall.addLineToArray(line2);
        CenterBall.addLineToArray(line3);
        CenterBall.addLineToArray(line4);
        CenterBall.addLineToArray(line5);
    }

    public void moveLeft(Ball ball) {
        if (ball.getCenterX() > 150)
            ball.setCenterX(ball.getCenterX() - 15);
    }

    public void moveRight(Ball ball) {
        if (ball.getCenterX() < 350)
            ball.setCenterX(ball.getCenterX() + 15);
    }

//    public void checkForIncreaseRadius() {
//        if (numberOfBallsLeft <= (CurrentGame.getNumberOfBalls() * 3)/4)
//            increaseRadius();
//    }

//    public static void clearDefaultBalls() {
//        defaultBalls.clear();
//    }

//    public static void pauseAnimations() {
//        for (RotateAnimation animation : animations) {
//            animation.stop();
//        }
//    }
//
//    public static void pauseTimeLines() {
//        for (Timeline timeline : timelines) {
//            timeline.stop();
//        }
//    }

    public Label generateTimer() {
        timerTimeLine = new Timeline();
        Label timerLabel = new Label();
        final int[] timeSeconds = {120};

        timerTimeLine.setCycleCount(Timeline.INDEFINITE);
        timerTimeLine.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000), event -> {
                    timeSeconds[0]--;
                    int minutes = timeSeconds[0] / 60;
                    int seconds = timeSeconds[0] % 60;
                    String timeString = String.format("%02d:%02d", minutes, seconds);
                    timerLabel.setText(timeString);
                    if(timeSeconds[0] <= 0) {
                        timerTimeLine.stop();
                        try {
                            GameController.showGameResult(GameController.getGameScore(), true);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
        );
        timerTimeLine.playFromStart();
        return timerLabel;
    }


    public static void enterPauseMenu(Pane gamePane, Pane pausePane) {
        if (isPauseMenu && !isGameOver) {
            timerTimeLine.play();
            if (freezeTimeLine != null) freezeTimeLine.play();
            if (reverseRotateTimeLine != null) reverseRotateTimeLine.play();
            if (increaseRadiusTimeLine != null) increaseRadiusTimeLine.play();
            visibilityTimer.cancel();
            rotateAnimation.play();
            gamePane.getChildren().remove(pausePane);
            gamePane.requestFocus();
            isPauseMenu = false;
        }
        else if (!isGameOver){
            timerTimeLine.pause();
            if (freezeTimeLine != null) freezeTimeLine.pause();
            if (reverseRotateTimeLine != null) reverseRotateTimeLine.pause();
            if (increaseRadiusTimeLine != null) increaseRadiusTimeLine.pause();
//            visibilityTimer.cancel();
            rotateAnimation.pause();
            gamePane.getChildren().add(pausePane);
            button.requestFocus();
            isPauseMenu = true;
        }

    }
}
