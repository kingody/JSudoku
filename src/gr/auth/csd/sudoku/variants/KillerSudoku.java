package gr.auth.csd.sudoku.variants;

import gr.auth.csd.sudoku.utilities.FileHandler;
import gr.auth.csd.sudoku.utilities.Area;
import gr.auth.csd.sudoku.utilities.Index;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a Sudoku variation called Killer Sudoku.
 */
public class KillerSudoku extends ClassicSudoku {
    private final ArrayList<Area> areas;

    /**
     * Creates a new Killer Sudoku from a file. The format of each area in the file is sum : x,y x1,y1 etc.
     * @param size Size of grid
     * @param filename Name of file
     */
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

    /**
     * Conducts same checks as ClassicSudoku. It also checks if the move fills the area, the area's sum must be correct
     * @param row Row of Cell
     * @param col Column of Cell
     * @param value Numeric value whose validity is to be determined
     * @return True if it is valid, false otherwise
     */
    public boolean isValidMove(int row, int col, int value) {
        if (!super.isValidMove(row, col, value))
            return false;

        Index currentCell = new Index(row, col);
        int sum = value;
        for (Area area : areas) {
            ArrayList<Index> cells = area.getCells();
            boolean isFull = true;

            if (cells.contains(currentCell)) {
                for(Index cell : cells) {
                    if (cell.equals(currentCell))
                        continue;

                    int cellValue = getCell(cell.getRow(), cell.getColumn());

                    if (cellValue == 0)
                        isFull = false;

                    if (cellValue == value)
                        return false;

                    sum += cellValue;
                }

                if (isFull)
                    return sum == area.getSum();
                else
                    return sum < area.getSum();
            }
        }
        return false;
    }
}
