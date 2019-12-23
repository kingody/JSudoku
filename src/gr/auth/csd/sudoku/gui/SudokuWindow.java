package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuWindow extends JFrame{
    JTable board;

    public SudokuWindow(String mode,int [][] grid){
        super(mode);
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        board = new JTable(grid.length,grid.length);
        board.setRowHeight(50);
        setColumnWidth(board,50,grid.length);
        for(int i =0;i<grid.length;i++) {
            for (int j = 0; j < grid.length; j++){
                board.setValueAt(grid[i][j], i, j);
            }
        }
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = board.rowAtPoint(e.getPoint());
                int col = board.columnAtPoint(e.getPoint());
                System.out.println(row);
            }
        });
        panel.add(board);




        this.add(panel);
        setVisible(true);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        public void setColumnWidth(JTable table,int width,int num_colum){
            for(int i =0;i<num_colum;i++){
                TableColumn col = table.getColumnModel().getColumn(i);
                col.setPreferredWidth(width);
            }
        }
    }
