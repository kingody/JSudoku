package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.User;
import gr.auth.csd.sudoku.variants.duidoku.Duidoku;
import gr.auth.csd.sudoku.variants.killer.Index;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class represents the GUI on which the user plays Duidoku against the pc. It is a child of SudokuWindow class
 */
public class DuidokuWindow extends SudokuWindow {
    /**
     * Calls super contructor with duidoku charSet and false as parameters. Basically this creates a SudokuWindow with no key Listeners
     * Then calls setListeners and sets the properties of DuidokuWindow
     * @param duidoku Duidoku object
     * @param charSet Acceptable characters
     * @param user User object
     */
    public DuidokuWindow(Duidoku duidoku, char[] charSet, User user) {
        super(duidoku, charSet, false);
        setListeners();

        grid.setPreferredSize(new Dimension(280,280));

        setTitle(lang.getString("duidoku"));
        setSize(350,370);
        setLocationRelativeTo(null);
    }

    public void finishGame(boolean isWin) {
        if (isWin)
            new Hint("YOU WIN");
        else
            new Hint("YOU LOSE");
    }

    /**
     * Adds DuidokuKeyListener to each cells
     */
    public void setListeners() {
        int size = sudoku.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].getInputTextField().addKeyListener(new DuidokuKeyListener());
            }
        }
    }

    /**
     * Removes listeners from a component
     * @param comp JComponent whole listeners are to be removed
     */
    public void removeListeners(JComponent comp) {
        for (KeyListener listener : comp.getKeyListeners())
            comp.removeKeyListener(listener);
    }

    /**
     * This class represents our implementation of KeyListener interface for Duidoku
     */
    public class DuidokuKeyListener implements KeyListener{
        Duidoku duidoku = (Duidoku) sudoku;

        @Override
        public void keyTyped(KeyEvent e) {

        }

        /**
         *  The cell is emptied when the user input is an accepted character.
         *  This also prevents the user from entering invalid characters to occupied cells.
         * @param e Key event
         */
        @Override
        public void keyPressed(KeyEvent e) {
            for (char c : charSet)
                if (c == e.getKeyChar()) {
                    cells[rowsel][colsel].setInputText("");
                    return;
                }
        }

        /**
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {

            String input = cells[rowsel][colsel].getInputTextField().getText();
            if (input.isEmpty())
                return;

            char value = input.charAt(0);
            if (!isValidChar(value)) {
                cells[rowsel][colsel].setInputText("");
                return;
            }

            int numValue = getIndex(value);
            boolean validMove = sudoku.setCell(rowsel, colsel, numValue);

            if (!validMove) {
                cells[rowsel][colsel].setInputText("");
                return;
            }
            cells[rowsel][colsel].getInputTextField().setEditable(false);
            removeListeners(cells[rowsel][colsel].getInputTextField());

            if (duidoku.checkVictory()) {
                finishGame(true);
                return;
            }

            Index compMove = duidoku.computerMove();

            int row = compMove.getRow(), col = compMove.getColumn();
            int val = duidoku.getCell(row, col);
            cells[row][col].setInputText(String.valueOf(charSet[val]));
            cells[row][col].getInputTextField().setEditable(false);
            cells[row][col].getInputTextField().setForeground(Color.RED);
            removeListeners(cells[row][col].getInputTextField());

            if (duidoku.checkVictory()) {
                finishGame(false);
            }
        }
    }

}
