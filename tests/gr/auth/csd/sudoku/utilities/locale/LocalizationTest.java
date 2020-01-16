package gr.auth.csd.sudoku.utilities.locale;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationTest {
    @Test
    void localize() {
        Locale.setDefault(new Locale("en","US"));
        ResourceBundle lang = Localization.getLanguage();
        assertEquals("Let's play Sudoku!", lang.getString("greeting"));

        Locale.setDefault(new Locale("el","GR"));
        lang = Localization.getLanguage();
        assertEquals("Ας παίξουμε Σουντόκου!", lang.getString("greeting"));

        Locale.setDefault(new Locale("en","CA"));
        lang = Localization.getLanguage();
        assertEquals("Let's play Sudoku!", lang.getString("greeting"));

        char[] charSet = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        assertArrayEquals(charSet, lang.getString("charSet").toCharArray());
    }
}