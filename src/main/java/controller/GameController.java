package controller;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;
import view.Animations.RotateAnimation;
import view.Animations.ShootAnimation;

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

    public void shoot(Ball ball, Pane gamePane, CenterBall outerBall) {
        if(CurrentGame.getNumberOfBallsInEachPhase() > 0) {
            Ball shootedBall = new Ball(ball.getCenterX(), ball.getCenterY(), ball.getRadius(), Color.BLACK);

            Text numberOfBallsLeft = getTextForNumber(shootedBall);

            gamePane.getChildren().addAll(shootedBall, numberOfBallsLeft);

            ShootAnimation shootingAnimation = new ShootAnimation(gamePane, shootedBall, outerBall, numberOfBallsLeft);
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


    public static Rotate rotate1 = new Rotate(0);

    public static void rotate(Ball ball, Line line, Text number) {
        ball.getTransforms().add(rotateAnimation.getRotationForDelay());
        line.getTransforms().add(rotateAnimation.getRotationForDelay());
        number.getTransforms().add(rotateAnimation.getRotationForDelay());
        ball.getTransforms().add(rotateAnimation.getRotate());
        line.getTransforms().add(rotateAnimation.getRotate());
        number.getTransforms().add(rotateAnimation.getRotate());

        rotateAnimation.play();
    }

    public static void createRotationAnimation(CenterBall outerBall) {
        rotateAnimation = new RotateAnimation(outerBall);
    }
}
