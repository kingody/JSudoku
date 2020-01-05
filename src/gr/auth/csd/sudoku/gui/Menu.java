package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;
import gr.auth.csd.sudoku.variants.duidoku.Duidoku;
import gr.auth.csd.sudoku.variants.killer.KillerSudoku;

import javax.swing.*;

public class Menu extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JButton classic;
    private JButton killer;
    private JButton settings;
    private JButton duidoku;
    private JPanel menu;
    private JButton userButton;

    private Settings settingsWindow = new Settings(this);


    private final char[][] charSet = {
            {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
            {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'},
            {' ', 'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ', 'Ι'}
    };

    private char[] current = charSet[0];


    public Menu() {
        add(panel);

        ClassicSudoku sudoku = new ClassicSudoku(9,"classic10.txt");
        KillerSudoku kil = new KillerSudoku(9,"killer10.txt");
        Duidoku dui = new Duidoku(4);

        classic.addActionListener(click -> new SudokuWindow("Classic", sudoku, current));
        killer.addActionListener(click -> new KillerWindow("killer", kil, current));
        duidoku.addActionListener(click->new DuidokuWindow("Duidoku",dui,current));
        settings.addActionListener(click -> settingsWindow.showWindow());
        userButton.addActionListener(click-> new UserWindow());




        setTitle("Sudoku");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setCharSet(int index) {
        if (index >= 0 && index < charSet.length)
            current = charSet[index];
    }

    public static void main(String[] args) {
        JFrame frame = new Menu();
        frame.setVisible(true);
    }
}
