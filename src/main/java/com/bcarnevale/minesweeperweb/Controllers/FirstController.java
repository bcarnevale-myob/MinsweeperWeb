package com.bcarnevale.minesweeperweb.Controllers;

import Field.Size;
import Game.*;
import MinePlacer.IRandom;
import MinePlacer.RealRandomNumberGenerator;
import com.bcarnevale.minesweeperweb.Game.HTMLReader;
import com.bcarnevale.minesweeperweb.Game.HTMLWriter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    private final IRandom random;
    private Game game;
    private HTMLWriter webWriter;
    private HTMLReader webReader;

    public FirstController(IRandom random) {
        this.random = random;
    }

    public FirstController() {
        this.random = new RealRandomNumberGenerator();
    }

    @RequestMapping("/setup/{height}/{width}")
    public String setUpGame(@PathVariable("height") int height, @PathVariable("width") int width) {
        webWriter = new HTMLWriter();
        webReader = new HTMLReader();
        this.game = new Game(webReader, webWriter);
        Size fieldSize = new Size(height, width);
        game.setUpGame(random, fieldSize);
        String bufferContents = webWriter.getBuffer();
        webWriter.flushBuffer();
        return bufferContents;
    }

    @RequestMapping("/move/{x}/{y}")
    public String makeAMove(@PathVariable("x") int x, @PathVariable("y") int y) {
        if(!(game.getGameIsOver())) {
            webReader.setX(x);
            webReader.setY(y);
            game.play();
            String bufferContents = webWriter.getBuffer();
            webWriter.flushBuffer();
            return bufferContents;
        }
        return "GAME IS OVER! Would you like to start a new game?";
    }

}
