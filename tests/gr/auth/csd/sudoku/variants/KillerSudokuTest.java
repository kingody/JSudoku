package gr.auth.csd.sudoku.variants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KillerSudokuTest {
    KillerSudoku killer;
    @BeforeEach
    void setUp() {
        killer = new KillerSudoku(9, "killer1.txt");
    }

    @Test
    void getAreas() {
        assertEquals(31, killer.getAreas().size());
    }

    @Test
    void isValidMove() {
        assertTrue(killer.setCell(0, 0, 5));
        assertFalse(killer.setCell(1, 0, 6));

        assertTrue(killer.setCell(1, 0, 1));
        assertFalse(killer.setCell(1, 1, 1));
        assertFalse(killer.setCell(2, 0, 1));
        assertTrue(killer.setCell(1, 1, 2));
        assertTrue(killer.setCell(2, 1, 3));
    }
}