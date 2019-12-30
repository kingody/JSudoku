package gr.auth.csd.sudoku.gui;

import javax.swing.*;

public class Hint extends JFrame {
    private JPanel panel;
    private JLabel title;
    private JLabel validChars;

    public Hint(String chars) {
        panel = new JPanel();
        title = new JLabel("The following are acceptable:  ");
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
