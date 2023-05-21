package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CenterBall extends Circle {

    public CenterBall() {
        super(250, 250, 80, Color.BLACK);
    }

    public CenterBall(double radius) {
        super(250, 250, radius, Color.TRANSPARENT);

    }
}
