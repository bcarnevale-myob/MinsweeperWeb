package com.bcarnevale.minesweeperweb.Controllers;

import Game.IReader;

public class WebReader implements IReader {

    private int counter = -1;
    private int x;
    private int y;

    @Override
    public String prompt(String s) {
//        counter++;
//        if(counter == 0) {
//            return "5,6";
//        }
        return x + "," + y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
