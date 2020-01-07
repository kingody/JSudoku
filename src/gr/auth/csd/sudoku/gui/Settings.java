package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.gui.locale.Language;
import gr.auth.csd.sudoku.gui.locale.Localization;

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
    private Language lang;

    public Settings(Menu menu){
        setText();
        System.out.println("hi");

        closeButton.addActionListener(click -> {
            menu.setWordoku(wordokuCheckBox.isSelected());
            menu.localize(englishButton.isSelected() ? new Locale("en", "US") : new Locale("el", "GR"));
            setVisible(false);
            setText();
        });
        System.out.println(Locale.getDefault().equals(Locale.ENGLISH));
        if(Locale.getDefault().equals(Locale.ENGLISH)){
            englishButton.setSelected(true);
        }
        else{
            greekButton.setSelected(true);
        }

        englishButton.setSelected(true);

        add(panel);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getRootPane().setDefaultButton(closeButton);
    }

    public void setText() {
        Language lang = Localization.getLanguage();

        setTitle(lang.getString("settings"));

        title.setText(lang.getString("settings"));
        wordokuCheckBox.setText(lang.getString("wordoku"));
        languageLabel.setText(lang.getString("languageSelect"));
        englishButton.setText(lang.getString("english"));
        greekButton.setText(lang.getString("greek"));
    }

    public void showWindow() {
        setVisible(true);
    }
}
