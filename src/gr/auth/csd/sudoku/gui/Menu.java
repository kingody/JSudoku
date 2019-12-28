package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JButton classic;
    private JButton killer;
    private JButton settings;
    private JButton duidoku;
    private boolean wordoku;
    private JPanel menu;
    private JButton userButton;


    private final char[] numbers = {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                         letters = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

    public Menu() {
        add(panel);

        ClassicSudoku sudoku = new ClassicSudoku(9,"classic1.txt");
        classic.addActionListener(click -> new SudokuWindow("Classic", sudoku, numbers));
        settings.addActionListener(click -> new SettingsWindow());


    }
    public void setWordoku(boolean b){
        wordoku = b;
    }

    public static void main(String[] args) {
        JFrame frame = new Menu();
        frame.setTitle("Sudoku");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
