package view.Animations;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;

public class RotateAnimation extends Transition {
//    private Pane pane;
//    private Ball ball;
//    private CenterBall outerBall;
//    private Line line;
//    private Text number;
    private final Rotate rotate;

    public RotateAnimation(CenterBall outerBall) {
        rotate = new Rotate(0, outerBall.getCenterX(), outerBall.getCenterY());
        setInterpolator(Interpolator.LINEAR);
        setCycleDuration(Duration.millis(20000));
        setCycleCount(-1);
    }
    @Override
    protected void interpolate(double frac) {
        rotate.setAngle(rotate.getAngle() + CurrentGame.getDifficultyRate().getSpeedRate());
    }

    public Rotate getRotate() {
        return this.rotate;
    }

    public Rotate getRotationForDelay() {
        return new Rotate(-rotate.getAngle(), 250, 250);
    }
}
