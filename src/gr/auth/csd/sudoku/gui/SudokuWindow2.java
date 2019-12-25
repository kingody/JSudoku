package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuWindow2 extends JFrame {

    public SudokuWindow2(String mode, Sudoku sud){

        JFrame frame = new JFrame(mode);
        int size = sud.getGrid().length;
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        JTable board;
        board = new JTable(size,size){
            public boolean isCellEditable(int row,int col){return false;}
        };
        board.setRowHeight(50);

        setColumnWidth(board,50,size);
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++) {
                board.setValueAt(sud.getCell(i,j),i,j);

            }
        }
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = board.rowAtPoint(e.getPoint());
                int col = board.columnAtPoint(e.getPoint());
                new InputHelpWindow();


            }
        });
        panel.add(board);
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public void setColumnWidth(JTable table,int width,int num_colum){
        for(int i =0;i<num_colum;i++){
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(width);
        }
    }
}
