package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserWindow extends  JFrame{
    private JPanel panel;
    private JList<String> list;
    private JPanel buttonpanel;
    private JButton newUser;
    private JButton viewStatsButton;
    private String username="";
    private InputUsername inputUsername = new InputUsername(this);

    public UserWindow(){
        super("Select User");
        newUser.addActionListener(click -> inputUsername.showWindow());
        viewStatsButton.addActionListener(click->{
            JFrame stats = new JFrame("stats");
            JLabel label = new JLabel();
            if(!username.equals("")) {
                User a = User.loadUser(username);
                label.setText("User " + username + " has " + a.getWins() + " wins and " + a.getLosses() + " loses");
            }
            else{
                label.setText("Please select a user first");
            }
            label.setHorizontalAlignment(SwingConstants.CENTER);
            stats.add(label);
            stats.setLocationRelativeTo(null);
            stats.setResizable(false);
            stats.setSize(300,100);
            stats.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            stats.setVisible(true);
        });

        add(panel);



        setSize(350,350);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void setUsername(String str){
        username = str;
    }

    private void createUIComponents() {
        DefaultListModel<String> model = new DefaultListModel<>();
        list = new JList<>(model);
        ArrayList<User> users = new ArrayList<>(User.loadAll());
        for(User user: users){
            model.addElement(user.getUsername());
        }
        list.getSelectionModel().addListSelectionListener(e -> {
            username = list.getSelectedValue();
            System.out.println(username);
        });

        


    }
}
