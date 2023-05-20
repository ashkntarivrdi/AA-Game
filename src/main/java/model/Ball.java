package model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    int currentX;
    int curretY;

    public Ball(int centerX, int centerY, int radius, Color color) {
        super(centerX, centerY, radius);
        this.setFill(color);
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurretY() {
        return curretY;
    }

    public void setCurretY(int curretY) {
        this.curretY = curretY;
    }
}
