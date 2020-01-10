package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.utilities.User;
import gr.auth.csd.sudoku.gui.locale.Language;
import gr.auth.csd.sudoku.gui.locale.Localization;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used when the user wants to add a new user from the UserWindow
 */
public class InputUsername extends JFrame {
    private JTextField textField;
    private JLabel label;
    private JLabel userExists;
    private JButton closeButton;

    /**
     * The constructor initializes our panel and sets its layout. Then the properties of the panel's components are set.
     * Said components are then added to our panel and the properties of the InputUsername are set.
     * Every language-sensitive property is set based on Language lang.
     * Upon pressing the closeButton, the username from the textField is acquired. If it is not empty, the user is created.
     * Upon successful creation, the new user is added to the list in userWindow
     * @param userWindow UserWindow object
     */
    public InputUsername(UserWindow userWindow) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        Language lang = Localization.getLanguage();

        label = new JLabel(lang.getString("inputLabel"));
        userExists = new JLabel();
        textField = new JTextField(15);
        textField.setDocument(new TextLimit(15));

        closeButton = new JButton(lang.getString("add"));
        closeButton.addActionListener(click -> {
            //Removes whitespace from the beginning and end of the name
            String username = textField.getText().trim();

            if (!username.isEmpty()) {
                User user = User.newUser(username);

                if (user != null) {
                    userWindow.addUserToList(user);
                    setVisible(false);
                    userExists.setVisible(false);
                }
                else {
                    userExists.setText(lang.getString("user") + username + lang.getString("userExists"));
                    userExists.setVisible(true);
                }
            }


        });

        panel.add(label);
        panel.add(textField);
        panel.add(closeButton);
        panel.add(userExists);
        add(panel);

        setTitle(lang.getString("newUser"));
        setSize(320, 120);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getRootPane().setDefaultButton(closeButton);
        setLocationRelativeTo(null);
    }
    public void showWindow() {
        textField.setText("");
        setVisible(true);
    }
}
