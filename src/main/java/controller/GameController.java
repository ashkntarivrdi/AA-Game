package controller;

import enums.Phase;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    public static RotateAnimation rotateAnimation = new RotateAnimation(new CenterBall(150));
    public static Timeline freezeTimeLine;
    public static ArrayList<RotateAnimation> animations = new ArrayList<>();
    public static ArrayList<Timeline> timelines = new ArrayList<>();
//    private static ArrayList<Ball> defaultBalls = new ArrayList<>();
    private int numberOfBallsLeft;

//    {
//        timelines.add(freezeTimeLine);
//        animations.add(rotateAnimation);
//    }

    public int getNumberOfBallsLeft() {
        return numberOfBallsLeft;
    }

    public  void setNumberOfBallsLeft(int numberOfBallsLeft) {
        this.numberOfBallsLeft = numberOfBallsLeft;
    }

    private void decreaseNumberOfBallsLeft() {
        this.numberOfBallsLeft--;
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

    public void shoot(Ball ball, Pane gamePane, CenterBall outerBall, ProgressBar progressBar) {
        if(numberOfBallsLeft > 0) {
            Ball shootedBall = new Ball(ball.getCenterX(), ball.getCenterY(), ball.getRadius(), Color.BLACK);

            Text ballsNumber = getTextForNumber(shootedBall);

            gamePane.getChildren().addAll(shootedBall, ballsNumber);

            ShootAnimation shootingAnimation = new ShootAnimation(gamePane, shootedBall, outerBall, ballsNumber, progressBar);
            shootingAnimation.play();

            decreaseNumberOfBallsLeft();
            if(numberOfBallsLeft == 0) {
                gamePane.getChildren().remove(ball);
//                CurrentGame.decreaseNumberOfBallsInEachPhase();
            }
        }
    }

    public Text getTextForNumber(Ball shootedBall) {
        Text ballsNumber = new Text("" + numberOfBallsLeft);
        ballsNumber.setFill(Color.WHITE);
        ballsNumber.setX(shootedBall.getCenterX() - 7);
        ballsNumber.setY(shootedBall.getCenterY() + 5);
        return ballsNumber;
    }

    public static void rotate(Ball ball, Line line, Text number, Pane gamePane) throws Exception {
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
            showGameResult(getGameScore());
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

//    public static void createRotationAnimation(CenterBall outerBall) {
//        rotateAnimation = new RotateAnimation(outerBall);
//    }

    public static void addBall(Ball ball) {
        CenterBall.addBallToArray(ball);
    }

//    public static ArrayList<Ball> getBall() {
//        return CenterBall.getBalls();
//    }

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
        freezeTimeLine = new Timeline(new KeyFrame(Duration.millis(CurrentGame.getDifficultyRate().getFreezeTime() * 1000),
                event -> GameController.rotateAnimation.setRotateFrequency(CurrentGame.getDifficultyRate().getSpeedRate())));
        freezeTimeLine.setCycleCount(0);
        freezeTimeLine.play();
        progressBar.setProgress(0);
    }

    public void createDefaultBalls(Pane gamePane, CenterBall outerBall) {
        Ball ball1 = new Ball(outerBall.getCenterX() + 50, outerBall.getCenterY() + 140, 10, Color.BLACK);
        Ball ball2 = new Ball(outerBall.getCenterX() - 50, outerBall.getCenterY() + 140, 10, Color.BLACK);
        Ball ball3 = new Ball(outerBall.getCenterX(), outerBall.getCenterY() - 150, 10, Color.BLACK);
        Ball ball4 = new Ball(outerBall.getCenterX() - 150, outerBall.getCenterY(), 10, Color.BLACK);
        Ball ball5 = new Ball(outerBall.getCenterX() + 150, outerBall.getCenterY(), 10, Color.BLACK);
//        defaultBalls.addAll(List.of(ball1, ball2, ball3, ball4, ball5));

        gamePane.getChildren().addAll(ball1, ball2, ball3, ball4, ball5);
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



}
