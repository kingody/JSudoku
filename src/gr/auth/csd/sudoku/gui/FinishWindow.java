package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.utilities.locale.Localization;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used when a game comes to an end.
 * Shows whether or not the user completed a puzzle or won the game of duidoku
 */
public class FinishWindow extends JDialog {
    private JLabel label;
    private JButton mainMenu;
    private JPanel panel;

    /**
     * Used to build the dialog window. Returns to main menu if the mainMenu button is pressed
     * @param win Whether or not the user had a successful outcome
     * @param frame Frame from which it was called
     */
    public FinishWindow(boolean win, JFrame frame){
        super(frame);

        label = new JLabel();
        mainMenu = new JButton(Localization.getLanguage().getString("mainMenu"));
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,15));
        if(win){
            label.setText(Localization.getLanguage().getString("wonMessage"));
        }
        else{
            label.setText(Localization.getLanguage().getString("lostMessage"));
        }
        mainMenu.addActionListener(click -> {
            frame.dispose();
            dispose();
        });
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenu.setPreferredSize(new Dimension(200,30));
        panel.add(label);
        panel.add(mainMenu);
        add(panel);
        setBackground(Color.lightGray);
        setResizable(false);
        setSize(250,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
