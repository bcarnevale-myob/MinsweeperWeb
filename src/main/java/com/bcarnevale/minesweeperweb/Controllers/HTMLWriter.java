package com.bcarnevale.minesweeperweb.Controllers;

import Game.IWriter;

public class HTMLWriter implements IWriter {

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
