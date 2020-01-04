package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.*;

public class InputUsername extends JFrame {
    private JTextField textField;
    private JLabel label;
    private JButton closeButton;

    public InputUsername(UserWindow userWindow) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        label = new JLabel("Enter your username (up to 15 characters)");
        textField = new JTextField(15);
        closeButton = new JButton("Ok");
        textField.setDocument(new TextLimit(15));


        closeButton.addActionListener(click -> {
            userWindow.setUsername(textField.getText());
            setVisible(false);
        });

        panel.add(label);
        panel.add(textField);
        panel.add(closeButton);
        add(panel);
        setSize(300, 100);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void showWindow() {
        setVisible(true);
    }

}
