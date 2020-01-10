package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;
import gr.auth.csd.sudoku.utilities.locale.Language;
import gr.auth.csd.sudoku.utilities.locale.Localization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represents the basis for all game modes' Windows. Language lang represents the language being used
 */
public abstract class SudokuWindow extends JFrame {
    private final Color fixedColor = Color.lightGray;

    protected Sudoku sudoku;
    protected int size;
    protected char[] charSet;
    protected int selRow;
    protected int selCol;
    protected JButton hint;
    protected SudokuCell[][] cells;
    protected JPanel grid;

    protected MouseListener mouseListener;
    protected KeyListener keyListener;

    protected Language lang = Localization.getLanguage();

    /**
     * The constructor initializes the GUI grid.
     * The hint button is added to the frame and the properties of the grid and background panels as well as the frame are set
     * Upon pressing the hint button, all the available characters for current cell are calculated and passed to a new object Hint as parameters
     * The grid panel and the hint button are added to the background panel, with itself being added in turn to our SudokuWindow.
     * @param sudoku The Sudoku object
     * @param charSet Array with the characters used in game. Used to distinguish between Wordoku and Sudoku and languages
     */
    public SudokuWindow(Sudoku sudoku, char[] charSet) {
        this.sudoku = sudoku;
        this.charSet = charSet;
        size = sudoku.getSize();

        JPanel background = new JPanel();
        background.setBackground(Color.DARK_GRAY);
        cells = new SudokuCell[size][size];
        hint = new JButton();
        hint.setText(lang.getString("hint"));
        hint.addActionListener(click -> {
            StringBuilder display = new StringBuilder();

            for (char c : charSet) {
                if(sudoku.isValidMove(selRow, selCol, getIndex(c))) {
                    display.append(c).append(" ");
                }
            }
            new Hint(display.toString());
        });

        grid = new JPanel(new GridLayout(size, size));
        grid.setPreferredSize(new Dimension(70 * size,70 * size));
        setListeners();
        initializeGrid();
        styleGrid();

        background.add(grid);
        background.add(hint);
        add(background);

        setSize(70 * size + 80, 70 * size + 80);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Converts a character to it's numeric value. Used to separate the logic(everything in numbers)
     * from the GUI(user can choose between numbers and letters) for abstraction purposes.
     * @param c the input character
     * @return numeric value of character if it belongs to the valid characters of charSet(essentially it's index).
     * Returns -1 if not found.
     */
    public int getIndex(char c) {
        for (int i = 0; i < charSet.length; i++) {
            if (c == charSet[i])
                return i;
        }
        return -1;
    }

    /**
     * Checks if char c is valid in the context of the current sudoku mode(numbers or letters).
     * @param c char whose validity is to be determined
     * @return true if it is valid, else false
     */
    public boolean isValidChar(char c) {
        int index = getIndex(c);
        return index > 0 && index <= sudoku.getSize();
    }

    /**
     * Utility method used to set Listeners
     */
    protected void setListeners() {
        mouseListener = new DefaultMouseListener();
        keyListener = new DefaultKeyListener();
    }

    /**
     * Creates SudCell objects to fill the Cells array. Creates fixed cells that are used for the classic sudoku.
     * The remaining cells are initialized to an empty string and have a mouse and key listener attached to them.
     * Finally, the cells are added to the grid.
     */
    private void initializeGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new SudokuCell();

                String num = String.valueOf(charSet[sudoku.getCell(i, j)]);
                cells[i][j].setText(num);

                if (sudoku.getCell(i, j) != 0) {
                    //cells from file are initiliazed to corresponding value and not to be edited
                    cells[i][j].setColor(fixedColor);
                    cells[i][j].getTextField().setEditable(false);
                }
                //empty cells
                else {
                    //finding the selected cell from mouse click
                    cells[i][j].getTextField().addMouseListener(mouseListener);
                    //waiting for user input in mouse selected cell
                    cells[i][j].getTextField().addKeyListener(keyListener);
                }

                grid.add(cells[i][j]);
            }
        }
    }

    /**
     * Utility method for grid Styling
     */
    protected void styleGrid() {}

    /**
     * This class represents our implementation of the MouseListener interface.
     * In the mousePressed method we obtain the coordinates of the cell in the grid chosen
     * by the user via mouse, for further processing.
     */
    public class DefaultMouseListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            JTextField selected = (JTextField) e.getSource();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (selected.equals(cells[i][j].getTextField())) {
                        selRow = i;
                        selCol = j;
                        System.out.println("You selected :" + selRow + " " + selCol);
                        e.consume();
                        return;
                    }
                }
            }
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }


    /**
     * This class represents our implementation of the KeyListener interface
     */
    public class DefaultKeyListener implements KeyListener {
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
         * We obtain the input from the cell in question, based on the coordinates obtained via MyMouseListener.
         * If the input is empty (backspace, delete or space button), the selected cell is cleared.
         *
         * If the input is a valid char, we get the numeric value of it
         * and check if it is a valid move and set the number and color of the Cell accordingly.
         * If value is not a valid char, the color of the Cell is restored, and the cell is cleared.
         * @param e Information about the event
         */
        @Override
        public void keyReleased(KeyEvent e) {
            String input = cells[selRow][selCol].getTextField().getText();

            if (input.isEmpty()) {
                cells[selRow][selCol].setWarning(false);
                cells[selRow][selCol].setText("");
                sudoku.clearCell(selRow, selCol);
                return;
            }

            char value = input.charAt(0);

            cells[selRow][selCol].setWarning(false);
            sudoku.clearCell(selRow, selCol);
            if (isValidChar(value)) {
                int numValue = getIndex(value);
                boolean validMove = sudoku.setCell(selRow, selCol, numValue);
                cells[selRow][selCol].setWarning(!validMove);

            }
            else {
                cells[selRow][selCol].setWarning(false);
                cells[selRow][selCol].setText("");
                sudoku.clearCell(selRow, selCol);
            }

        }

        public void keyTyped(KeyEvent e) {}
    }
}
