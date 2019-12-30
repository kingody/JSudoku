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


    private final char[] numbers = {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                         letters = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};


    public Menu() {
        add(panel);


        classic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassicSudoku sudoku = new ClassicSudoku(9,"classic1.txt");
                if (wordoku){
                    new SudokuWindow("Classic",sudoku,letters);
                    System.out.println("Wordoku");
                }
                else{
                    new SudokuWindow("Classic",sudoku,numbers);
                }
            }
        });
        KillerSudoku kil = new KillerSudoku(9,"killer1.txt");

        killer.addActionListener(click -> new KillerWindow("killer",kil,numbers));

        settings.addActionListener(click -> new SettingsWindow(this));


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
