package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.util.Locale;

public class Settings extends JFrame{
    private JPanel panel;
    private JPanel settingsPanel;
    private JPanel languages;
    private JLabel title;
    private JCheckBox wordokuCheckBox;
    private JLabel languageLabel;
    private JButton closeButton;
    private JRadioButton englishButton;
    private JRadioButton greekButton;
    //Default to greek
    private int charSetIndex = 2;



    public Settings(Menu menu){
        super("Settings");

        closeButton.addActionListener(click -> {
            menu.setCharSet(wordokuCheckBox.isSelected() ? charSetIndex : 0);
            menu.setCurrLanguage(englishButton.isSelected() ? 1 : 0);
            setVisible(false);
        });

        englishButton.addActionListener(click -> charSetIndex = 1);
        greekButton.addActionListener(click -> charSetIndex = 2);

        add(panel);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getRootPane().setDefaultButton(closeButton);
    }

    public void showWindow() {
        setVisible(true);
    }
}
