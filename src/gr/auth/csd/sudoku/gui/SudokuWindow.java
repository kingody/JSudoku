package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

public class SudokuWindow extends JFrame {
    final Color fixedColor = Color.gray;
    final Font font = new Font("Helvetica", Font.BOLD, 14);
    private Sudoku sud;
    JTextField[][] cells;


    public SudokuWindow(String mode, Sudoku sud) {
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
                cells[i][j].setDocument(new Textlimit(1));
                if (sud.getCell(i, j) != 0) {
                    cells[i][j].setText(Integer.toString(sud.getCell(i, j)));
                    cells[i][j].setEditable(false);
                    cells[i][j].setBackground(fixedColor);
                } else {
                    cells[i][j].setText("");
                }
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(font);
                board2.add(cells[i][j]);
            }
            System.out.println(cells[i][0].getText());
        }


        board2.setPreferredSize(new Dimension(650, 650));
        panel.add(board2);
        this.add(panel);
        setVisible(true);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


}
