package com.bcarnevale.minesweeperweb.Controllers;

import Field.Coordinate;
import MinePlacer.Random;
import com.bcarnevale.minesweeperweb.GameModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity setup(@PathVariable("height") Integer height, @PathVariable("width") Integer width) {
        currentGame = new GameModel(height, width, this.random);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/api/game/play/{x}/{y}/")
    public ResponseEntity makeAMove(@PathVariable("x") Integer x, @PathVariable("y") Integer y) {
        currentGame.makeAMove(new Coordinate(x, y));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/api/game/board/")
    public GameModel getCurrentGame() {
        return currentGame;
    }

}
