package gr.auth.csd.sudoku.gui.locale;

import java.util.ListResourceBundle;

public abstract class Language extends ListResourceBundle {
    protected char[] charSet;
    protected Object[][] contents;

    @Override
    public Object[][] getContents() {
        return contents;
    }

    public char[] getCharSet() { return charSet; }
}
