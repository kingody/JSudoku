package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represents the GUI on which the user plays the classic version of Sudoku.
 */
public class SudokuWindow extends JFrame {
    protected Color fixedColor = Color.gray;

    private Sudoku sudoku;
    protected char[] charSet;
    protected int rowsel;
    protected int colsel;
    protected JButton hint;
    protected SudCell[][] cells;
    //protected JTextField[][] cells;
    protected JPanel grid;


    /**
     * The constructor initializes the GUI grid, sets the properties of each cell (color, initial values, editability, mouse and keylisteners etc)
     * @param title The mode played (classic,killer or wordoku)
     * @param sudoku Sudoku object
     * @param charSet Array with the characters used in game. Used to distinguish between Wordoku and Sudoku and languages
     */
    public SudokuWindow(String title, Sudoku sudoku, char[] charSet) {
        super(title);
        this.sudoku = sudoku;
        this.charSet = charSet;
        int size = sudoku.getSize();


        JPanel background = new JPanel();
        background.setBackground(Color.DARK_GRAY);
        grid = new JPanel(new GridLayout(size, size));
        cells = new SudCell[size][size];
        initializeGrid();

        hint = new JButton("Hint");
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
        setSize(710, 710);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Converts input character to numeric value
     * @param c the input character
     * @return numeric value of character if it belongs to the valid characters of charSet. Else returns -1
     */

    public int getIndex(char c) {
        for (int i = 0; i < charSet.length; i++) {
            if (c == charSet[i])
                return i;
        }
        return -1;
    }

    /**
     * Checks if char c is valid
     * @param c char whose validity is to be determined
     * @return true if it is valid, else false
     */

    public boolean isValidChar(char c) {
        int index = getIndex(c);
        return index > 0 && index <= sudoku.getSize();
    }

    public void initializeGrid(){
        int size = sudoku.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new SudCell();
                /*cells[i][j] =  new JTextField();
                cells[i][j].setDocument(new TextLimit(1));*/

                if (sudoku.getCell(i, j) != 0) {
                    //cells from file are initiliazed to corresponding value and not to be edited
                    String num = String.valueOf(charSet[sudoku.getCell(i, j)]);
                    //cells[i][j].setText(num);
                    cells[i][j].setInputText(num);
                    cells[i][j].setColor(fixedColor);
                    cells[i][j].getInputTextField().setEditable(false);
                }
                else {
                    //empty cells
                    cells[i][j].getInputTextField().addMouseListener(new MyMouseListener()); //finding the selected cell from mouse click
                    MyKeyListener a = new MyKeyListener();
                    cells[i][j].getInputTextField().addKeyListener(a);//waiting for user input in mouse selected cell
                    //cells[i][j].setText("");
                    cells[i][j].setInputText("");
                }

                //cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                grid.add(cells[i][j]);
            }
        }
    }

    public class MyMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            JTextField selected = (JTextField) e.getSource();
            int size = sudoku.getSize();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (selected.equals(cells[i][j].getInputTextField())) {
                        rowsel = i;
                        colsel = j;
                        System.out.println("You selected :" + rowsel + " " + colsel);
                        return;
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }



    public class MyKeyListener implements KeyListener{
//        Color previous = cells[rowsel][colsel].getCellColor();


        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            for (char c : charSet)
                if (c == e.getKeyChar()) {
                    //cells[rowsel][colsel].setText("");
                    cells[rowsel][colsel].setInputText("");
                    return;
                }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //String input = cells[rowsel][colsel].getText();
//            Color previous = cells[rowsel][colsel].getCellColor();
            String input = cells[rowsel][colsel].getInputTextField().getText();
            if (input.isEmpty()) {
//                cells[rowsel][colsel].getInputTextField().setBackground(previous);
                cells[rowsel][colsel].setWarning(false);
                cells[rowsel][colsel].setInputText("");
                sudoku.clearCell(rowsel,colsel);
                return;
            }

            char value = input.charAt(0);

//            cells[rowsel][colsel].getInputTextField().setBackground(previous);
            cells[rowsel][colsel].setWarning(false);
            sudoku.clearCell(rowsel, colsel);

            if (isValidChar(value)) {
                int numValue = getIndex(value);
                boolean validMove = sudoku.setCell(rowsel, colsel, numValue);
//                Color color = validMove ? previous : wrongColor;
//                cells[rowsel][colsel].getInputTextField().setBackground(color);
                cells[rowsel][colsel].setWarning(!validMove);

            }
            else {
//                cells[rowsel][colsel].setBackground(previous);
                cells[rowsel][colsel].setWarning(false);
                cells[rowsel][colsel].setInputText("");
                sudoku.clearCell(rowsel,colsel);
            }

        }

    }
}
