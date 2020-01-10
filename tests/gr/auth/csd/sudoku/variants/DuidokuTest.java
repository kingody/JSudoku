package gr.auth.csd.sudoku.variants;

import gr.auth.csd.sudoku.utilities.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuidokuTest {
    private Duidoku duidoku;

    @BeforeEach
    void setUp() {
        duidoku = new Duidoku(4);
    }

    @Test
    void isCompleted() {
        while (!duidoku.isCompleted()) {
            computerMove();
        }
        assertTrue(duidoku.isCompleted());

        Index pos = duidoku.computerMove();
        assertEquals(pos, new Index(-1, -1));
    }

    @Test
    void computerMove() {
        Index pos = duidoku.computerMove();
        assertTrue(duidoku.getCell(pos.getRow(), pos.getColumn()) != 0);
    }
}