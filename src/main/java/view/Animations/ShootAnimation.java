package view.Animations;

import controller.GameController;
import javafx.animation.*;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;
import view.Menus.GameMenu;
import view.Menus.LoginMenu;

public class ShootAnimation extends Transition {
    private Pane pane;
    private Ball ball;
    private CenterBall outerBall;
    private Text numberOfBallsLeft;
    private ProgressBar progressBar;

    static {
        GameController.createRotationAnimation(new CenterBall(150));
    }
    public ShootAnimation(Pane pane, Ball ball, CenterBall outerBall , Text numberOfBallsLeft, ProgressBar progressBar) {
        this.pane = pane;
        this.ball = ball;
        this.outerBall = outerBall;
        this.numberOfBallsLeft = numberOfBallsLeft;
        this.progressBar = progressBar;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.progressBar.setProgress(this.progressBar.getProgress() + 0.11);
    }

    @Override
    protected void interpolate(double frac) {
        double y = ball.getCenterY() - 10;
        double textY = numberOfBallsLeft.getY() - 10;

        //TODO: get ball from database?
        if(outerBall.getBoundsInParent().intersects(ball.getLayoutBounds())) {
            this.stop();
            GameController.addBall(ball);

            Line line = new Line(outerBall.getCenterX(), outerBall.getCenterY(), ball.getCenterX(), ball.getCenterY());
            pane.getChildren().add(0, line);
            try {
                GameController.rotate(ball, line, numberOfBallsLeft);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if(y <= 20) {
            pane.getChildren().remove(ball);
            this.stop();
        }

        ball.setCenterY(y);
        numberOfBallsLeft.setY(textY);
    }

}
