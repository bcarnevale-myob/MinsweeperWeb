package com.bcarnevale.minesweeperweb.Game;

import Game.Writer;


public class JsonWriter implements Writer {

    private String[] buffer = new String[]{};

    @Override
    public void write(String s) {
        buffer = s.split("\n");
    }

    public void writeField(String[] s){
        buffer = s;
    }

    public String[] getBuffer() {
        return this.buffer;
    }
}
