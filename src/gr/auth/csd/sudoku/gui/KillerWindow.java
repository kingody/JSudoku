package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;
import gr.auth.csd.sudoku.variants.killer.Area;
import gr.auth.csd.sudoku.variants.killer.Index;
import gr.auth.csd.sudoku.variants.killer.KillerSudoku;

import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.util.ArrayList;

public class KillerWindow extends SudokuWindow {
    private Color[] colors;
    private KillerSudoku sud;
    private ArrayList<Area>areas;
    private ArrayList<Index>connectedCells;
    private JLabel sum;

    public KillerWindow(String mode, KillerSudoku sud, char[] chars) {
        super(mode,sud,chars);
        this.sud = sud;
        ArrayList<Area> areas = new ArrayList<>(sud.getAreas());
        ArrayList<Index> connectedCells;
        colors = new Color[11];
        colors[0] = Color.cyan;
        colors[1] = Color.green;
        colors[2] = Color.orange;
        colors[3] = Color.magenta;
        colors[4] = new Color(0x475035);
        colors[5] = Color.GRAY;
        colors[6] = Color.WHITE;
        colors[7] = new Color(189, 160, 25);
        colors[8] = new Color(255, 138, 231);
        colors[9] = new Color(79, 77, 8);
        colors[10] = new Color(30, 0, 255);
        System.out.println("Lol");

        int i = 0;
        for (Area area : areas) {
            int j=0;
            connectedCells = new ArrayList<>(area.getCells());
            double randomizer = Math.random();
            double sature = Math.random()*(0.7-0.2)+0.2;
            double brightness = Math.random()*(1-0.5)+0.5;
            int rgb = Color.HSBtoRGB((float) randomizer,(float)sature,(float) brightness);
            Color color = new Color(rgb);
            //Color color = new Color((int)(Math.random() * 0x1000000));
            for (Index coord : connectedCells) {
                int row = coord.getRow();
                int col = coord.getColumn();
                cells[row][col].getInputTextfield().setBackground(color);
                cells[row][col].setCellColor(color);
                if (j == 0) {
                    cells[row][col].getSumLabel().setVisible(true);
                    cells[row][col].setSum(Integer.toString(area.getSum()), color);
                }


                j++;
            }
            j=0;
            i++;
        }

    }

}
