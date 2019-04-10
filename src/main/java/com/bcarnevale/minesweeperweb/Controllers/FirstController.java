package com.bcarnevale.minesweeperweb.Controllers;

import Field.*;
import Game.*;
import MinePlacer.RandomMinePlacer;
import MinePlacer.RealRandom;
import com.bcarnevale.minesweeperweb.GameStuff.Writer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    private Game game;
    private WebWriter webWriter;
    private WebReader webReader;

    @RequestMapping("/move/{x}/{y}")
    public String makeAMove(@PathVariable("x") int x, @PathVariable("y") int y) {
        if(game == null) {
            webWriter = new WebWriter();
            webReader = new WebReader();
            this.game = new Game(webReader, webWriter);
            game.setUpGame();
        }
        webReader.setX(x);
        webReader.setY(y);
        game.play();
        String bufferContents = webWriter.getBuffer();
        webWriter.flushBuffer();
        return bufferContents;
    }

//    @RequestMapping("/")
//    public String Wawa() {
//        this.field = new Field(new Size(5,5), new RandomMinePlacer(new Size(5,5), new RealRandom()));
//        return field.getPlayerField().replace("\n","<br>");
//    }
//
//    @RequestMapping("/move")
//    public String Wawa2(){
//        Wawa();
//        field.revealSquare(new Coordinates(2,2));
//        return field.getPlayerField().replace("\n","<br>");
//    }

}
