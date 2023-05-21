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

            Text staticNumber = new Text("" + CurrentGame.getNumberOfBallsInEachPhase());
            staticNumber.setFill(Color.WHITE);
            staticNumber.setX(shootedBall.getCenterX() - 5);
            staticNumber.setY(shootedBall.getCenterY() + 5);


            Text number = new Text();
            number.setText("" + CurrentGame.getNumberOfBallsInEachPhase());
            number.setFill(Color.WHITE);
            number.setX(ball.getCenterX() - 5);
            number.setY(ball.getCenterY() - 145);

            gamePane.getChildren().addAll(shootedBall, number, staticNumber);

            ShootAnimation shootingAnimation = new ShootAnimation(gamePane, shootedBall, outerBall, number, staticNumber);
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
}
