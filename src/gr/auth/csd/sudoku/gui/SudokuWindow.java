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
    private int rowsel;
    private int colsel;


    public SudokuWindow(String mode, ClassicSudoku sud) {
        super(mode);
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
                if (sud.getCell(i, j) != 0) {//cells from file are initiliazed to corresponding value and not to be edited
                    cells[i][j].setText(Integer.toString(sud.getCell(i, j)));
                    cells[i][j].setEditable(false);
                    cells[i][j].setBackground(fixedColor);
                } else {//empty cells
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
                            boolean isDig = true;
                            for (char c : input.toCharArray()) {//checking to see if user entered a digit
                                isDig = Character.isDigit(c);
                            }
                            if (isDig && !cells[rowsel][colsel].getText().isEmpty()) {
                                cells[rowsel][colsel].setBackground(Color.WHITE);
                                sud.clearCell(rowsel, colsel);
                                int actual = Integer.parseInt(input);//converting String input to integer for further processing
                                if (!sud.isValidMove(rowsel, colsel, actual)) {
                                    System.out.println("Potato");
                                    cells[rowsel][colsel].setBackground(wrongColor);
                                    //cells[rowsel][colsel].setText("");

                                }
                                else{
                                    System.out.println("Nice");
                                    sud.setCell(rowsel,colsel,actual);
                                    cells[rowsel][colsel].setBackground(Color.WHITE);
                                }
                            }
                            else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                                cells[rowsel][colsel].setBackground(Color.WHITE);
                                sud.clearCell(rowsel,colsel);
                            }
                            else{
                                System.out.println("Invalid Input");
                                cells[rowsel][colsel].setText("");
                            }
                        }
                    });
                    cells[i][j].setText("");
                   //cells[i][j].setFocusable(true);


                }
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(font);
                board2.add(cells[i][j]);
            }
        }


        board2.setPreferredSize(new Dimension(630, 630));
        panel.add(board2);
        this.add(panel);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
