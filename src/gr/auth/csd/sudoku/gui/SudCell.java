package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a sudoku cell for the GUI. It contains a JLabel sum (for the Killer variant) and a JTextField inputText for the input
 */

public class SudCell extends JPanel {
    private static final Color warningColor = new Color(255, 28, 4);
    private static final Font font = new Font("Arial", Font.BOLD, 15);

//    private JLabel label;
    private JTextField inputText;
    private Color cellColor = Color.WHITE;

    /**
     * In the constructor the layout and border of each SudCell are set as well as the properties(document,font etc.) of inputText which is then added to the cell
     */
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

    /**
     * This method adds the sum Label to the cell, after having set its properties(size,font etc.)
     * @param text The label's text
     */
    public void addLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.BLACK);
        label.setMinimumSize(new Dimension(10,15));
        label.setPreferredSize(new Dimension(10,15));
        label.setMaximumSize(new Dimension(10,15));
        add(label,BorderLayout.NORTH);

        label.setVisible(true);
    }

    public JTextField getTextField(){
        return inputText;
    }

    public void setText(String s){
        inputText.setText(s);
    }

    /**
     * Sets the background of both the SudCell and its inputText to cellColor
     * @param cellColor The color that the cell will be set to
     */
    public void setColor(Color cellColor) {
        this.cellColor = cellColor;
        setBackground(cellColor);
        inputText.setBackground(cellColor);
    }

    /**
     * This method is used to set the color of the cell based on the value of flag
     * If true, both SudCell's and inputText's color is set to warningColor, else the SedCell's color is restored
     * @param flag changes the color if true, restores it if false
     */
    public void setWarning(boolean flag) {
        if (flag) {
            setBackground(warningColor);
            inputText.setBackground(warningColor);
        } else {
            setColor(cellColor);
        }
    }
}
