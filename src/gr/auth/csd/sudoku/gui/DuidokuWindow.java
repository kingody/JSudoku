package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;
import gr.auth.csd.sudoku.variants.duidoku.Duidoku;

import java.awt.*;
import java.util.ResourceBundle;

public class DuidokuWindow extends SudokuWindow {

    public DuidokuWindow(String title, Duidoku duidoku, char[] charSet, ResourceBundle bundle) {
        super(title, duidoku, charSet,bundle);
        grid.setPreferredSize(new Dimension(280,280));
        setSize(400,400);
    }
}
