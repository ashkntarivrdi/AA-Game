package model;

import enums.Avatar;

public class User {
    private String name;
    private String password;
    private int score;
    private Avatar avatar;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.score = 0;
        this.avatar = Avatar.getRandomAvatar();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
