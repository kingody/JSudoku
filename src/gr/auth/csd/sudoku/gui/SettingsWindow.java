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

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setWordoku(wordokuCheckBox.isSelected());
                dispose();
            }
        });





        add(panel);
        setVisible(true);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


    public boolean getWordoku() {
        return isWordoku;
    }


    }