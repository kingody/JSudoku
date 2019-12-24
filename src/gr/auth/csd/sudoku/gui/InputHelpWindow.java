package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHelpWindow extends JFrame  {
    private JTextField input;
    public InputHelpWindow(){
        super("Enter number");

        input = new JTextField();
        KeyListener listen = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keycode = e.getKeyCode();
                int actual = (keycode+2)%10;
                System.out.println(actual);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //prama

            }
        };
        input.addKeyListener(listen);
        add(input);
        setVisible(true);
        setSize(100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
