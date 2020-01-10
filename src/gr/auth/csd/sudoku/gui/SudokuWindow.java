package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;
import gr.auth.csd.sudoku.gui.locale.Language;
import gr.auth.csd.sudoku.gui.locale.Localization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represents the GUI on which the user plays the classic version of Sudoku. Language lang represents the language being used
 */
public class SudokuWindow extends JFrame {
    protected Color fixedColor = Color.gray;

    protected Sudoku sudoku;
    protected char[] charSet;
    protected int rowsel;
    protected int colsel;
    protected JButton hint;
    protected SudCell[][] cells;
    protected JPanel grid;

    protected Language lang = Localization.getLanguage();

    /**
     * This constructor is used to call the other constructor with sudoku and charSet as parameters
     * The boolean true indicates that there will be KeyListeners
     * @param sudoku The Sudoku object
     * @param charSet Array with the characters used in game. Used to distinguish between Wordoku and Sudoku and languages
     */
    public SudokuWindow(Sudoku sudoku, char[] charSet) {
        this(sudoku, charSet, true);
    }

    /**
     * The constructor initializes the GUI grid.
     * The hint button is added to the frame and the properties of the grid and background panels as well as the frame are set
     * Upon pressing the hint button, all the available characters for current cell are calculated and passed to a new object Hint as parameters
     * The grid panel and the hint button are added to the background panel, with itself being added in turn to our SudokuWindow.
     * @param sudoku The Sudoku object
     * @param charSet Array with the characters used in game. Used to distinguish between Wordoku and Sudoku and languages
     * @param setKeyListeners determines whether or not the keylisteners will be set for each cell
     */
    public SudokuWindow(Sudoku sudoku, char[] charSet, boolean setKeyListeners) {
        this.sudoku = sudoku;
        this.charSet = charSet;
        int size = sudoku.getSize();

        JPanel background = new JPanel();
        background.setBackground(Color.DARK_GRAY);
        grid = new JPanel(new GridLayout(size, size));
        cells = new SudCell[size][size];
        initializeGrid(setKeyListeners);
        hint = new JButton();
        hint.setText(lang.getString("hint"));
        hint.addActionListener(click -> {
            StringBuilder display = new StringBuilder();

            for (char c: charSet) {
                if(sudoku.isValidMove(rowsel,colsel,getIndex(c))){
                    display.append(c).append(" ");
                }
            }
            new Hint(display.toString());
        });

        grid.setPreferredSize(new Dimension(630,630));
        background.add(grid);
        background.add(hint);
        this.add(background);

        setTitle(lang.getString("menuTitle"));
        setSize(710, 710);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
     * Creates SudCell objects to fill the Cells array. Creates fixed cells that are used for the classic sudoku.
     * The remaining cells are initialized to an empty string and have a MyMouseListener mouse listener associated with them.
     * A MyKeyListener is also added to the cells if setKeyListener is true.
     * Finally, the cells are added to the grid.
     * @param setKeyListeners determines if the cells will have the default key listeners
     */
    public void initializeGrid(boolean setKeyListeners) {
        int size = sudoku.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new SudCell();

                if (sudoku.getCell(i, j) != 0) {
                    //cells from file are initiliazed to corresponding value and not to be edited
                    String num = String.valueOf(charSet[sudoku.getCell(i, j)]);
                    cells[i][j].setInputText(num);
                    cells[i][j].setColor(fixedColor);
                    cells[i][j].getInputTextField().setEditable(false);
                }
                else {
                    //empty cells
                    cells[i][j].setInputText("");
                    //finding the selected cell from mouse click
                    cells[i][j].getInputTextField().addMouseListener(new DefaultMouseListener());

                    if (setKeyListeners) {
                        //waiting for user input in mouse selected cell
                        DefaultKeyListener a = new DefaultKeyListener();
                        cells[i][j].getInputTextField().addKeyListener(a);
                    }

                }
                grid.add(cells[i][j]);
            }
        }
        for(int i = (int)Math.sqrt(size); i<size;i+=(int)Math.sqrt(size)){
            for(int j =0;j<size;j++){
                cells[i][j].setBorder(BorderFactory.createMatteBorder(3,1,1,1,Color.BLACK));
                cells[j][i].setBorder(BorderFactory.createMatteBorder(1,3,1,1,Color.BLACK));
            }
        }
    }

    /**
     * This class represents our implementation of the MouseListener interface.
     * In the mousePressed method we obtain the coordinates of the cell in the grid chosen
     * by the user via mouse, for further processing.
     */
    public class DefaultMouseListener implements MouseListener{
        int tempRow, tempCol;

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            JTextField selected = (JTextField) e.getSource();
            int size = sudoku.getSize();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (selected.equals(cells[i][j].getInputTextField())) {
                        rowsel = i;
                        colsel = j;
                        System.out.println("You selected :" + rowsel + " " + colsel);
                        e.consume();
                        return;
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }


    /**
     * This class represents our implementation of the KeyListener interface
     */
    public class DefaultKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {}

        /**
         *  The cell is emptied when the user input is an accepted character.
         *  This also prevents the user from entering invalid characters to occupied cells.
         * @param e Information about the event
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
            String input = cells[rowsel][colsel].getInputTextField().getText();

            if (input.isEmpty()) {
                cells[rowsel][colsel].setWarning(false);
                cells[rowsel][colsel].setInputText("");
                sudoku.clearCell(rowsel,colsel);
                return;
            }

            char value = input.charAt(0);

            cells[rowsel][colsel].setWarning(false);
            sudoku.clearCell(rowsel, colsel);
            if (isValidChar(value)) {
                int numValue = getIndex(value);
                boolean validMove = sudoku.setCell(rowsel, colsel, numValue);
                cells[rowsel][colsel].setWarning(!validMove);

            }
            else {
                cells[rowsel][colsel].setWarning(false);
                cells[rowsel][colsel].setInputText("");
                sudoku.clearCell(rowsel, colsel);
            }

        }

    }
}
