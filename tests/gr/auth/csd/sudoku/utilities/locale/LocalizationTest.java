package gr.auth.csd.sudoku.utilities.locale;

import gr.auth.csd.sudoku.utilities.locale.languages.Language_en_US;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationTest {
    @Test
    void localize() {
        Language lang = Localization.localize(new Locale("en","US"));
        assertEquals("class gr.auth.csd.sudoku.utilities.locale.languages.Language_en_US", lang.getClass().toString());

        lang = Localization.localize(new Locale("el","GR"));
        assertEquals("class gr.auth.csd.sudoku.utilities.locale.languages.Language_el_GR", lang.getClass().toString());

        lang = Localization.localize(new Locale("en","CA"));
        assertEquals("class gr.auth.csd.sudoku.utilities.locale.languages.Language_en_US", lang.getClass().toString());

        assertTrue(Localization.getLanguage() instanceof Language_en_US);

        char[] charSet = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        assertArrayEquals(charSet, lang.getCharSet());

        Language_en_US english = new Language_en_US();
        assertArrayEquals(english.getContents(), lang.getContents());
    }
}