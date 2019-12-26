package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.killer.KillerSudoku;
import gr.auth.csd.sudoku.classic.ClassicSudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGui extends JFrame {
    private JButton normal;
    private JButton killer;
    private JButton duidoku;
    private JButton settings;

    public SudokuGui(){
        super("Sudoku");
        ClassicSudoku classic = new ClassicSudoku(9,"classic1.txt");

        KillerSudoku[] killers = new KillerSudoku[10];
        JPanel panel = new JPanel();
        JLabel text =  new JLabel("Welcome to Sudoku! Please choose one of the following or close the app");
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        normal = new JButton("Classic");
        killer = new JButton("Killer");
        duidoku = new JButton("Play Duidoku");
        settings = new JButton("Settings");


       /* int[][] grid = new int[9][9];
        for(int i=0;i<grid.length;i++)
            for(int j= 0;j<grid.length;j++)
                grid[i][j] = 0;*/

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SudokuWindow("Classic",classic);

            }
        });

       /* killer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SudokuWindow("Killer Sudoku",grid);

            }
        });

        duidoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SudokuWindow("Duidoku",grid);
            }
        });*/

        panel.add(text);
        panel.add(normal);
        panel.add(killer);
        panel.add(duidoku);
        panel.add(settings);
        this.add(panel);

        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
