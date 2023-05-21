package model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    public Ball() {
        super(250, 550, 10, Color.BLACK);
    }

    public Ball(double centerX, double centerY, double radius, Paint fill) {
        super(centerX, centerY, radius, fill);
    }
}
