package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

public class InputHelpWindow extends JFrame {
    private JTextField input;
    private JLabel label;
    private JPanel panel;

    public InputHelpWindow() {
        input = new JTextField("Hi");
        label = new JLabel("Enter value of selected cell:");
        panel = new JPanel();

        KeyListener listen = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keycode = e.getKeyCode();
                int actual = (keycode + 2) % 10;

            }

            @Override
            public void keyReleased(KeyEvent e) {
                //prama

            }
        };
        input.addKeyListener(listen);
        panel.add(label);
        panel.add(input);
        add(panel);

        setVisible(true);
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

