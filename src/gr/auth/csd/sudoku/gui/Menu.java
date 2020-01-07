package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.gui.locale.Language;
import gr.auth.csd.sudoku.gui.locale.Localization;
import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;
import gr.auth.csd.sudoku.variants.duidoku.Duidoku;
import gr.auth.csd.sudoku.variants.killer.KillerSudoku;

import javax.swing.*;
import java.util.Locale;
import java.util.Random;

public class Menu extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JButton classic;
    private JButton killer;
    private JButton settings;
    private JButton duidoku;
    private JPanel menu;
    private JButton userButton;
    private Language lang;

    private boolean isWordoku = false;
    private Settings settingsWindow;

    private final char[] numberSet = {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private char[] charSet;

    public Menu() {
        add(panel);

        localize(Locale.getDefault());

        Random random = new Random();
        String classicNum = Integer.toString(1 + random.nextInt(10));
        String killerNum  = Integer.toString(1 + random.nextInt(10));
        ClassicSudoku sudoku = new ClassicSudoku(9,"classic" + classicNum + ".txt");
        KillerSudoku kil = new KillerSudoku(9,"killer" + killerNum + ".txt");

        settingsWindow = new Settings(this);
        classic.addActionListener(click -> new SudokuWindow(sudoku, charSet));
        killer.addActionListener(click -> new KillerWindow(kil, charSet));
        duidoku.addActionListener(click-> new DuidokuWindow(new Duidoku(4), charSet));
        settings.addActionListener(click -> settingsWindow.showWindow());
        userButton.addActionListener(click-> new UserWindow());

        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setWordoku(boolean set) {
        isWordoku = set;
        setCharSet();
    }

    public void localizeAll(Locale locale) {
        localize(locale);
        settingsWindow.setText();
    }

    public void localize(Locale locale) {
        lang = Localization.localize(locale);
        setText();
        setCharSet();
    }

    private void setText() {
        setTitle(lang.getString("menuTitle"));

        title.setText(lang.getString("greeting"));
        classic.setText(lang.getString("classic"));
        killer.setText(lang.getString("kill"));
        duidoku.setText(lang.getString("duidoku"));
        settings.setText(lang.getString("settings"));
        userButton.setText(lang.getString("selectUser"));
    }

    private void setCharSet() {
            charSet = isWordoku ? lang.getCharSet() : numberSet;
    }

    public static void main(String[] args) {
        JFrame frame = new Menu();
        frame.setVisible(true);
    }
}
