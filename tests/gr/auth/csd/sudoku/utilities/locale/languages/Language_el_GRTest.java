package gr.auth.csd.sudoku.utilities.locale.languages;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Language_el_GRTest {
    @Test
    void getCharSet(){
        char[] exp = new char[] { ' ', 'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ', 'Ι' };
        Language_el_GR gr = new Language_el_GR();
        assertEquals(Arrays.hashCode(exp),Arrays.hashCode(gr.getCharSet()));
    }


}