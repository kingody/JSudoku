package gr.auth.csd.sudoku.classic;

import gr.auth.csd.sudoku.FileHandler;
import gr.auth.csd.sudoku.Sudoku;

import java.util.Scanner;

/**
 * This class represents the Classic Sudoku.
 */
public class ClassicSudoku extends Sudoku {
    /**
     * Creates an empty Classic Sudoku from a given size.
     * @param size The Sudoku grid size
     */
    public ClassicSudoku(int size) {
        super(size);
    }

    /**
     * Creates a new Classic Sudoku from a grid.
     * @param grid The Sudoku grid
     */
    public ClassicSudoku(int[][] grid) {
        super(grid);
    }

    /**
     * Creates a new Classic Sudoku from a file.
     * @param size The Sudoku grid size.
     * @param filename The name of the file.
     */
    public ClassicSudoku(int size, String filename) {
        super(size);
        Scanner scanner = FileHandler.openFile(fileDir + "Classic/" + filename);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
    }

    public boolean isValidMove(int row, int col, int value) {
        if (!super.isValidMove(row, col, value))
            return false;

        int squareSize = (int) Math.sqrt(gridSize);

        int squareRow = row / squareSize;
        int squareCol = col / squareSize;

        for (int i = squareSize * squareRow; i < squareSize * squareRow + squareSize; i++) {
            for (int j = squareSize * squareCol; j < squareSize * squareCol + squareSize; j++) {
                //Square uniqueness check
                if (grid[i][j] == value)
                    return false;
            }
        }
        return true;
    }
}
