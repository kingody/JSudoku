package gr.auth.csd.sudoku.utilities.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
    private static final String baseName = "gr.auth.csd.sudoku.utilities.locale.languages";

    public static ResourceBundle getLanguage() { return ResourceBundle.getBundle(baseName, Locale.getDefault()); }
}
