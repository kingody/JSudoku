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
    private Menu instance =new Menu();


    public SettingsWindow(){
        super("Settings");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        wordokuCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                isWordoku = e.getStateChange() == ItemEvent.SELECTED;
                System.out.println(isWordoku);
                instance.setWordoku(isWordoku);
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
