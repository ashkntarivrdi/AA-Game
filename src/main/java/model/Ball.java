package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    public Ball() {
        //TODO:super constructor
//        super(300, 300, 25);
        this.setFill(new ImagePattern(
                new Image(Ball.class.getResource("/images/circle.png").toExternalForm())));
    }
}
