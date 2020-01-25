package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.utilities.locale.Localization;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class NewSettings extends JFrame {
    private JPanel panel;
    private TopBar bar;
    private JPanel background;
    private JLabel title;
    private JCheckBox wordokuCheckBox;
    private JLabel languageSelect;
    private JPanel main;
    private JRadioButton englishRadioButton;
    private JRadioButton greekRadioButton;
    private JButton closeButton;

    public NewSettings(Menu menu) {

        closeButton.addActionListener(click -> {
            menu.setWordoku(wordokuCheckBox.isSelected());
            Locale.setDefault(englishRadioButton.isSelected() ? new Locale("en", "US") : new Locale("el", "GR"));
            menu.setText();
            setVisible(false);
            setText();
        });

        if (Locale.getDefault().equals(new Locale("en", "US"))) {
            englishRadioButton.setSelected(true);
        }
        else {
            greekRadioButton.setSelected(true);
        }

        setUndecorated(true);
        add(background);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getRootPane().setDefaultButton(closeButton);
    }

    /**
     * Used to localize language-sensitive properties of components
     */
    public void setText() {
        ResourceBundle lang = Localization.getLanguage();
        setTitle(lang.getString("settings"));

        title.setText(lang.getString("settings"));
        wordokuCheckBox.setText(lang.getString("wordoku"));
        languageSelect.setText(lang.getString("languageSelect"));
        englishRadioButton.setText(lang.getString("english"));
        greekRadioButton.setText(lang.getString("greek"));
    }

    /**
     * Sets the visibility of the Settings window to true
     */
    public void showWindow() {
        setVisible(true);
    }
}

