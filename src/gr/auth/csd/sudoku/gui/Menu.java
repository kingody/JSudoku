package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.variants.classic.ClassicSudoku;
import gr.auth.csd.sudoku.variants.duidoku.Duidoku;
import gr.auth.csd.sudoku.variants.killer.KillerSudoku;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Menu extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JButton classic;
    private JButton killer;
    private JButton settings;
    private JButton duidoku;
    private JPanel menu;
    private JButton userButton;
    ResourceBundle lang;


    private Settings settingsWindow = new Settings(this);


    private final char[][] charSet = {
            {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
            {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'},
            {' ', 'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ', 'Ι'}
    };

    private char[] current = charSet[0];

    Locale[] supportedLocales = {
            new Locale("el","GR"),
            new Locale("en", "CA")
    };
    private Locale currLanguage;

    public Menu(Locale currLangua) {
        add(panel);
        this.currLanguage = currLangua;


        lang = ResourceBundle.getBundle("gr.auth.csd.sudoku.gui.Language", currLanguage);

        title.setText(lang.getString("greeting"));
        classic.setText(lang.getString("classic"));
        killer.setText(lang.getString("kill"));
        duidoku.setText(lang.getString("duidoku"));
        settings.setText(lang.getString("settings"));
        userButton.setText(lang.getString("selectUser"));



        ClassicSudoku sudoku = new ClassicSudoku(9,"classic10.txt");
        KillerSudoku kil = new KillerSudoku(9,"killer10.txt");
        Duidoku dui = new Duidoku(4);

        classic.addActionListener(click -> new SudokuWindow("Classic", sudoku, current,lang));
        killer.addActionListener(click -> new KillerWindow("killer", kil, current,lang));
        duidoku.addActionListener(click->new DuidokuWindow("Duidoku",dui,current,lang));
        settings.addActionListener(click -> settingsWindow.showWindow());
        userButton.addActionListener(click-> new UserWindow(lang));




        setTitle("Sudoku");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setCharSet(int index) {
        if (index >= 0 && index < charSet.length)
            current = charSet[index];
    }
    public void setCurrLanguage(int index){
        System.out.println(Locale.getDefault().equals(supportedLocales[index]));
        if(Locale.getDefault().equals(supportedLocales[index])){
            System.out.println("hi");
        }
        else{
            currLanguage = supportedLocales[index];
            lang = ResourceBundle.getBundle("gr.auth.csd.sudoku.gui.Language", currLanguage);
            Locale.setDefault(currLanguage);
            dispose();
            JFrame frame = new Menu(currLanguage);
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new Menu(Locale.getDefault());
        frame.setVisible(true);
    }
}
