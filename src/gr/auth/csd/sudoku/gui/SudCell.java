package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;

public class SudCell extends JPanel {
    private JLabel sum;
    private JTextField inputText;
    protected Color fixedColor = Color.gray;



    public SudCell(int row,int col, Sudoku sudoku, char[] charSet){
        setLayout(new BorderLayout());
        inputText = new JTextField();
        inputText.setDocument(new TextLimit(1));
        if (sudoku.getCell(row, col) != 0) {
            //cells from file are initiliazed to corresponding value and not to be edited
            String num = String.valueOf(charSet[sudoku.getCell(row,col)]);
            inputText.setText(num);
            //cells[i][j].inputText.setText(num);
            setBackground(fixedColor);
            inputText.setEditable(false);
        }
        inputText.setHorizontalAlignment(JTextField.CENTER);



        sum = new JLabel();
        add(sum,BorderLayout.NORTH);
        add(inputText,BorderLayout.CENTER);

    }

}
