package com.bcarnevale.minesweeperweb.Controllers;

import Field.Coordinate;
import MinePlacer.Random;
import com.bcarnevale.minesweeperweb.GameModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private GameModel currentGame;
    private Random random;

    public GameController(Random random) {
        this.random = random;
    }

    @PostMapping("/api/game/setup/{height}/{width}/")
    public GameModel hello(@PathVariable("height") Integer height, @PathVariable("width") Integer width) {
        this.currentGame = new GameModel(height, width, this.random);
        return currentGame;
    }

    @PostMapping("/api/game/play/{x}/{y}/")
    public GameModel makeAMove(@PathVariable("x") int x, @PathVariable("y") int y) {
        currentGame.makeAMove(new Coordinate(x, y));
        return currentGame;
    }
}
