package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.User;

import javax.swing.*;
import java.awt.*;

public class InputUsername extends JFrame {
    private JTextField textField;
    private JLabel label;
    private JButton closeButton;

    public InputUsername(UserWindow userWindow) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        label = new JLabel(userWindow.bundle.getString("inputLabel"));
        textField = new JTextField(15);
        textField.setDocument(new TextLimit(15));

        closeButton = new JButton(userWindow.bundle.getString("add"));
        closeButton.addActionListener(click -> {
            String username = textField.getText();

            if (!username.equals("")) {
                User user = User.newUser(username);

                if (user != null) {
                    userWindow.addUserToList(user);
                }
            }

            setVisible(false);
        });

        panel.add(label);
        panel.add(textField);
        panel.add(closeButton);
        add(panel);

        setSize(300, 100);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getRootPane().setDefaultButton(closeButton);
        setLocationRelativeTo(null);
    }
    public void showWindow() {
        textField.setText("");
        setVisible(true);
    }
}
