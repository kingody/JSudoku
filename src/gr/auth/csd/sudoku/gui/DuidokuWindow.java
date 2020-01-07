package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.duidoku.Duidoku;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DuidokuWindow extends SudokuWindow {

    public DuidokuWindow(Duidoku duidoku, char[] charSet) {
        super(duidoku, charSet);
        grid.setPreferredSize(new Dimension(280,280));


        setTitle(lang.getString("duidoku"));
        setSize(400,400);
    }

}
