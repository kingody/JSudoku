package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuWindow extends JFrame {
    final Color fixedColor = Color.gray;
    final Color wrongColor = new Color(255, 28, 4);
    final Font font = new Font("Helvetica", Font.BOLD, 14);
    private ClassicSudoku sud;
    private char[] charSet;
    private int rowsel;
    private int colsel;
    private JButton hint = new JButton("Hint");


    public SudokuWindow(String mode, ClassicSudoku sud, char[] charSet) {
        super(mode);
        this.charSet = charSet;
        this.sud = sud;
        int size = sud.getGrid().length;
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        JPanel board2 = new JPanel(new GridLayout(size, size));
        JTextField[][] cells = new JTextField[size][size];

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
                    cells[i][j].addMouseListener(new MouseAdapter() {//finding the selected cell from mouse click
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            JTextField selected = (JTextField)e.getSource();
                            boolean flag = true;
                            int row = -69;
                            int col = -69;
                            for(int i=0;i<size && flag;i++){
                                for(int j=0;j<size && flag;j++){
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
                    });
                    cells[i][j].addKeyListener(new KeyAdapter() {//waiting for user input in mouse selected cell
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String input = cells[rowsel][colsel].getText();
                            if (input.isEmpty()) {
                                cells[rowsel][colsel].setBackground(Color.WHITE);
                                cells[rowsel][colsel].setText("");
                                sud.clearCell(rowsel,colsel);
                                return;
                            }

                            char value = input.charAt(0);

                            cells[rowsel][colsel].setBackground(Color.WHITE);
                            sud.clearCell(rowsel, colsel);

                            if (isValidLetter(value)) {
                                int numValue = getIndex(value);
                                boolean validMove = sud.setCell(rowsel, colsel, numValue);

                                Color color = validMove ? Color.WHITE : wrongColor;
                                cells[rowsel][colsel].setBackground(color);

                            }
                            else {
                                cells[rowsel][colsel].setBackground(Color.WHITE);
                                cells[rowsel][colsel].setText("");
                                sud.clearCell(rowsel,colsel);
                            }

                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                            for (char c : charSet)
                                if (c == e.getKeyChar()) {
                                    cells[rowsel][colsel].setText("");
                                    return;
                                }
                        }
                    });

                    cells[i][j].setText("");
                }

                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(font);
                board2.add(cells[i][j]);
            }
        }

        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame hint = new JFrame();
                JPanel hintPanel = new JPanel();
                JLabel hintLabel = new JLabel("The following are acceptable:  ");
                StringBuilder display = new StringBuilder();
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
            }
        });


        board2.setPreferredSize(new Dimension(630, 630));
        panel.add(board2);
        panel.add(hint);
        this.add(panel);
        setSize(700, 700);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

            }
        });
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
}
