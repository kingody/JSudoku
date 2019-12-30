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


    public SettingsWindow(Menu menu){
        super("Settings");

        closeButton.addActionListener(click -> {
            menu.setWordoku(wordokuCheckBox.isSelected());
            setVisible(false);
        });

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
