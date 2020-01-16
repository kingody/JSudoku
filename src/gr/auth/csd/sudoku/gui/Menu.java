package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.User;
import gr.auth.csd.sudoku.gui.variants.ClassicWindow;
import gr.auth.csd.sudoku.gui.variants.DuidokuWindow;
import gr.auth.csd.sudoku.gui.variants.KillerWindow;
import gr.auth.csd.sudoku.utilities.locale.Localization;
import gr.auth.csd.sudoku.variants.ClassicSudoku;
import gr.auth.csd.sudoku.variants.Duidoku;
import gr.auth.csd.sudoku.variants.KillerSudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Menu extends JFrame {
    private JPanel background;
    private JLabel title;
    private JButton close;
    private JPanel buttons;
    private JButton classic;
    private JButton killer;
    private JButton duidoku;
    private JButton settings;
    private JButton users;
    private JButton minimize;
    private JPanel bar;
    private JLabel currentUser;

    private boolean isWordoku = false;
    private final Settings settingsWindow;

    private final char[] numberSet = {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public Menu() {
        initializeTopBar();
        settingsWindow = new Settings(this);

        classic.addActionListener(click -> {
            String filename = getRandomSudoku("Classic");
            if (filename == null)
                filename = "classic1.txt";

            ClassicSudoku sudoku = new ClassicSudoku(9, filename);
            new ClassicWindow(sudoku, getCharSet());
        });

        killer.addActionListener(click -> {
            String filename = getRandomSudoku("Killer");
            if (filename == null)
                filename = "killer1.txt";

            KillerSudoku sudoku = new KillerSudoku(9, filename);
            new KillerWindow(sudoku, getCharSet());
        });

        duidoku.addActionListener(click-> new DuidokuWindow(new Duidoku(4), getCharSet()));
        settings.addActionListener(click -> settingsWindow.showWindow());
        users.addActionListener(click-> new UserWindow(this));

        getRootPane().setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(60, 60, 60)));
        add(background);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeTopBar() {
        MouseHandler mouse = new MouseHandler();
        bar.addMouseListener(mouse);
        bar.addMouseMotionListener(mouse);

        close.addActionListener(click -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        minimize.addActionListener(click -> setState(Frame.ICONIFIED));
    }

    /**
     * Sets Menu's user to user and the text of label currentUser
     * @param user User object
     */
    public void setUser(User user) {
        User.setCurrentUser(user);
        currentUser.setText(Localization.getLanguage().getString("currentUser") + user.getUsername());
        currentUser.setVisible(true);
    }

    public void removeUser() {
        User.setCurrentUser(null);
        currentUser.setVisible(false);
    }


    public void setWordoku(boolean set) {
        isWordoku = set;
    }

    /**
     * This function sets all the language-sensitive properties of our components
     */
    public void setText() {
        ResourceBundle lang = Localization.getLanguage();

        setTitle(lang.getString("menuTitle"));

        title.setText(lang.getString("menuTitle"));
        classic.setText(lang.getString("classic"));
        killer.setText(lang.getString("killer"));
        duidoku.setText(lang.getString("duidoku"));
        settings.setText(lang.getString("settings"));
        users.setText(lang.getString("selectUser"));
        currentUser.setText(lang.getString("currentUser"));

        User user = User.getCurrentUser();
        if (user != null)
            setUser(user);
    }

    /**
     * Sets the characters for the game based on the value of isWordoku boolean
     */
    private char[] getCharSet() {
        return isWordoku ? Localization.getLanguage().getString("charSet").toCharArray() : numberSet;
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
        new Menu();
    }


    private class MouseHandler implements MouseListener, MouseMotionListener {
        private int x, y;
        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Menu.this.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }
}
