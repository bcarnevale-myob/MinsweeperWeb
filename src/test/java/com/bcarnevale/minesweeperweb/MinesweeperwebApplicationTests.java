package com.bcarnevale.minesweeperweb;

import Field.Coordinates;
import MinePlacer.*;
import com.bcarnevale.minesweeperweb.Controllers.FirstController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinesweeperwebApplicationTests {

    @Test
    public void whenANewGameIsLoadedTheDesiredHeightAndWidthCreateAFieldOfTheCorrectSize() {
        // Arrange
        FirstController controller = new FirstController(new RealRandomNumberGenerator());

        // Act
        String actualField = controller.setUpGame(5,4);

        // Assert
        String expectedField =
                "Welcome To MineSweeper!<br><br>Let's Play...<br>" +
                        "<br>....<br>" +
                        "....<br>" +
                        "....<br>" +
                        "....<br>" +
                        "....<br><br>";

        assertEquals(expectedField, actualField);

    }

    @Test
    public void whenAMoveIsMadeOnCoordinate2_3ThenThatSquareIsRevealed() {
        // Arrange
        Coordinates[] minePositions = new Coordinates[2];
        minePositions[0] = new Coordinates(1,1);
        minePositions[1] = new Coordinates(0,0);
        FirstController controller = new FirstController(new PredictableNumberGenerator(2, minePositions));
        controller.setUpGame(5,4);

        // Act
        String actualField = controller.makeAMove(2,3);

        // Assert
        String expectedField =
                        "....<br>" +
                        "....<br>" +
                        "...0<br>" +
                        "....<br>" +
                        "....<br><br>";

        assertEquals(expectedField, actualField);

    }

}
