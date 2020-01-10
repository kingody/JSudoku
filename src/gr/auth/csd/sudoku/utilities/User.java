package gr.auth.csd.sudoku.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * This class represents a player and records their stats
 */
public class User {
    //Save file directory
    private static final String dir = "Users/";
    private final String username;
    private HashSet<String> solvedSudokus;
    private int wins, losses;

    private static User current = null;

    /**
     * Creates a new user and saves them to disk.
     * @param username The user's nickname.
     */
    private User(String username) {
        this.username = username;

        solvedSudokus = new HashSet<>();
        wins = 0;
        losses = 0;
        saveToFile();
    }

    /**
     * Loads an existing user from a file.
     * @param file The user file.
     */
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

    public static User getCurrentUser() {
        return current;
    }

    public static void setCurrentUser(User user) {
        current = user;
    }

    /**
     * Creates a new user if the username is not taken.
     * @param username The user's nickname.
     * @return null if the user exists, else a new User object.
     */
    public static User newUser(String username) {
        File file = new File(dir + username + ".txt");
        if (file.exists())
            return null;

        return new User(username);
    }

    /**
     * Used to load every user stored
     * @return ArrayList of Users stored in the disk
     */
    public static ArrayList<User> loadAll() {
        File directory = new File(dir);
        File[] files = directory.listFiles();

        if (files == null)
            return null;

        ArrayList<User> users = new ArrayList<>();
        for (File file : files) {
            if (file == null || file.isDirectory())
                continue;

            User user = loadUser(file);
            if (user != null)
                users.add(user);
        }

        return users;
    }

    /**
     * Loads a user from disk.
     * @param username The user's nickname.
     * @return null if the user does not exist, else a User object.
     */
    public static User loadUser(String username) {
        File file = new File(dir + username + ".txt");

        User user = loadUser(file);
        if (user == null)
            System.out.println("User " + username + " does not exist.");

        return user;
    }

    private static User loadUser(File file) {
        if (file.exists())
            return new User(file);

        return null;
    }

    /**
     * Saves the User's data to a file
     */
    public void saveToFile() {
        String path = dir + username + ".txt";

        try {
            File file = new File(path);

            FileWriter writer = new FileWriter(file);
            writer.write(this.toString());
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Can't create/open user file.");
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
        saveToFile();
    }

    public int getLosses() {
        return losses;
    }

    public void incrementLosses() {
        losses++;
        saveToFile();
    }

    /**
     * Checks to see if the Sudoku stored in filename.txt has been solved by the user
     * @param filename the name of Sudoku file
     * @return true if it has been solved, false otherwise
     */
    public boolean hasSolved(String filename) {
        return solvedSudokus.contains(filename);
    }

    public void addToSolved(String filename) {
        solvedSudokus.add(filename);
        saveToFile();
    }

    /**
     * Overriding the toString method to best fit the file handling
     * @return the text that contains all the necessary information to create a new User
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(username).append('\n')
                .append(wins).append('\n')
                .append(losses).append('\n');

        for (String sudoku : solvedSudokus) {
            str.append(sudoku).append('\n');
        }

        return str.toString();
    }
}
