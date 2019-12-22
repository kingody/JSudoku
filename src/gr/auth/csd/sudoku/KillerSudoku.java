package gr.auth.csd.sudoku;

import java.util.ArrayList;

public class KillerSudoku extends Sudoku {
    private ArrayList<Area> areas;

    /**
     * Creates a new Killer Sudoku from a given size and list of areas.
     * @param size The Sudoku grid size
     */
    public KillerSudoku(int size) {
        super(size);
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    protected boolean isValidMove(int row, int col, int value) {
        if (!super.isValidMove(row, col, value))
            return false;

        Index currentCell = new Index(row, col);
        int sum = value;
        for (Area area : areas) {
            ArrayList<Index> cells = area.getCells();

            if (cells.contains(currentCell)) {
                for(Index cell : cells) {
                    if (cell.equals(currentCell))
                        continue;

                    sum += getCell(cell.getRow(), cell.getColumn());
                }

                return sum <= area.getSum();
            }
        }
        return false;
    }
}
