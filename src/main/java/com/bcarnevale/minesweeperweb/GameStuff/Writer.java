package com.bcarnevale.minesweeperweb.GameStuff;

import Game.IWriter;

public class Writer implements IWriter {

    StringBuilder sb = new StringBuilder();

    @Override
    public void write(String s) {
        sb.append(s).append("\n");
    }

    public String getString() {
        try {
            return sb.toString();

        } finally {
            sb = new StringBuilder();
        }
    }
}
