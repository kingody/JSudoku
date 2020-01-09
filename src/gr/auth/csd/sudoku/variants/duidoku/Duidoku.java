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

    /**
     * In this method, a list of all the available cells is obtained.
     * The value of each cell needs to be 0 and have a legal move in order to be added to the list
     * @return ArrayList<Index> freeCells
     */
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

    /**
     * Checks if the specified cell can accept any legal value
     * @param row Row of cell
     * @param col Column of cell
     * @return True if it can, false otherwise
     */
    private boolean hasLegalMoves(int row, int col) {
        for (int val = 1; val <= gridSize; val++) {
            if (isValidMove(row, col, val))
                return true;
        }

        return false;
    }

    /**
     *This method produces a computer move. First the free cells are acquired and one is selected randomly.
     * An acceptable value is selected randomly, until a legal move occurs, in which case the value is set in said cell.
     * @return Index of move
     */
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
