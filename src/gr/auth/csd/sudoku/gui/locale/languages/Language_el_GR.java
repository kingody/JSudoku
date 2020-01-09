package gr.auth.csd.sudoku.gui.locale.languages;

import gr.auth.csd.sudoku.gui.locale.Language;

/**
 * This class represents the greek translation
 */
public class Language_el_GR extends Language {

    public Language_el_GR(){
        charSet = new char[] { ' ', 'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ', 'Ι' };

        contents = new Object[][] {
                {"menuTitle", "Σουντόκου"},
                {"greeting", "Ας παίξουμε Σουντόκου"},
                {"classic", "Κλασσικό Σουντόκου"},
                {"kill", "Χρωμοσουντόκου"},
                {"duidoku", "Διντόκου"},
                {"hint", "Υπόδειξη"},
                {"acceptable", "Τα παρακάτω είναι αποδεκτά:"},
                {"settings", "Ρυθμίσεις"},
                {"wordoku", "Σουντόκου με γράμματα"},
                {"languageSelect", "Επέλεξε γλώσσα"},
                {"english", "Αγγλικά"},
                {"greek", "Ελληνικά"},
                {"userB", "Επέλεξε χρήστη"},
                {"existingUser", "Υπάρχων χρήστες:"},
                {"addUser", "Πρόσθεσε χρήστη"},
                {"newUser", "Νέος χρήστης"},
                {"userExists", " υπάρχει ήδη"},
                {"currentUser", "Χρήστης: "},
                {"statisticButton", "Δες τα στατιστικά"},
                {"selectUser", "Επέλεξε χρήστη"},
                {"noUser", "Παίξε χωρίς χρήστη"},
                {"stats", "Στατιστικά"},
                {"user", "Ο χρήστης "},
                {"has", " έχει "},
                {"wins", " νίκες και "},
                {"loses", " ήττες"},
                {"noUserWarn", "Επέλεξε χρήστη πρώτα"},
                {"add", "Προσθήκη"},
                {"inputLabel", "Εισήγαγε το όνομα χρήστη (μέχρι 15 χαρακτήρες)"}
        };
    }
}
