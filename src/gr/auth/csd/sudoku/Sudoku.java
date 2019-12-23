package gr.auth.csd.sudoku;

/**
 * This class represents a basic Sudoku puzzle.
 */
public class Sudoku {
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
     * Creates a new Sudoku from a grid. Used for loading from files.
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

    public void setCell(int row, int col, int value) {
        if (isValidMove(row, col, value))
            grid[row][col] = value;
    }

    public int[][] getGrid() {
        return grid;
    }

    private boolean isOutOfBounds(int row, int col) {
        return (row < 0) || (row >= gridSize) || (col < 0) || (col >= gridSize);
    }

    private boolean isValidNumber(int value) {
        return (value > 0) && (value <= gridSize);
    }

    public boolean isValidMove(int row, int col, int value) {
        if (isOutOfBounds(row, col) || !isValidNumber(value))
            return false;

        for (int i = 0; i < gridSize; i++) {
            //Row and column uniqueness check
            if (grid[i][col] == value || grid[row][i] == value)
                return false;
        }

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
