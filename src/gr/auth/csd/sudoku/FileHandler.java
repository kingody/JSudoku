package gr.auth.csd.sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    public static Scanner openFile(String filename) {
        return openFile(new File(filename));
    }

    public static Scanner openFile(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + file.getName() + " not found.");
            System.exit(404);
        }
        return scanner;
    }
}
