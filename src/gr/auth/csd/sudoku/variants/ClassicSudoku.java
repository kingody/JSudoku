package gr.auth.csd.sudoku.variants;

import gr.auth.csd.sudoku.utilities.FileHandler;
import gr.auth.csd.sudoku.Sudoku;

import java.util.Scanner;

/**
 * This class represents the Classic Sudoku. It is a child of Sudoku
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

    /**
     * Makes the same validity checks as the parent. It however conducts the square uniqueness check
     * @param row Row of Cell
     * @param col Column of Cell
     * @param value Numeric value whose validity is to be determined
     * @return Wheter or not is is a valid move.
     */
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
