package gr.auth.csd.sudoku.gui.variants;

import gr.auth.csd.sudoku.gui.FinishWindow;
import gr.auth.csd.sudoku.gui.SudokuWindow;
import gr.auth.csd.sudoku.utilities.User;
import gr.auth.csd.sudoku.variants.ClassicSudoku;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

/**
 * This class represents the GUI on which the user plays the classic version of Sudoku and is a child of SudokuWindow
 */
public class ClassicWindow extends SudokuWindow {
    /**
     * Constructs the Window using the parent's constructor and sets its title
     * @param sudoku ClassicSudoku object
     * @param charSet Acceptable characters
     */
    public ClassicWindow(ClassicSudoku sudoku, char[] charSet) {
        super(sudoku, charSet);
        setTitle(lang.getString("classic"));
    }

    /**
     * Method to make 3*3 squares easily visible
     */
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

    @Override
    protected void endGame() {
        super.endGame();
        User user = User.getCurrentUser();
        user.addToSolved(((ClassicSudoku) sudoku).getFilename());
    }
}
