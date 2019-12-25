package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

public class InputHelpWindow extends JFrame {
    private JTextArea input;
    private JLabel label;
    private JPanel panel;

    public InputHelpWindow() {
        input = new JTextArea();
        input.setDocument(new Textlimit(1));
        label = new JLabel("Enter value of selected cell:");
        panel = new JPanel();

        panel.setLayout(new FlowLayout(FlowLayout.LEADING,10,20));

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
        panel.add(label,BorderLayout.CENTER);
        panel.add(input,BorderLayout.EAST);
        add(panel);

        setVisible(true);
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

