package gr.auth.csd.sudoku;

/**
 * This class represents a basic Sudoku puzzle.
 */
public class Sudoku {
    //Sudoku file directory
    protected final String fileDir = "Sudokus/";
    protected final int gridSize;
    protected int[][] grid;

    /**
     * Creates an empty Sudoku from a given size.
     * @param size The Sudoku grid size
     */
    public Sudoku(int size) {
        gridSize = size;
        grid = new int[gridSize][gridSize];
    }

    /**
     * Creates a new Sudoku from a grid.
     * @param grid The Sudoku grid
     */
    public Sudoku(int[][] grid) {
        gridSize = grid.length;
        this.grid = grid;
    }

    public int getCell(int row, int col) {
        if (isOutOfBounds(row, col))
            return -1;

        return grid[row][col];
    }

    /**
     * Sets a value to a cell.
     * @param row The row of the Cell.
     * @param col The Column of the Cell.
     * @param value The chosen value.
     * @return true if the move was valid, false otherwise.
     */
    public boolean setCell(int row, int col, int value) {
        if (isValidMove(row, col, value)) {
            grid[row][col] = value;
            return true;
        }
        return false;
    }

    public void clearCell(int row, int col) {
        if (!isOutOfBounds(row, col))
            grid[row][col] = 0;
    }

    public int getSize() { return gridSize; }

    private boolean isOutOfBounds(int row, int col) {
        return (row < 0) || (row >= gridSize) || (col < 0) || (col >= gridSize);
    }

    private boolean isValidNumber(int value) {
        return (value > 0) && (value <= gridSize);
    }

    /**
     * Determines if a move obides by Sudoku's rules
     * @param row Row of Cell
     * @param col Column of Cell
     * @param value Numeric value whose validity is to be determined
     * @return Whether or not it is a valid move
     */
    public boolean isValidMove(int row, int col, int value) {
        if (isOutOfBounds(row, col) || !isValidNumber(value))
            return false;

        for (int i = 0; i < gridSize; i++) {
            //Row and column uniqueness check
            if (grid[i][col] == value || grid[row][i] == value)
                return false;
        }
        return true;
    }

    public boolean isCompleted() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
}
