package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.killer.Area;
import gr.auth.csd.sudoku.variants.killer.Index;
import gr.auth.csd.sudoku.variants.killer.KillerSudoku;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KillerWindow extends SudokuWindow {
    private ArrayList<Area> areas;
    private JLabel sum;

    public KillerWindow(String mode, KillerSudoku sud, char[] chars) {
        super(mode,sud,chars);
        ArrayList<Area> areas = sud.getAreas();
        System.out.println("Lol");

        for (Area area : areas) {
            ArrayList<Index> connectedCells = area.getCells();

            float hue = (float) Math.random();
            float saturation = (float) Math.random() * 0.5f + 0.2f;
            float brightness = (float) Math.random()* 0.5f + 0.5f;
            int rgb = Color.HSBtoRGB(hue, saturation, brightness);
            Color color = new Color(rgb);

            Index first = connectedCells.get(0);
            int row = first.getRow(), col = first.getColumn();

//            cells[row][col].setSum(Integer.toString(area.getSum()));
//            cells[row][col].getSumLabel().setVisible(true);
            cells[row][col].addLabel(Integer.toString(area.getSum()));

            for (Index position : connectedCells) {
                row = position.getRow();
                col = position.getColumn();

//                cells[row][col].getInputTextfield().setBackground(color);
                cells[row][col].setColor(color);
            }
        }

    }

}
