package view.Animations;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;

public class RotateAnimation extends Transition {
    private Pane pane;
    private Ball ball;
    private CenterBall outerBall;
    private Line line;
    private Text number;
    private Rotate rotate;

    public RotateAnimation(Pane pane, Ball ball, CenterBall outerBall, Line line, Text number) {
        this.pane = pane;
        this.ball = ball;
        this.line = line;
        this.number = number;
        this.outerBall = outerBall;
        rotate = new Rotate(0, outerBall.getCenterX(), outerBall.getCenterY());
        setInterpolator(Interpolator.LINEAR);
        setCycleDuration(Duration.millis(20000));
        setCycleCount(-1);

        number.getTransforms().add(rotate);
        line.getTransforms().add(rotate);
        ball.getTransforms().add(rotate);
    }
    @Override
    protected void interpolate(double frac) {
        rotate.setAngle(rotate.getAngle() + 2);
    }
}
