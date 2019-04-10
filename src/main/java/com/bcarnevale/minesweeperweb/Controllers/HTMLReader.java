package com.bcarnevale.minesweeperweb.Controllers;

import Game.IReader;

public class HTMLReader implements IReader {

    private int x;
    private int y;

    @Override
    public String prompt(String s) {
        return x + "," + y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
