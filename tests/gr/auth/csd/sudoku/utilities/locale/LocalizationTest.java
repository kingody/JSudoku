package gr.auth.csd.sudoku.utilities.locale;

import gr.auth.csd.sudoku.utilities.locale.languages.Language_en_US;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationTest {
    @Test
    void localize() {
        Language lang= Localization.localize(new Locale("en","US"));
        assertEquals("class gr.auth.csd.sudoku.utilities.locale.languages.Language_en_US",lang.getClass().toString());
    }
}