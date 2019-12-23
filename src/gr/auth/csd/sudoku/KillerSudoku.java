package gr.auth.csd.sudoku;

import java.util.ArrayList;

/**
 * This class represents a Sudoku variation called Killer Sudoku.
 */
public class KillerSudoku extends Sudoku {
    private ArrayList<Area> areas;

    /**
     * Creates a new Killer Sudoku from a given size and list of areas.
     * @param size The Sudoku grid size
     */
    public KillerSudoku(int size, ArrayList<Area> areas) {
        super(size);
        this.areas = areas;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public boolean isValidMove(int row, int col, int value) {
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

                    int cellValue = getCell(cell.getRow(), cell.getColumn());

                    if (cellValue == value)
                        return false;

                    sum += cellValue;
                }

                return sum <= area.getSum();
            }
        }
        return false;
    }
}
