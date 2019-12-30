package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;
import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represents the GUI on which the user plays the classic version of Sudoku.
 */
public class SudokuWindow extends JFrame {
    protected Color fixedColor = Color.gray;
    protected final Color wrongColor = new Color(255, 28, 4);
    protected final Font font = new Font("Helvetica", Font.BOLD, 14);
    private Sudoku sudoku;
    protected char[] charSet;
    protected int rowsel;
    protected int colsel;
    protected JButton hint;
    protected JTextField[][] cells;
    protected MyKeyListener a;
    protected JPanel grid;

    /**
     * The constructor initializes the GUI grid, sets the properties of each cell (color, initial values, editability, mouse and keylisteners etc)
     * @param title The mode played (classic,killer or wordoku)
     * @param sudoku Sudoku object
     * @param charSet Array with the characters used in game. Used to distinguish between Wordoku and Sudoku
     */
    public SudokuWindow(String title, Sudoku sudoku, char[] charSet) {
        super(title);
        this.sudoku = sudoku;
        this.charSet = charSet;
        int size = sudoku.getSize();

        JPanel background = new JPanel();
        background.setBackground(Color.DARK_GRAY);
        grid = new JPanel(new GridLayout(size, size));
        cells = new JTextField[size][size];
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

        grid.setPreferredSize(new Dimension(630, 630));
        background.add(grid);
        background.add(hint);
        this.add(background);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public int getIndex(char c) {
        for (int i = 0; i < charSet.length; i++) {
            if (c == charSet[i])
                return i;
        }
        return -1;
    }

    public boolean isValidChar(char c) {
        int index = getIndex(c);
        return index > 0 && index <= sudoku.getSize();
    }

    public void initializeGrid(){
        int size = sudoku.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setDocument(new TextLimit(1));

                if (sudoku.getCell(i, j) != 0) {
                    //cells from file are initiliazed to corresponding value and not to be edited
                    String num = String.valueOf(charSet[sudoku.getCell(i, j)]);
                    cells[i][j].setText(num);
                    cells[i][j].setBackground(fixedColor);
                    cells[i][j].setEditable(false);
                }
                else {
                    //empty cells
                    cells[i][j].addMouseListener(new MyMouseListener()); //finding the selected cell from mouse click
                    MyKeyListener a = new MyKeyListener();
                    cells[i][j].addKeyListener(a);//waiting for user input in mouse selected cell
                    cells[i][j].setText("");
                }

                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(font);
                grid.add(cells[i][j]);
            }
        }
    }

    public class MyMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            JTextField selected = (JTextField)e.getSource();
            int size = sudoku.getSize();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (selected.equals(cells[i][j])) {
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
        protected Color cellColor;

        public void setCellColor(Color cellColor) {
            this.cellColor = cellColor;
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            for (char c : charSet)
                if (c == e.getKeyChar()) {
                    cells[rowsel][colsel].setText("");
                    return;
                }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            String input = cells[rowsel][colsel].getText();
            if (input.isEmpty()) {
                cells[rowsel][colsel].setBackground(cellColor);
                cells[rowsel][colsel].setText("");
                sudoku.clearCell(rowsel,colsel);
                return;
            }

            char value = input.charAt(0);

            cells[rowsel][colsel].setBackground(cellColor);
            sudoku.clearCell(rowsel, colsel);

            if (isValidChar(value)) {
                int numValue = getIndex(value);
                boolean validMove = sudoku.setCell(rowsel, colsel, numValue);

                Color color = validMove ? cellColor : wrongColor;
                cells[rowsel][colsel].setBackground(color);

            }
            else {
                cells[rowsel][colsel].setBackground(cellColor);
                cells[rowsel][colsel].setText("");
                sudoku.clearCell(rowsel,colsel);
            }

        }
    }
}
