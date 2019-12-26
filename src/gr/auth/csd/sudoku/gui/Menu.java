package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;

import javax.swing.*;

public class Menu extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JButton classic;
    private JButton killer;
    private JButton settings;
    private JButton duidoku;
    private JPanel menu;

    public Menu() {
        ClassicSudoku sudoku = new ClassicSudoku(9,"classic1.txt");

        add(panel);
        classic.addActionListener(e -> new SudokuWindow("Classic", sudoku));
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
