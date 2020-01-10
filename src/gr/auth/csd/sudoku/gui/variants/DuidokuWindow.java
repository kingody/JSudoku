package gr.auth.csd.sudoku.gui.variants;

import gr.auth.csd.sudoku.utilities.User;
import gr.auth.csd.sudoku.gui.FinishWindow;
import gr.auth.csd.sudoku.variants.Duidoku;
import gr.auth.csd.sudoku.utilities.Index;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class represents the GUI on which the user plays Duidoku against the pc. It is a child of ClassicWindow class
 */
public class DuidokuWindow extends ClassicWindow {
    /**
     * Calls super contructor with duidoku charSet
     * Then calls setListeners and sets the properties of DuidokuWindow
     * @param duidoku Duidoku object
     * @param charSet Acceptable characters
     */
    public DuidokuWindow(Duidoku duidoku, char[] charSet) {
        super(duidoku, charSet);

        setTitle(lang.getString("duidoku"));
    }

    @Override
    protected void setListeners() {
        mouseListener = new DefaultMouseListener();
        keyListener = new DuidokuKeyListener();
    }

    /**
     * Removes listeners from a component
     * @param comp JComponent whose listeners are to be removed
     */
    private void removeKeyListeners(JComponent comp) {
        for (KeyListener listener : comp.getKeyListeners())
            comp.removeKeyListener(listener);
    }

    /**
     * Utility method to show the user if they won.
     * @param isWin true if the user won, false otherwise
     */
    public void endGame(boolean isWin) {
        User user =  User.getCurrentUser();

        if (user != null) {
            if (isWin)
                user.incrementWins();
            else
                user.incrementLosses();
        }

        new FinishWindow(isWin,this);
    }

    /**
     * This class represents our implementation of KeyListener interface for Duidoku
     */
    private class DuidokuKeyListener implements KeyListener{
        private Duidoku duidoku = (Duidoku) sudoku;

        /**
         *  The cell is emptied when the user input is an accepted character.
         *  This also prevents the user from entering invalid characters to occupied cells.
         * @param e Information about the event
         */
        @Override
        public void keyPressed(KeyEvent e) {
            for (char c : charSet)
                if (c == e.getKeyChar()) {
                    cells[selRow][selCol].setText("");
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
         * @param e Information about the event
         */
        @Override
        public void keyReleased(KeyEvent e) {

            String input = cells[selRow][selCol].getTextField().getText();
            if (input.isEmpty())
                return;

            char value = input.charAt(0);
            if (!isValidChar(value)) {
                cells[selRow][selCol].setText("");
                return;
            }

            int numValue = getIndex(value);
            boolean validMove = sudoku.setCell(selRow, selCol, numValue);

            if (!validMove) {
                cells[selRow][selCol].setText("");
                return;
            }
            cells[selRow][selCol].getTextField().setEditable(false);
            removeKeyListeners(cells[selRow][selCol].getTextField());

            if (duidoku.isCompleted()) {
                endGame(true);
                return;
            }

            Index compMove = duidoku.computerMove();

            int row = compMove.getRow(), col = compMove.getColumn();
            int val = duidoku.getCell(row, col);
            cells[row][col].setText(String.valueOf(charSet[val]));
            cells[row][col].getTextField().setEditable(false);
            removeKeyListeners(cells[row][col].getTextField());
            cells[row][col].getTextField().setForeground(Color.RED);

            if (duidoku.isCompleted())
                endGame(false);
        }

        public void keyTyped(KeyEvent e) {}
    }
}
