package gr.auth.csd.sudoku.utilities;

import java.util.ArrayList;

/**
 * This class represents a Killer Sudoku area
 */
public class Area {
    private int sum;
    private ArrayList<Index> cells;

    /**
     * Creates the area
     * @param sum of area
     * @param cells of area
     */
    public Area(int sum, ArrayList<Index> cells) {
        this.sum = sum;
        this.cells = cells;
    }

    public int getSum() {
        return sum;
    }

    /**
     * Gets cells of area
     * @return Cells of area
     */
    public ArrayList<Index> getCells() {
        return cells;
    }
}
