package com.bcarnevale.minesweeperweb.Game;

import Game.Reader;

public class HTMLReader implements Reader {

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
