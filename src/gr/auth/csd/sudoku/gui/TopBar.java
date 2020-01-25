package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;

public class TopBar {
    private JLabel close;
    private JLabel minimize;
    private JPanel panel;
    private JPanel buttons;
    private JLabel title;

    public TopBar() {
        DragHandler mouse = new DragHandler();
        panel.addMouseListener(mouse);
        panel.addMouseMotionListener(mouse);

        close.addMouseListener(new DefaultButtonListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Window window = (Window) panel.getRootPane().getParent();
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            }
        });

        minimize.addMouseListener(new DefaultButtonListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((JFrame) panel.getRootPane().getParent()).setState(Frame.ICONIFIED);
            }
        });
    }

    public void setTitle(String text) {
        title.setText(text);
    }

    private class DragHandler implements MouseListener, MouseMotionListener {
        private int x, y;
        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            panel.getRootPane().getParent().setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }

    private class DefaultButtonListener implements MouseListener {
        @Override
        public void mouseEntered(MouseEvent e) {
            ((JLabel) e.getSource()).setForeground(new Color(200, 200, 200));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((JLabel) e.getSource()).setForeground(new Color(154, 154, 154));
        }

        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
    }
}
