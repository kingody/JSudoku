package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.User;
import gr.auth.csd.sudoku.gui.locale.Language;
import gr.auth.csd.sudoku.gui.locale.Localization;

import javax.swing.*;
import java.util.ArrayList;

public class UserWindow extends JFrame {
    private JPanel panel;
    private JList<String> list;
    private JPanel buttonpanel;
    private JButton viewStatsButton;
    private JButton selectButton;
    private JLabel title;
    private JButton addUserButton;
    private JButton noUserButton;
    private String currentUser = "";
    private InputUsername inputUsername;
    private Language lang;

    private DefaultListModel<String> model;

    public UserWindow(){
        lang = Localization.getLanguage();

        inputUsername = new InputUsername(this);
        title.setText(lang.getString("existingUser"));
        addUserButton.setText(lang.getString("addUser"));
        viewStatsButton.setText(lang.getString("statisticButton"));
        selectButton.setText(lang.getString("selectUser"));
        noUserButton.setText(lang.getString("noUser"));


        addUserButton.addActionListener(click -> inputUsername.showWindow());
        viewStatsButton.addActionListener(click -> {
            JFrame stats = new JFrame(lang.getString("stats"));
            JLabel label = new JLabel();

            if(!currentUser.equals("")) {
                User a = User.loadUser(currentUser);
                label.setText(lang.getString("user") + currentUser + lang.getString("has") + a.getWins() +lang.getString("wins") + a.getLosses() + lang.getString("loses"));
            }
            else{
                label.setText(lang.getString("noUserWarn"));
            }
            label.setHorizontalAlignment(SwingConstants.CENTER);
            stats.add(label);
            stats.setLocationRelativeTo(null);
            stats.setResizable(false);
            stats.setSize(300,100);
            stats.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            stats.setVisible(true);
        });

        selectButton.addActionListener(click -> {
            if(!currentUser.equals("")) {
                User user = User.loadUser(currentUser);
                setVisible(false);
            }
        });

        noUserButton.addActionListener(click -> {
            currentUser = "";
            setVisible(false);
        });

        add(panel);

        setTitle(lang.getString("selectUser"));
        setSize(350,350);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUIComponents() {
        model = new DefaultListModel<>();
        list = new JList<>(model);

        ArrayList<User> users = User.loadAll();
        if (users == null)
            return;

        for(User user: users){
            addUserToList(user);
        }

        list.getSelectionModel().addListSelectionListener(e -> {
            //Stops event from happening many times
            if (e.getValueIsAdjusting())
                return;

            currentUser = list.getSelectedValue();
            System.out.println(currentUser);
        });
    }

    public void addUserToList(User user) {
        model.addElement(user.getUsername());
        list.setSelectedIndex(list.getLastVisibleIndex());
    }
}
