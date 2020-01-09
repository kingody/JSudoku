package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.User;
import gr.auth.csd.sudoku.gui.locale.Language;
import gr.auth.csd.sudoku.gui.locale.Localization;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class represents our window for the management of stored users
 */
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

    /**
     * First gets the language via Localization.getLanguage and sets the components' language-sensitive properties
     * Adds ActionListeners to the buttons. The add user button simply makes the inputUsername visible
     * The view stats button allows the user to view the stats of  currentUser, as long as currentUser is not empty
     * Then the User with the username currentUser is loaded and his stats are displayed in a pop-up window
     * The selectUser button loads the User user with the the username currentUser(if it is not empty) and then user is passed as an argument to menu.setUser
     * The noUser button removes any previously selected user
     * @param menu instance of Menu
     */
    public UserWindow(Menu menu){
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
                menu.setUser(user);
                setVisible(false);
            }
        });

        noUserButton.addActionListener(click -> {
            currentUser = "";
            menu.removeUser();
            setVisible(false);
        });

        add(panel);

        setTitle(lang.getString("selectUser"));
        setSize(350,350);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Used for the configuration of our JList, where the stored usernames are displayed.
     * It also adds a SelectionListener to the list, from which the current user is obtained and set
     *
     */
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
