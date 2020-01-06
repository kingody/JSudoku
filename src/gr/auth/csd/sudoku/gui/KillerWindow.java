package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.killer.Area;
import gr.auth.csd.sudoku.variants.killer.Index;
import gr.auth.csd.sudoku.variants.killer.KillerSudoku;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class KillerWindow extends SudokuWindow {
    private ArrayList<Area> areas;


    public KillerWindow(String mode, KillerSudoku sud, char[] chars, ResourceBundle bundle) {
        super(mode,sud,chars,bundle);
        areas = sud.getAreas();
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

            cells[row][col].addLabel(Integer.toString(area.getSum()));

            for (Index position : connectedCells) {
                row = position.getRow();
                col = position.getColumn();

                cells[row][col].setColor(color);
            }
        }

    }

}
