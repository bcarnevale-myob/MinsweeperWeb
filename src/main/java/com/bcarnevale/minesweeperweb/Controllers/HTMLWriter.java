package com.bcarnevale.minesweeperweb.Controllers;

import Game.IWriter;

public class WebWriter implements IWriter {

    private String buffer = "";

    public void WebWriter() {
        this.buffer = "";
    }

    @Override
    public void write(String s) {
        buffer += (s +"\n");
    }


    public String getBuffer() {
        return this.buffer;
    }

    public void flushBuffer() {
        this.buffer = "";
    }
}
