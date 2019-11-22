package gr.auth.csd.sudoku;

public abstract class Sudoku {
    protected int gridSize;
    protected int[][] grid;

    public Sudoku(int size) {
        gridSize = size;
        grid = new int[gridSize][gridSize];
    }

    private boolean isOutOfBounds(int row, int col) {
        return (row >= 0) && (row < gridSize) && (col >= 0) && (col < gridSize);
    }

    private boolean isValidNumber(int value) {
        return (value > 0) && (value <= gridSize);
    }

    public int getCell(int row, int col) {
        if (isOutOfBounds(row, col))
            return -1;

        return grid[row][col];
    }

    public void setCell(int row, int col, int value) {
        if (isOutOfBounds(row, col) && !isValidMove(row, col, value))
            return;

        grid[row][col] = value;
    }

    private boolean isValidMove(int row, int col, int value) {
        if (!isValidNumber(value))
            return false;

        for (int i = 0; i < gridSize; i++) {
            if (grid[i][col] == value || grid[row][i] == value)
                return false;
        }

        int squareRow = row / 3;
        int squareCol = col / 3;

        for (int i = 3 * squareRow; i < 3 * squareRow + 3; i++) {
            for (int j = 3 * squareCol; j < 3 * squareCol + 3; j++) {
                if (grid[i][j] == value)
                    return false;
            }
        }
        return true;
    }
}
