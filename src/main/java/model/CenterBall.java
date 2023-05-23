package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.time.temporal.Temporal;
import java.util.ArrayList;

public class CenterBall extends Circle {
    private static ArrayList<Ball> balls;
    private static ArrayList<Line> lines;
    private static ArrayList<Text> texts;
    private static ArrayList<Double> ballsDegree;

    public CenterBall() {
        super(250, 250, 80, Color.BLACK);
    }

    public CenterBall(double radius) {
        super(250, 250, radius, Color.TRANSPARENT);
        balls = new ArrayList<>();
        lines = new ArrayList<>();
        texts = new ArrayList<>();
//        ballsDegree = new ArrayList<>();
    }

    public static ArrayList<Ball> getBalls() {
        return balls;
    }

    public ArrayList<Double> getBallsDegree() {
        return ballsDegree;
    }

    public static ArrayList<Line> getLines() {
        return lines;
    }

    public static ArrayList<Text> getTexts() {
        return texts;
    }

    public static void addBallToArray(Ball ball) {
        balls.add(ball);
    }
    public static void addLineToArray(Line line) {
        lines.add(line);
    }
    public static void addTextToArray(Text text) {
        texts.add(text);
    }


    public static void addBallDegreeToArray(double degree) {
        ballsDegree.add(degree);
    }
}
