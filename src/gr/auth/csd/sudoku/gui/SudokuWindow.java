package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SudokuWindow extends JFrame{

    public SudokuWindow(String mode, Sudoku sud){
        super(mode);
        int size = sud.getGrid().length;
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        JPanel board2 = new JPanel(new GridLayout(size,size));
        JTextField[][] cells = new JTextField[size][size];
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setDocument(new Textlimit(1));
                cells[i][j].setText(Integer.toString(sud.getCell(i,j)));
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                board2.add(cells[i][j]);
                /*cells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        Point p = e.getPoint();
                        p = SwingUtilities.convertPoint(e.getComponent(), p, e.getComponent().getParent());
                        System.out.println("Converted point = " + p );
                    }
                });*/
            }
        }



        board2.setPreferredSize(new Dimension(650,650));
        panel.add(board2);
        this.add(panel);
        setVisible(true);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        }
