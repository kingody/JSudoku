package gr.auth.csd.sudoku.variants.killer;

import gr.auth.csd.sudoku.FileHandler;
import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a Sudoku variation called Killer Sudoku.
 */
public class KillerSudoku extends ClassicSudoku {
    private ArrayList<Area> areas;

    /**
     * Creates a new Killer Sudoku from a given size and list of areas.
     * @param size The Sudoku grid size
     */
    public KillerSudoku(int size, ArrayList<Area> areas) {
        super(size);
        this.areas = areas;
    }

    public KillerSudoku(int size, String filename) {
        super(size);
        areas = new ArrayList<>();

        Scanner scanner = FileHandler.openFile(fileDir + "Killer/" + filename);

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
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public boolean isValidMove(int row, int col, int value) {
        if (!super.isValidMove(row, col, value))
            return false;

        Index currentCell = new Index(row, col);
        int sum = value;
        for (Area area : areas) {
            ArrayList<Index> cells = area.getCells();

            if (cells.contains(currentCell)) {
                for(Index cell : cells) {
                    if (cell.equals(currentCell))
                        continue;

                    int cellValue = getCell(cell.getRow(), cell.getColumn());

                    if (cellValue == value)
                        return false;

                    sum += cellValue;
                }

                return sum <= area.getSum();
            }
        }
        return false;
    }
}
