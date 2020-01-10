package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.utilities.locale.Localization;

import javax.swing.*;

/**
 * This class is used when the user wishes to see hints
 */
public class Hint extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JLabel validChars;

    /**
     * The panel and its two labels are initialized.
     * The label valid chars is used to display chars, while title is used to present a helpful message
     * The labels are then added to our panel, with itself being added to Hint. Finally the properties of Hint are set.
     * @param chars String with all the available characters
     */
    public Hint(String chars) {
        panel = new JPanel();
        title = new JLabel(Localization.getLanguage().getString("acceptable"));
        validChars = new JLabel(chars);

        panel.add(title);
        panel.add(validChars);
        add(panel);

        setSize(200,100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
