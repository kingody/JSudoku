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

    //System default Locale
    private Language lang = Localization.getLanguage();



    public Settings(Menu menu){
        super("Settings");

        closeButton.addActionListener(click -> {
            menu.setWordoku(wordokuCheckBox.isSelected());
            menu.localize(englishButton.isSelected() ? new Locale("en", "US") : new Locale("el", "GR"));
            setVisible(false);
        });

        englishButton.setSelected(true);

        add(panel);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getRootPane().setDefaultButton(closeButton);
    }

    public void setText() {
        title.setText(lang.getString("settings"));
        wordokuCheckBox.setText(lang.getString("wordoku"));
        englishButton.setText(lang.getString("english"));
        greekButton.setText(lang.getString("greek"));
    }

    public void showWindow() {
        setVisible(true);
    }
}
