package com.example.safam.myapplication;

public class Cour {

    private String name;
    private String path;
    private int chap;

    public Cour() {
    }

    public Cour(String name, String path, int chap) {
        this.name = name;
        this.path = path;
        this.chap = chap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getChap() {
        return chap;
    }

    public void setChap(int chap) {
        this.chap = chap;
    }
}
