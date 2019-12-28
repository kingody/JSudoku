package gr.auth.csd.sudoku.gui;

import javax.swing.*;

public class SettingsWindow extends JFrame{
    private JPanel panel;
    private JLabel title;
    private JPanel settingsPanel;
    private JCheckBox wordokuCheckBox;
    private JLabel lanuageLabel;
    private JButton englishButton;
    private JPanel languages;
    private JButton greekButton;
    private boolean isWordoku;
    public SettingsWindow(){
        super("Settings");
        add(panel);
        setVisible(true);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    }
}
