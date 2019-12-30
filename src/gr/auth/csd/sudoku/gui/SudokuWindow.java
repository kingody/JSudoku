package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represents the GUI on which the user plays the classic version of sudoku or wordoku
 */

public class SudokuWindow extends JFrame {


    protected Color fixedColor = Color.gray;
    protected final Color wrongColor = new Color(255, 28, 4);
    protected final Font font = new Font("Helvetica", Font.BOLD, 14);
    private ClassicSudoku sud;
    protected char[] charSet;
    protected int rowsel;
    protected int colsel;
    protected JButton hint = new JButton("Hint");
    protected JTextField[][] cells;
    protected MyKeyListener a;
    protected JPanel board2;

    /**
     * The constructor initializes the GUI grid, sets the properties of each cell (color, initial values, editability, mouse and keylisteners etc)
     * @param mode The mode played (classic,killer or wordoku)
     * @param sud Sudoku object
     * @param charSet Array with the characters used in game. Used to distinguish between wordoku and sudoku
     */

    public SudokuWindow(String mode, ClassicSudoku sud, char[] charSet) {
        super(mode);
        this.charSet = charSet;
        this.sud = sud;
        int size = sud.getGrid().length;
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        board2 = new JPanel(new GridLayout(size, size));
        cells = new JTextField[size][size];
        initializeGrid();
        hint.addActionListener(click -> {
            JFrame hint = new JFrame();
            JPanel hintPanel = new JPanel();
            JLabel hintLabel = new JLabel("The following are acceptable:  ");
            StringBuilder display = new StringBuilder();
            System.out.println("");
            for (char c: charSet) {
                if(sud.isValidMove(rowsel,colsel,getIndex(c))){
                    display.append(c).append(" ");
                }
            }
            JLabel hints = new JLabel(display.toString());
            hintPanel.add(hintLabel);
            hintPanel.add(hints);
            hint.add(hintPanel);
            hint.setSize(200,100);
            hint.setLocationRelativeTo(null);
            hint.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            hint.setResizable(false);
            hint.setVisible(true);
        });


        board2.setPreferredSize(new Dimension(630, 630));
        panel.add(board2);
        panel.add(hint);
        this.add(panel);
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

    public boolean isValidLetter(char c) {
        return getIndex(c) > 0;
    }

    public void initializeGrid(){
        int size = sud.getGrid().length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setDocument(new TextLimit(1));
                if (sud.getCell(i, j) != 0) {
                    //cells from file are initiliazed to corresponding value and not to be edited
                    String num = String.valueOf(charSet[sud.getCell(i, j)]);
                    cells[i][j].setText(num);
                    cells[i][j].setEditable(false);
                    cells[i][j].setBackground(fixedColor);

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
                board2.add(cells[i][j]);
            }
        }

    }

    public class MyMouseListener implements MouseListener{


        @Override
        public void mouseClicked(MouseEvent e) {
            JTextField selected = (JTextField)e.getSource();
            boolean flag = true;
            int row = -69;
            int col = -69;
            for(int i=0;i<sud.getGrid().length && flag;i++){
                for(int j=0;j<sud.getGrid().length && flag;j++){
                    if(selected.equals(cells[i][j])){
                        flag = false;
                        row = i;
                        col = j;
                    }
                }
            }
            rowsel = row;
            colsel = col;
            System.out.println("You selected :"+rowsel+" "+colsel);
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
                sud.clearCell(rowsel,colsel);
                return;
            }

            char value = input.charAt(0);

            cells[rowsel][colsel].setBackground(cellColor);
            sud.clearCell(rowsel, colsel);

            if (isValidLetter(value)) {
                int numValue = getIndex(value);
                boolean validMove = sud.setCell(rowsel, colsel, numValue);

                Color color = validMove ? cellColor : wrongColor;
                cells[rowsel][colsel].setBackground(color);

            }
            else {
                cells[rowsel][colsel].setBackground(cellColor);
                cells[rowsel][colsel].setText("");
                sud.clearCell(rowsel,colsel);
            }

        }
    }
}
