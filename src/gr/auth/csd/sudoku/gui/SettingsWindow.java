package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SettingsWindow extends JFrame{
    private JPanel panel;
    private JLabel title;
    private JPanel settingsPanel;
    private JCheckBox wordokuCheckBox;
    private JLabel lanuageLabel;
    private JButton englishButton;
    private JPanel languages;
    private JButton greekButton;
    private JButton closeButton;
    private boolean isWordoku;
    private int charSetIndex = 1; //Default to English


    public SettingsWindow(Menu menu){
        super("Settings");

        closeButton.addActionListener(click -> {
//            menu.setWordoku(wordokuCheckBox.isSelected());
        menu.setCharSet(wordokuCheckBox.isSelected() ? charSetIndex : 0);
        setVisible(false);
        });

        greekButton.addActionListener(click -> charSetIndex = 2);
        englishButton.addActionListener(click -> charSetIndex = 1);

        add(panel);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void showWindow() {
        setVisible(true);
    }


    public boolean getWordoku() {
        return isWordoku;
    }


    }
