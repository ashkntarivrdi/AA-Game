package model;

import enums.Avatar;

public class User {
    private String name;
    private String password;
    private int easyScore;
    private int mediumScore;
    private int hardScore;
    private Avatar avatar;
    private String path;
    private double time;
    private CurrentGame currentGame;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.easyScore = 0;
        this.mediumScore = 0;
        this.hardScore = 0;
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

    public double getTime() {
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

    public int getEasyScore() {
        return easyScore;
    }

    public void setEasyScore(int easyScore) {
        this.easyScore = easyScore;
    }

    public int getMediumScore() {
        return mediumScore;
    }

    public void setMediumScore(int mediumScore) {
        this.mediumScore = mediumScore;
    }

    public int getHardScore() {
        return hardScore;
    }

    public void setHardScore(int hardScore) {
        this.hardScore = hardScore;
    }
}
