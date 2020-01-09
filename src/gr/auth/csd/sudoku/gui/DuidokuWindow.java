package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.User;
import gr.auth.csd.sudoku.variants.duidoku.Duidoku;
import gr.auth.csd.sudoku.variants.killer.Index;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DuidokuWindow extends SudokuWindow {

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

    public void setListeners() {
        int size = sudoku.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].getInputTextField().addKeyListener(new DuidokuKeyListener());
            }
        }
    }

    public void removeListeners(JComponent comp) {
        for (KeyListener listener : comp.getKeyListeners())
            comp.removeKeyListener(listener);
    }

    public class DuidokuKeyListener implements KeyListener{
        Duidoku duidoku = (Duidoku) sudoku;

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            for (char c : charSet)
                if (c == e.getKeyChar()) {
                    cells[rowsel][colsel].setInputText("");
                    return;
                }
        }

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
