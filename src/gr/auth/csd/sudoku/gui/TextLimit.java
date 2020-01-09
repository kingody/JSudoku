package gr.auth.csd.sudoku.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Utility class to limit the size of the JTextFields used in the program to a number of our choice. It does not allow for further text after the limit at all.
 */
public class TextLimit extends PlainDocument {
    private int limit;

    public TextLimit(int limit){
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
