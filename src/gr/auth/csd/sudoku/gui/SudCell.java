package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SudCell extends JPanel {
    private JLabel sum;
    private JTextField inputText;


    public SudCell(boolean kill) {
        setLayout(new BorderLayout());
        inputText = new JTextField();
        inputText.setDocument(new TextLimit(1));
        inputText.setHorizontalAlignment(JTextField.CENTER);

        sum = new JLabel();
        sum.setLabelFor(inputText);
        sum.setBorder(BorderFactory.createMatteBorder(0,0,1,1,Color.GRAY));
        sum.setMinimumSize(new Dimension(10,12));
        sum.setPreferredSize(new Dimension(10,12));
        sum.setMaximumSize(new Dimension(10,12));

        if(!kill)
        {
            sum.setVisible(false);
        }
        add(sum,BorderLayout.NORTH);
        add(inputText,BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


    }
    public JTextField getInputTextfield(){
        return inputText;
    }
    public void setInputText(String s){
        inputText.setText(s);
    }
    public void setSum(String s,Color color){
        sum.setText(s);
        setBackground(color);
    }
    public JLabel getSumLabel(){
        return  sum;
    }
}
