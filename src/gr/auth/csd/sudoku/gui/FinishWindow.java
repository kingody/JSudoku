package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.gui.locale.Localization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishWindow extends JDialog {
    private JLabel label;
    private  JButton mainMenu;
    private JPanel panel;
    public FinishWindow(boolean win, JFrame frame){
        super(frame);
        label = new JLabel();
        mainMenu = new JButton(Localization.getLanguage().getString("mainMenu"));
        panel = new JPanel();
        if(win){
            label.setText(Localization.getLanguage().getString("wonMessage"));
        }
        else{
            label.setText(Localization.getLanguage().getString("lostMessage"));
        }
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                dispose();
            }
        });
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenu.setPreferredSize(new Dimension(200,30));
        panel.add(label);
        panel.add(mainMenu);
        add(panel);
        setResizable(false);
        setSize(250,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
