package gr.auth.csd.sudoku.killer;

import java.util.ArrayList;

public class Area {
    private int sum;
    private ArrayList<Index> cells;

    public Area(int sum, ArrayList<Index> cells) {
        this.sum = sum;
        this.cells = cells;
    }

    public int getSum() {
        return sum;
    }

    public ArrayList<Index> getCells() {
        return cells;
    }
}
