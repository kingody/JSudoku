package gr.auth.csd.sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is designed to create new Sudokus from files.
 */
public class SudokuBuilder {

    private Scanner openFile(String filename) {
        File file = new File("Sudokus/" + filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + filename + " not found.");
            System.exit(404);
        }

        return scanner;
    }

    public Sudoku sudokuFromFile(int size, String filename) {
        Scanner scanner = openFile("Classic/" + filename);

        int[][] cells = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = scanner.nextInt();
            }
        }

        return new Sudoku(cells);
    }

    public KillerSudoku killerSudokuFromFile(int size, String filename) {
        Scanner scanner = openFile("Killer/" + filename);
        ArrayList<Area> areas = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] info = line.split(":");
            String[] positions = info[1].split(",");

            ArrayList<Index> cells = new ArrayList<>();
            for (int i = 0; i < positions.length; i += 2) {
                int row = Integer.parseInt(positions[i]);
                int col = Integer.parseInt(positions[i + 1]);

                cells.add(new Index(row, col));
            }

            areas.add(new Area(Integer.parseInt(info[0]), cells));
        }

        return new KillerSudoku(size, areas);
    }
}
