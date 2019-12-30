package gr.auth.csd.sudoku.variants.duidoku;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;

public class Duidoku extends ClassicSudoku {
    /**
     * Creates a new Duidoku from a given size.
     * @param size The Duidoku grid size
     */
    public Duidoku(int size) {
        super(size);
    }

    public boolean checkVictory() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == 0) {
                    for (int value = 1; value < gridSize + 1; value++) {
                        if (isValidMove(i, j, value))
                            return false;
                    }
                }
            }
        }
        return true;
    }
}
