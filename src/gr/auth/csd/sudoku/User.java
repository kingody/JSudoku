package gr.auth.csd.sudoku;

import java.util.HashSet;

public class User {
    private String username;
    private static HashSet<String> userList = new HashSet<>();
    private HashSet<String> solvedSudokus;
    private int wins, losses;

    private User(String username) {
        this.username = username;
        userList.add(username);

        solvedSudokus = new HashSet<>();
        wins = 0;
        losses = 0;
    }

    public static User newUser(String username) {
        if (userList.contains(username)) {
            return null;
        }

        return  new User(username);
    }

    public String getUsername() {
        return username;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public boolean hasSolved(String filename) {
        return solvedSudokus.contains(filename);
    }

    public void addToSolved(String filename) {
        solvedSudokus.add(filename);
    }
}
