package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.gui.locale.Language;
import gr.auth.csd.sudoku.gui.locale.Localization;

import javax.swing.*;
import java.util.Locale;

/**
 * This class represents our settings window, from which the user can easily configure the application's settings
 */
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

    /**
     * Adds an action listener to the close button.
     * It calls the setWordoku method of menu with the selection state of wordokuCheckBox as its parameter
     * It also calls the localize method of menu and with the Locale parameter being determined based on the state of the JRadioButtons
     * Hides the Setting object and then calls setText
     * Lastly, the constructor determines which JRadioButton will be selected upon showing the window, and sets the properties of Settings
     * @param menu The current instance of the Menu
     */
    public Settings(Menu menu){
        setText();

        closeButton.addActionListener(click -> {
            menu.setWordoku(wordokuCheckBox.isSelected());
            menu.localize(englishButton.isSelected() ? new Locale("en", "US") : new Locale("el", "GR"));
            setVisible(false);
            setText();
        });

        if (Locale.getDefault().equals(new Locale("en", "US"))) {
            englishButton.setSelected(true);
        }
        else {
            greekButton.setSelected(true);
        }

        add(panel);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getRootPane().setDefaultButton(closeButton);
    }

    /**
     * Used to localize language-sensitive properties of components
     */
    public void setText() {
        lang = Localization.getLanguage();

        setTitle(lang.getString("settings"));

        title.setText(lang.getString("settings"));
        wordokuCheckBox.setText(lang.getString("wordoku"));
        languageLabel.setText(lang.getString("languageSelect"));
        englishButton.setText(lang.getString("english"));
        greekButton.setText(lang.getString("greek"));
    }

    /**
     * Sets the visibility of the Settings window to true
     */
    public void showWindow() {
        setVisible(true);
    }
}
