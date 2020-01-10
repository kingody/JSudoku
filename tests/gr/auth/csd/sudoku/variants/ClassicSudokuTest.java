package gr.auth.csd.sudoku.variants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicSudokuTest {
    private ClassicSudoku sud;

    @BeforeEach
    void setUp(){
       sud = new ClassicSudoku(9,"classic1.txt");
    }

    @Test
    void setCell() {
        assertTrue(sud.setCell(2,0,5));
    }

    @Test
    void clearCell(){
        sud.clearCell(3,0);
        assertEquals(0,sud.getCell(3,0));
    }
    @Test
    void checkVictory(){
        assertFalse(sud.isCompleted());
    }
    @Test
    void getSize(){
        assertEquals(9,sud.getSize());
    }
}