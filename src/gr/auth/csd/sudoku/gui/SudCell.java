package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.*;

public class SudCell extends JPanel {
    private JLabel sum;
    private JTextField inputText;
    private Color cellColor = Color.WHITE;
    private static final Color wrongColor = new Color(255, 28, 4);
    private static final Font font = new Font("Arial", Font.BOLD, 15);


    public SudCell() {
        setLayout(new BorderLayout());
        inputText = new JTextField();
        inputText.setFont(font.deriveFont(Font.BOLD, 18));
        inputText.setBorder(BorderFactory.createEmptyBorder());
        inputText.setDocument(new TextLimit(1));
        inputText.setForeground(Color.BLACK);
        inputText.setHorizontalAlignment(JTextField.CENTER);

        add(inputText,BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void addLabel(String text) {
        sum = new JLabel(text);
        sum.setFont(font);
        sum.setForeground(Color.BLACK);
        sum.setMinimumSize(new Dimension(10,15));
        sum.setPreferredSize(new Dimension(10,15));
        sum.setMaximumSize(new Dimension(10,15));
        add(sum,BorderLayout.NORTH);

        sum.setVisible(true);
    }

    public JTextField getInputTextField(){
        return inputText;
    }

    public void setInputText(String s){
        inputText.setText(s);
    }

    public void setColor(Color cellColor) {
        this.cellColor = cellColor;
        setBackground(cellColor);
        inputText.setBackground(cellColor);
    }

    public void setWarning(boolean flag) {
        if (flag) {
        setBackground(wrongColor);
        inputText.setBackground(wrongColor);
        } else {
            setColor(cellColor);
        }
    }


    public Color getCellColor(){ return cellColor; }
}
