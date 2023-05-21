package controller;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;
import view.Animations.RotateAnimation;
import view.Animations.ShootAnimation;

public class GameController {
//    public static RotateAnimation rotateAnimation;

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

            Text numberOfBallsLeft = new Text("" + CurrentGame.getNumberOfBallsInEachPhase());
            numberOfBallsLeft.setFill(Color.WHITE);
            numberOfBallsLeft.setX(shootedBall.getCenterX() - 5);
            numberOfBallsLeft.setY(shootedBall.getCenterY() + 5);

            gamePane.getChildren().addAll(shootedBall, numberOfBallsLeft);

            ShootAnimation shootingAnimation = new ShootAnimation(gamePane, shootedBall, outerBall, numberOfBallsLeft);
            shootingAnimation.play();
            CurrentGame.decreaseNumberOfBallsInEachPhase();
            if(CurrentGame.getNumberOfBallsInEachPhase() == 0)
                gamePane.getChildren().remove(ball);
        }
    }


    public static void rotate(Pane gamePane, Ball ball, CenterBall outerBall, Line line, Text number) {
        RotateAnimation rotateAnimation = new RotateAnimation(gamePane, ball, outerBall, line, number);
        rotateAnimation.play();
    }

//    public static void createRotationAnimation() {
//        rotateAnimation = new RotateAnimation()
//    }
}
