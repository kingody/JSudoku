package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserWindow extends  JFrame{
    private JPanel panel;
    private JList list;
    private JPanel buttonpanel;
    private JButton newUser;
    private JButton viewStatsButton;
    private User user;

    public UserWindow(){
        super("Select User");
        add(panel);
        setSize(350,350);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUIComponents() {
        DefaultListModel<String> model = new DefaultListModel<>();
        list = new JList<>(model);


    }
}
