package controller;

import enums.Phase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;
import view.Animations.RotateAnimation;
import view.Animations.ShootAnimation;
import view.Menus.GameResult;
import view.Menus.LoginMenu;

import java.util.ArrayList;

public class GameController {
    public static RotateAnimation rotateAnimation;

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

    public void shoot(Ball ball, Pane gamePane, CenterBall outerBall, ProgressBar progressBar) {
        if(CurrentGame.getNumberOfBallsInEachPhase() > 0) {
            Ball shootedBall = new Ball(ball.getCenterX(), ball.getCenterY(), ball.getRadius(), Color.BLACK);

            Text numberOfBallsLeft = getTextForNumber(shootedBall);

            gamePane.getChildren().addAll(shootedBall, numberOfBallsLeft);

            ShootAnimation shootingAnimation = new ShootAnimation(gamePane, shootedBall, outerBall, numberOfBallsLeft, progressBar);
            shootingAnimation.play();

            CurrentGame.decreaseNumberOfBallsInEachPhase();
            if(CurrentGame.getNumberOfBallsInEachPhase() == 0)
                gamePane.getChildren().remove(ball);
        }
    }

    public Text getTextForNumber(Ball shootedBall) {
        Text numberOfBallsLeft = new Text("" + CurrentGame.getNumberOfBallsInEachPhase());
        numberOfBallsLeft.setFill(Color.WHITE);
        numberOfBallsLeft.setX(shootedBall.getCenterX() - 7);
        numberOfBallsLeft.setY(shootedBall.getCenterY() + 5);
        return numberOfBallsLeft;
    }

    public static void rotate(Ball ball, Line line, Text number) throws Exception {
        ball.getTransforms().add(rotateAnimation.getRotationForDelay());
        line.getTransforms().add(rotateAnimation.getRotationForDelay());
        number.getTransforms().add(rotateAnimation.getRotationForDelay());

        ball.getTransforms().add(rotateAnimation.getRotate());
        line.getTransforms().add(rotateAnimation.getRotate());
        number.getTransforms().add(rotateAnimation.getRotate());

        rotateAnimation.play();

        for (int i = 0; i < GameController.getBall().size() - 1; i++) {
            if (GameController.getBall().get(i).getBoundsInParent().intersects(ball.getLayoutBounds())) {
                rotateAnimation.stop();
                showGameResult(getGameScore());
            }
        }

    }

    private static int getGameScore() {
        //TODO
        return 0;
    }

    private static void showGameResult(int score) throws Exception{
        //TODO: score and username must added
        CurrentGame.setPhase(Phase.ONE);
        new GameResult().start(LoginMenu.stage);
    }

    public static void createRotationAnimation(CenterBall outerBall) {
        rotateAnimation = new RotateAnimation(outerBall);
    }

    public static void addBall(Ball ball) {
        CenterBall.addBallToArray(ball);
    }

    public static ArrayList<Ball> getBall() {
        return CenterBall.getBalls();
    }

    public static void addDegree(double degree) {
        CenterBall.addBallDegreeToArray(degree);
    }

    public static double getBallsAngle(Ball ball, CenterBall centerBall) {
        double deltaX = ball.getCenterX() - centerBall.getCenterX();
        double deltaY = ball.getCenterY() - centerBall.getCenterY();

        double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 /Math.PI;
        if (angleInDegrees < 0) angleInDegrees += 360;
        return angleInDegrees;
    }

    public void freeze(ProgressBar progressBar) {
        GameController.rotateAnimation.setRotateFrequency(0.5);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(CurrentGame.getDifficultyRate().getFreezeTime() * 1000),
                event -> GameController.rotateAnimation.setRotateFrequency(CurrentGame.getDifficultyRate().getSpeedRate())));
        timeline.setCycleCount(0);
        timeline.play();
        progressBar.setProgress(0);
    }

}
