package gr.auth.csd.sudoku;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class User {
    private static final String dir = "Users/";
    private String username;
    private HashSet<String> solvedSudokus;
    private int wins, losses;

    private User(String username) {
        this.username = username;

        solvedSudokus = new HashSet<>();
        wins = 0;
        losses = 0;
        saveToFile();
    }

    private User(File file) {
        Scanner scanner = FileHandler.openFile(file);

        username = scanner.nextLine();
        wins = Integer.parseInt(scanner.nextLine());
        losses = Integer.parseInt(scanner.nextLine());

        solvedSudokus = new HashSet<>();
        while (scanner.hasNext()) {
            solvedSudokus.add(scanner.nextLine());
        }
    }

    public static User newUser(String username) {
        File file = new File(dir + username + ".txt");
        if (file.exists()) {
            System.out.println("User " + username + " already exists.");
            return null;
        }
        return new User(username);
    }

    public static User loadFromFile(String username) {
        File file = new File(dir + username + ".txt");

        if (file.exists())
            return new User(file);

        System.out.println("User " + username + " does not exist.");
        return null;
    }

    public void saveToFile() {
        String path = dir + username + ".txt";

        try {
            //Overwrite old file if it exists
            File file = new File(path);
            file.delete();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(this.toString());
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Can't create user file.");
            System.exit(-1);
        }
    }

    public String getUsername() {
        return username;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        wins++;
    }

    public int getLosses() {
        return losses;
    }

    public void incrementLosses() {
        losses++;
    }

    public boolean hasSolved(String filename) {
        return solvedSudokus.contains(filename);
    }

    public void addToSolved(String filename) {
        solvedSudokus.add(filename);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(username)
                .append('\n')
                .append(wins)
                .append('\n')
                .append(losses)
                .append('\n');

        for (String sudoku : solvedSudokus) {
            str.append(sudoku).append('\n');
        }

        return str.toString();
    }
}
