package gr.auth.csd.sudoku.gui.variants;

import gr.auth.csd.sudoku.utilities.Area;
import gr.auth.csd.sudoku.utilities.Index;
import gr.auth.csd.sudoku.variants.KillerSudoku;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the GUI on which the user plays the Killer version of Sudoku and is a child of ClassicWindow
 */
public class KillerWindow extends ClassicWindow {
    /**
     * Calls constructor of parent class, with sud and chars as parameters.
     * @param sud Killer Sudoku object
     * @param chars char array with acceptable characters
     */
    public KillerWindow(KillerSudoku sud, char[] chars) {
        super(sud,chars);
        setTitle(lang.getString("kill"));
    }

    /**
     * Adds colors to the areas and displays the desired sum of each area within one of its cells
     * For each area, its cells are acquired, and colored randomly
     * This is done to indicate that these cells' sum must amount to the sum of the label
     */
    @Override
    protected void styleGrid() {
        super.styleGrid();

        KillerSudoku killer = (KillerSudoku) sudoku;
        for (Area area : killer.getAreas()) {
            ArrayList<Index> connectedCells = area.getCells();

            float hue = (float) Math.random();
            float saturation = (float) Math.random() * 0.5f + 0.2f;
            float brightness = (float) Math.random()* 0.5f + 0.5f;
            int rgb = Color.HSBtoRGB(hue, saturation, brightness);
            Color color = new Color(rgb);

            Index first = connectedCells.get(0);
            int row = first.getRow(), col = first.getColumn();

            cells[row][col].addLabel(Integer.toString(area.getSum()));

            for (Index position : connectedCells) {
                row = position.getRow();
                col = position.getColumn();

                cells[row][col].setColor(color);
            }
        }
    }

}
