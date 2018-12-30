package com.example.safam.myapplication;

/**
 * Created by safam on 26/03/2018.
 */

public class User {
    private String name;
    private String password;
    private String email;
    private int score;

    public User() {}

    public User(String name, String password, String email, int score) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
