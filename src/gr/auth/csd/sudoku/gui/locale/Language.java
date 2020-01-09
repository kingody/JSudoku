package gr.auth.csd.sudoku.gui.locale;

import java.util.ListResourceBundle;

/**
 * This class represents the blueprint for all languages
 */
public abstract class Language extends ListResourceBundle {
    protected char[] charSet;
    protected Object[][] contents;

    /**
     * Practically returns the dictionary(key- value pairs) of each language
     * The keys are common for all languages, only the values change
     * @return contents
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }

    /**
     * Used for wordoku
     * @return letters of language's alphabet for wordoku
     */
    public char[] getCharSet() { return charSet; }
}
