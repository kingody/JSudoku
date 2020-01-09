package gr.auth.csd.sudoku.variants.duidoku;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;
import gr.auth.csd.sudoku.variants.killer.Index;

import java.util.ArrayList;
import java.util.Random;

public class Duidoku extends ClassicSudoku {
    /**
     * Creates a new Duidoku from a given size.
     * @param size The Duidoku grid size
     */
    public Duidoku(int size) {
        super(size);
    }

    @Override
    public boolean isCompleted() {
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

    private ArrayList<Index> getFreeCells() {
        ArrayList<Index> freeCells = new ArrayList<>();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == 0 && hasLegalMoves(i, j)) {
                    freeCells.add(new Index(i, j));
                }
            }
        }

        return freeCells;
    }

    private boolean hasLegalMoves(int row, int col) {
        for (int val = 1; val <= gridSize; val++) {
            if (isValidMove(row, col, val))
                return true;
        }

        return false;
    }

    public Index computerMove() {
        Random random = new Random();
        ArrayList<Index> freeCells = getFreeCells();
        Index index = freeCells.get(random.nextInt(freeCells.size()));
        int row = index.getRow(), col = index.getColumn(), value;

        do {
            value = 1 + random.nextInt(gridSize);
        } while(!isValidMove(row, col, value));

        setCell(row, col, value);
        return new Index(row, col);
    }
}
