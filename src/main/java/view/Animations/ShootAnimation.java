package view.Animations;

import controller.GameController;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;

import java.awt.*;

public class ShootAnimation extends Transition {
    private Pane pane;
    private Ball ball;
    private CenterBall outerBall;
    private Text number;
    private Text staticNumber;
    public ShootAnimation(Pane pane, Ball ball, CenterBall outerBall ,Text staticNumber) {
        this.pane = pane;
        this.ball = ball;
        this.outerBall = outerBall;
//        this.number = number;
        this.staticNumber = staticNumber;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        double y = ball.getCenterY() - 10;
        double textY = staticNumber.getY() - 10;

        //TODO: get ball from database?
        if(outerBall.getBoundsInParent().intersects(ball.getLayoutBounds())) {
            this.stop();

            Line line = new Line(outerBall.getCenterX(), outerBall.getCenterY(), ball.getCenterX(), ball.getCenterY());
            pane.getChildren().addAll(line);
            GameController.rotate(pane, ball, outerBall, line, staticNumber);
//            pane.getChildren().remove(staticNumber);
        }

        if(y <= 20) {
            pane.getChildren().remove(ball);
            this.stop();
        }
        ball.setCenterY(y);
        staticNumber.setY(textY);

    }

}
