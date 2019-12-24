package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.Sudoku;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SudokuWindow extends JFrame{
    JTable board;

    public SudokuWindow(String mode, Sudoku sud){
        super(mode);
        int size = sud.getGrid().length;
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        JPanel board2 = new JPanel(new GridLayout(size,size));
        JTextField[][] cells = new JTextField[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cells[i][j] = new JTextField(Integer.toString(sud.getCell(i,j)));

                board2.add(cells[i][j]);
            }
        }
       /*board = new JTable(size,size){
            public boolean isCellEditable(int row,int col)  {return false;}
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
        });*/
        this.add(board2);




        //this.add(panel);
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
