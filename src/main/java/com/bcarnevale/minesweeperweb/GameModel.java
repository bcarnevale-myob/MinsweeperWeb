package com.bcarnevale.minesweeperweb;

import Field.Coordinate;
import Field.Size;
import Game.Game;
import MinePlacer.Random;
import com.bcarnevale.minesweeperweb.Game.JsonReader;
import com.bcarnevale.minesweeperweb.Game.JsonWriter;

public class GameModel {

    private Game game;
    private final JsonWriter writer;
    private final JsonReader reader;


    public GameModel(Integer height, Integer width, Random random){
        reader = new JsonReader();
        writer = new JsonWriter();
        this.game = new Game(reader, writer, random);
        this.game.setUpGame(new Size(height, width));
    }

    public void makeAMove(Coordinate coordinate) {
        game.makeAMove(coordinate);
    }

    public String[] getField() {
        return writer.getBuffer();
    }

    public boolean getGameOver() {
        return game.getGameIsOver();
    }

}
