package com.bcarnevale.minesweeperweb.Controllers;

import Game.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    private Game game;
    private WebWriter webWriter;
    private WebReader webReader;

    @RequestMapping("/")
    public String setUpGame() {
        webWriter = new WebWriter();
        webReader = new WebReader();
        this.game = new Game(webReader, webWriter);
        game.setUpGame();
        String bufferContents = webWriter.getBuffer();
        webWriter.flushBuffer();
        return bufferContents;
    }

    @RequestMapping("/move/{x}/{y}")
    public String makeAMove(@PathVariable("x") int x, @PathVariable("y") int y) {
        webReader.setX(x);
        webReader.setY(y);
        game.play();
        String bufferContents = webWriter.getBuffer();
        webWriter.flushBuffer();
        return bufferContents;
    }

}
