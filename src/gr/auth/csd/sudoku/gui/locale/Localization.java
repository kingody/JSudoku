package gr.auth.csd.sudoku.gui.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
    private static final String baseName = "gr.auth.csd.sudoku.gui.locale.languages.Language";
    private static Language language;

    private static Locale[] supportedLocales = {
            new Locale("el", "GR"),
            new Locale("en", "US")
    };

    public static Language localize(Locale locale) {
        for (Locale supported : supportedLocales) {
            if (supported.equals(locale)) {
                language = (Language) ResourceBundle.getBundle(baseName, locale);
                return language;
            }
        }

        locale = new Locale("en", "US");
        language = (Language) ResourceBundle.getBundle(baseName, locale);
        return language;
    }

    public static Language getLanguage() { return language; }
}
