package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.utilities.User;
import gr.auth.csd.sudoku.utilities.locale.Language;
import gr.auth.csd.sudoku.utilities.locale.Localization;
import gr.auth.csd.sudoku.gui.variants.ClassicWindow;
import gr.auth.csd.sudoku.gui.variants.DuidokuWindow;
import gr.auth.csd.sudoku.gui.variants.KillerWindow;
import gr.auth.csd.sudoku.variants.ClassicSudoku;
import gr.auth.csd.sudoku.variants.Duidoku;
import gr.auth.csd.sudoku.variants.KillerSudoku;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * This class represents our main menu, which contains our main method
 */
public class Menu extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JButton classic;
    private JButton killer;
    private JButton settings;
    private JButton duidoku;
    private JPanel menu;
    private JButton userButton;
    private JLabel currentUser;
    private Language lang;

    private boolean isWordoku = false;
    private Settings settingsWindow;

    private final char[] numberSet = {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private char[] charSet;

    /**
     * Adds all the components to the panel, initializes the puzzles randomly, adds the action listeners to the buttons and sets the properties of Menu
     */
    public Menu() {
        add(panel);

        localize(Locale.getDefault());

        Random random = new Random();

        settingsWindow = new Settings(this);
        classic.addActionListener(click -> {
            String filename = getRandomSudoku("Classic");
            if (filename == null)
                filename = "classic1.txt";

            ClassicSudoku sudoku = new ClassicSudoku(9, filename);
            new ClassicWindow(sudoku, charSet);
        });

        killer.addActionListener(click -> {
            String filename = getRandomSudoku("Killer");
            if (filename == null)
                filename = "killer1.txt";

            KillerSudoku sudoku = new KillerSudoku(9, filename);
            new KillerWindow(sudoku, charSet);
        });

        duidoku.addActionListener(click-> new DuidokuWindow(new Duidoku(4), charSet));
        settings.addActionListener(click -> settingsWindow.showWindow());
        userButton.addActionListener(click-> new UserWindow(this));

        setSize(300, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Sets Menu's user to user and the text of label currentUser
     * @param user User object
     */
    public void setUser(User user) {
        User.setCurrentUser(user);
        currentUser.setText(lang.getString("currentUser") + user.getUsername());
        currentUser.setVisible(true);
    }

    public void removeUser() {
        User.setCurrentUser(null);
        currentUser.setVisible(false);
    }


    public void setWordoku(boolean set) {
        isWordoku = set;
        setCharSet();
    }

    public void localize(Locale locale) {
        lang = Localization.localize(locale);
        setText();
        setCharSet();
    }

    /**
     * This function sets all the language-sensitive properties of our components
     */
    private void setText() {
        setTitle(lang.getString("menuTitle"));

        title.setText(lang.getString("greeting"));
        classic.setText(lang.getString("classic"));
        killer.setText(lang.getString("kill"));
        duidoku.setText(lang.getString("duidoku"));
        settings.setText(lang.getString("settings"));
        userButton.setText(lang.getString("selectUser"));
        currentUser.setText(lang.getString("currentUser"));

        User user = User.getCurrentUser();
        if (user != null)
            setUser(user);
    }

    /**
     * Sets the characters for the game based on the value of isWordoku boolean
     */
    private void setCharSet() {
            charSet = isWordoku ? lang.getCharSet() : numberSet;
    }

    private ArrayList<String> getUnplayedSudokus(String directory) {
        File[] files = new File(directory).listFiles();
        ArrayList<String> filenames = new ArrayList<>();
        User user = User.getCurrentUser();

        if (files != null)
            for (File file : files) {
                String filename = file.getName();
                if (user == null || !user.hasSolved(filename))
                    filenames.add(filename);
            }

        return filenames;
    }

    private String getRandomSudoku(String directory) {
        ArrayList<String> filenames = getUnplayedSudokus("Sudokus/" + directory);

        if (filenames.size() == 0)
            return null;

        int rand = new Random().nextInt(filenames.size());
        return filenames.get(rand);
    }

    public static void main(String[] args) {
        JFrame frame = new Menu();
        frame.setVisible(true);
    }
}
