package gr.auth.csd.sudoku.variants.killer;

import java.util.Objects;

/**
 * This class represents a set of 2d coordinates
 */
public class Index {
    private int row, column;

    /**
     * Creates Index with row and column
     * @param row
     * @param column
     */
    public Index(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    /**
     * Overriding equals method to compare both coordinates
     * @param o Object
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return row == index.row &&
                column == index.column;
    }


    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
