package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;
import gr.auth.csd.sudoku.variants.killer.Area;
import gr.auth.csd.sudoku.variants.killer.Index;
import gr.auth.csd.sudoku.variants.killer.KillerSudoku;
import java.util.Random;
import java.awt.*;
import java.util.ArrayList;

public class KillerWindow extends SudokuWindow {
    private Color[] colors;

    public KillerWindow(String mode, KillerSudoku sud, char[] chars) {
        super(mode, sud, chars);
        ArrayList<Area> areas = new ArrayList<>(sud.getAreas());
        ArrayList<Index> connectedCells;
        colors = new Color[11];
        colors[0] = Color.cyan;
        colors[1] = Color.green;
        colors[2] = Color.orange;
        colors[3] = Color.magenta;
        colors[4] = new Color(0x475035);
        colors[5] = Color.lightGray;
        colors[6] = Color.WHITE;
        colors[7] = Color.yellow;
        colors[8] = new Color(255, 138, 231);
        colors[9] = new Color(79, 77, 8);
        colors[10] = new Color(30, 0, 255);
        int i=0;
        for(Area area : areas){
            connectedCells = new ArrayList<>(area.getCells());
            for(Index coord: connectedCells){
                int row = coord.getRow();
                int col = coord.getColumn();
                cells[row][col].setBackground(colors[i%11]);
            }
            i++;
        }




    }
}
