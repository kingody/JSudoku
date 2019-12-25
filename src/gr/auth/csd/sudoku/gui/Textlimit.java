package gr.auth.csd.sudoku.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Textlimit extends PlainDocument {
    private  int limit;
    public Textlimit(int limit){
        super();
        this.limit = limit;
    }
    public Textlimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }

}
