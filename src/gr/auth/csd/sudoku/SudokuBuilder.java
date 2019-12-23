package gr.auth.csd.sudoku;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is designed to create new Sudokus from files.
 */
public class SudokuBuilder {
    private static final String dir = "Sudokus/";

    public Sudoku sudokuFromFile(int size, String filename) {
        Scanner scanner = FileHandler.openFile(dir + "Classic/" + filename);

        int[][] cells = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = scanner.nextInt();
            }
        }

        return new Sudoku(cells);
    }

    public KillerSudoku killerSudokuFromFile(int size, String filename) {
        Scanner scanner = FileHandler.openFile(dir + "Killer/" + filename);
        ArrayList<Area> areas = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] areaInfo = line.split(" : ");
            String[] positions = areaInfo[1].split(" ");

            ArrayList<Index> cells = new ArrayList<>();
            for (String position : positions) {
                String[] pos = position.split(",");
                int row = Integer.parseInt(pos[0]);
                int col = Integer.parseInt(pos[1]);

                cells.add(new Index(row, col));
            }

            areas.add(new Area(Integer.parseInt(areaInfo[0]), cells));
        }

        return new KillerSudoku(size, areas);
    }
}
