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
     */
    public DuidokuWindow(Duidoku duidoku, char[] charSet) {
        super(duidoku, charSet, false);
        setListeners();

        grid.setPreferredSize(new Dimension(280,280));

        setTitle(lang.getString("duidoku"));
        setSize(350,370);
        setLocationRelativeTo(null);
    }

    /**
     * Utility method to show the user if they won.
     * @param isWin true if the user won, false otherwise
     */
    public void finishGame(boolean isWin) {
        User user =  User.getCurrentUser();

        if (isWin) {
            new Hint("YOU WIN");

            if (user != null)
                user.incrementWins();
        }
        else {
            new Hint("YOU LOSE");

            if (user != null)
                user.incrementLosses();
        }
    }

    /**
     * Adds DuidokuKeyListener to each cell's inputText
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
     * @param comp JComponent whose listeners are to be removed
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
         * We obtain the input (String) from the cell in question, based on the coordinates obtained via MyMouseListener.
         * If the input is not emptychar value is set to the first character of input String. If it is an invalid character the SudCell is emptied
         * Else, we get the numeric value of it and check if it is a valid move. if not the SudCell is emptied
         * Otherwise, the respective cell in Sudoku grid is set, the inputText of said cells is made ineditable, and its listeners are removed
         * Then, we check is the user won with this move. If not, the computers move is calculated and set on the GUI grid for the user to see
         * The cells on which this took place is made ineditable and its listeners are removed. Lastly we check if the user lost with this move
         *
         * @param e the KeyEvent object
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

            if (duidoku.isCompleted()) {
                finishGame(true);
                return;
            }

            Index compMove = duidoku.computerMove();

            int row = compMove.getRow(), col = compMove.getColumn();
            int val = duidoku.getCell(row, col);
            cells[row][col].setInputText(String.valueOf(charSet[val]));
            cells[row][col].getInputTextField().setEditable(false);
            removeListeners(cells[row][col].getInputTextField());
            cells[row][col].getInputTextField().setForeground(Color.RED);

            if (duidoku.isCompleted()) {
                finishGame(false);
            }
        }
    }

}
