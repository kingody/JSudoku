package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.*;

public class SudCell extends JPanel {
    private JLabel sum;
    private JTextField inputText;
    private Color cellColor =Color.WHITE ;
    private static final Font font = new Font("Serif", Font.BOLD, 15);


    public SudCell() {
        setLayout(new BorderLayout());
        inputText = new JTextField();
        inputText.setBorder(BorderFactory.createEmptyBorder());
        inputText.setDocument(new TextLimit(1));
        inputText.setHorizontalAlignment(JTextField.CENTER);

        sum = new JLabel();
        sum.setFont(font);
        sum.setForeground(new Color(0,0,0));
//        sum.setBorder(BorderFactory.createMatteBorder(0,0,1,1,Color.GRAY));
        sum.setMinimumSize(new Dimension(10,15));
        sum.setPreferredSize(new Dimension(10,15));
        sum.setMaximumSize(new Dimension(10,15));
        sum.setVisible(false);
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

    public void setSum(String s){
        sum.setText(s);

    }
    public JLabel getSumLabel(){
        return  sum;
    }

    public void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
        setBackground(cellColor);
    }

    public Color getCellColor(){return cellColor;}
}
