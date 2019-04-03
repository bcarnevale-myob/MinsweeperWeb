package com.bcarnevale.minesweeperweb.GameStuff;

import Game.IReader;

public class Reader implements IReader {

    private final Writer writer;
    private int counter = -1;

    public Reader(Writer writer) {
        this.writer = writer;
    }

    @Override
    public String prompt(String s) {
        writer.write(s);
        counter++;
        switch (counter) {
            case 0:
                return "5";
            case 1:
                return "5";
            case 2:
                return "1,1";
            default:
                return "NOTHING!";
        }
    }
}
