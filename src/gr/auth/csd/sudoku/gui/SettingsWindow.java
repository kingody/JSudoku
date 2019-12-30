package gr.auth.csd.sudoku.gui;

import javax.swing.*;

public class SettingsWindow extends JFrame{
    private JPanel panel;
    private JPanel settingsPanel;
    private JPanel languages;
    private JLabel title;
    private JCheckBox wordokuCheckBox;
    private JLabel languageLabel;
    private JButton closeButton;
    private JRadioButton englishButton;
    private JRadioButton greekButton;
    //Default to English
    private int charSetIndex = 1;


    public SettingsWindow(Menu menu){
        super("Settings");

        closeButton.addActionListener(click -> {
            menu.setCharSet(wordokuCheckBox.isSelected() ? charSetIndex : 0);
            setVisible(false);
        });

        englishButton.setSelected(true);
        englishButton.addActionListener(click -> charSetIndex = 1);
        greekButton.addActionListener(click -> charSetIndex = 2);

        add(panel);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void showWindow() {
        setVisible(true);
    }
}
