package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.gui.locale.Localization;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class Hint extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JLabel validChars;

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
