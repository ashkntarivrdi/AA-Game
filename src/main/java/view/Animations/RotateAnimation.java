package view.Animations;

import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;

public class RotateAnimation extends Transition {
    private final Rotate rotate;
    private double rotateFrequency = CurrentGame.getDifficultyRate().getSpeedRate();
    private Timeline timeline = new Timeline();

    public RotateAnimation(CenterBall outerBall) {
        rotate = new Rotate(0, outerBall.getCenterX(), outerBall.getCenterY());
        setInterpolator(Interpolator.LINEAR);
        setCycleDuration(Duration.millis(1000));
        setCycleCount(-1);
    }
    @Override
    protected void interpolate(double frac) {
        rotate.setAngle(rotate.getAngle() + rotateFrequency);
    }

    public void setRotateFrequency(double rotateFrequency) {
        this.rotateFrequency = rotateFrequency;
    }

    public double getRotateFrequency() {
        return rotateFrequency;
    }

    public Rotate getRotate() {
        return this.rotate;
    }

    public Rotate getRotationForDelay() {
        return new Rotate(-rotate.getAngle(), 250, 250);
    }
}
