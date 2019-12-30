package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;
import gr.auth.csd.sudoku.variants.killer.KillerSudoku;
import org.w3c.dom.ls.LSOutput;

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

    private SettingsWindow settingsWindow = new SettingsWindow(this);


    private final char[][] charSet = {
            {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
            {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'},
            {' ', 'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ', 'Ι'}
    };

    private char[] current = charSet[0];


    public Menu() {
        add(panel);


        classic.addActionListener(click -> {
            ClassicSudoku sudoku = new ClassicSudoku(9,"classic1.txt");
//            if (wordoku){
//                new SudokuWindow("Classic", sudoku, charSet[1]);
//                System.out.println("Wordoku");
//            }
//            else{
//                new SudokuWindow("Classic", sudoku, charSet[0]);
//            }
            new SudokuWindow("Classic", sudoku, current);
        });
        KillerSudoku kil = new KillerSudoku(9,"killer1.txt");
        System.out.println("lol");

        killer.addActionListener(click -> new KillerWindow("killer",kil,numbers));

        settings.addActionListener(click -> settingsWindow.showWindow());

        setTitle("Sudoku");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setWordoku(boolean b){
        wordoku = b;
    }

    public void setCharSet(int index) {
        if (index > 0 && index < charSet.length)
            current = charSet[index];
    }

    public static void main(String[] args) {
        JFrame frame = new Menu();
        frame.setVisible(true);
    }
}
