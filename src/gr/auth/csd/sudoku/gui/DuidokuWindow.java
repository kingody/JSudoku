package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;
import gr.auth.csd.sudoku.variants.duidoku.Duidoku;

import java.awt.*;

public class DuidokuWindow extends SudokuWindow {

    public DuidokuWindow(String title, Duidoku duidoku, char[] charSet) {
        super(title, duidoku, charSet, false);
        grid.setPreferredSize(new Dimension(280,280));
        setSize(400,400);
    }
}
