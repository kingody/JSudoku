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
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Menu extends JFrame {
    private JPanel background;
    private JLabel title;
    private JPanel buttons;
    private JLabel classic;
    private JLabel settings;
    private JLabel users;
    private TopBar bar;
    private JLabel currentUser;
    private JLabel killer;
    private JLabel duidoku;
    private JPanel panel;
    private JPanel main;

    private boolean isWordoku = false;
    private final NewSettings settingsWindow;

    private final char[] numberSet = {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public Menu() {
        settingsWindow = new NewSettings(this);

        classic.addMouseListener(new ButtonListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String filename = getRandomSudoku("Classic");
                if (filename == null)
                    filename = "classic1.txt";

                ClassicSudoku sudoku = new ClassicSudoku(9, filename);
                new ClassicWindow(sudoku, getCharSet());
            }
        });

        killer.addMouseListener(new ButtonListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String filename = getRandomSudoku("Killer");
                if (filename == null)
                    filename = "killer1.txt";

                KillerSudoku sudoku = new KillerSudoku(9, filename);
                new KillerWindow(sudoku, getCharSet());
            }
        });

        duidoku.addMouseListener(new ButtonListener() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 new DuidokuWindow(new Duidoku(4), getCharSet());
             }
         });

        settings.addMouseListener(new ButtonListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settingsWindow.showWindow();
            }
        });

        users.addMouseListener(new ButtonListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UserWindow(Menu.this);
            }
        });

        getRootPane().setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(60, 60, 60)));
        add(background);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Sets Menu's user to user and the text of label currentUser
     * @param user User object
     */
    public void setUser(User user) {
        User.setCurrentUser(user);
        currentUser.setText(Localization.getLanguage().getString("currentUser") + user.getUsername());
    }

    public void removeUser() {
        User.setCurrentUser(null);
        currentUser.setText(" ");
    }


    public void setWordoku(boolean set) {
        isWordoku = set;
    }

    /**
     * This function sets all the language-sensitive properties of our components
     */
    public void setText() {
        ResourceBundle lang = Localization.getLanguage();

        title.setText(lang.getString("menuTitle"));
        classic.setText(lang.getString("classic"));
        killer.setText(lang.getString("killer"));
        duidoku.setText(lang.getString("duidoku"));
        settings.setText(lang.getString("settings"));
        users.setText(lang.getString("selectUser"));

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

    private void createUIComponents() {
        classic = new RoundedLabel();
        killer = new RoundedLabel();
        duidoku = new RoundedLabel();
        settings = new RoundedLabel();
        users = new RoundedLabel();
    }

    private static class ButtonListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            ((JLabel) e.getSource()).setBackground(new Color(80, 80, 80));
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            ((JLabel) e.getSource()).setBackground(new Color(60, 60, 60));
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            ((JLabel) e.getSource()).setBackground(new Color(70, 70, 70));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            ((JLabel) e.getSource()).setBackground(new Color(60, 60, 60));
        }

        public void mouseClicked(MouseEvent e) {}
    }

    private class RoundedLabel extends JLabel {
        @Override
        protected void paintComponent(Graphics g) {
            Dimension arcs = new Dimension(5,5);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics.setColor(getBackground());
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            super.paintComponent(g);
        }
    }
}
