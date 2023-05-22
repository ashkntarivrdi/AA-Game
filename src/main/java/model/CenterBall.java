package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class CenterBall extends Circle {
    private static ArrayList<Ball> balls;
    private static ArrayList<Double> ballsDegree;

    public CenterBall() {
        super(250, 250, 80, Color.BLACK);
    }

    public CenterBall(double radius) {
        super(250, 250, radius, Color.TRANSPARENT);
        balls = new ArrayList<>();
        ballsDegree = new ArrayList<>();
    }

    public static ArrayList<Ball> getBalls() {
        return balls;
    }

    public ArrayList<Double> getBallsDegree() {
        return ballsDegree;
    }

    public static void addBallToArray(Ball ball) {
        balls.add(ball);
    }

    public static void addBallDegreeToArray(double degree) {
        ballsDegree.add(degree);
    }
}
