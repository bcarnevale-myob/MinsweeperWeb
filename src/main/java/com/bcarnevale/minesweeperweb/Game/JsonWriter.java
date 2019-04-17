package com.bcarnevale.minesweeperweb.Game;

import Game.Writer;

public class HTMLWriter implements Writer {

    private String buffer = "";

    public void WebWriter() {
        this.buffer = "";
    }

    @Override
    public void write(String s) {
        buffer += (s.replace("\n", "<br>") + "<br>");
    }


    public String getBuffer() {
        return this.buffer;
    }

    public void flushBuffer() {
        this.buffer = "";
    }
}
