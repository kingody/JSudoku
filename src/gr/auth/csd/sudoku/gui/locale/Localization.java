package gr.auth.csd.sudoku.gui.locale;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class is responsible for the localization of our program
 */
public class Localization {
    private static final String baseName = "gr.auth.csd.sudoku.gui.locale.languages.Language";
    private static Language language;

    //supported languages
    private static Locale[] supportedLocales = {
            new Locale("el", "GR"),
            new Locale("en", "US")
    };

    /**
     * Finds the language selected by matching locale to its match in supportedLocales. If it does not find it, it returns the english Language object
     * @param locale Locale object
     * @return the Language object language
     */
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
