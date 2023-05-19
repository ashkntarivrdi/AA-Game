package model;

import enums.Avatar;
import javafx.scene.image.Image;

public class User {
    private String name;
    private String password;
    private int score;
    private Avatar avatar;
    private String path;
    private double time;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.score = 0;
        this.avatar = Avatar.getRandomAvatar();
        this.path = "";
        this.time = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
