package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class ClassicWindow extends SudokuWindow {

    public ClassicWindow(ClassicSudoku sudoku, char[] charSet) {
        super(sudoku, charSet);
        setTitle(lang.getString("classic"));
    }

    @Override
    protected void styleGrid() {
        int boxSize = (int) Math.sqrt(size);
        Border topBorder = BorderFactory.createMatteBorder(2,0,0,0, Color.BLACK);
        Border sideBorder = BorderFactory.createMatteBorder(0,2,0,0, Color.BLACK);

        for(int i = boxSize; i < size; i += boxSize) {
            for(int j = 0; j < size; j++) {
                cells[i][j].setBorder(new CompoundBorder(cells[i][j].getBorder(), topBorder));
                cells[j][i].setBorder(new CompoundBorder(cells[j][i].getBorder(), sideBorder));
            }
        }
    }
}
