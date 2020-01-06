package gr.auth.csd.sudoku.gui;

import java.util.ListResourceBundle;

public class Language_el_GR extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }
    private Object[][] contents = {
            { "hint", "Υπόδειξη" },
            { "greeting","Ας παίξουμε σουντόκου" },
            {"classic","Κλασσικό σουντόκου"},
            { "kill","Killer σουντόκου" },
            {"duidoku","Duidoku"},
            {"settings","Ρυθμίσεις"},
            {"userB", "Επέλεξε χρήστη"},
            {"hintwind" ,"Τα παρακάτω είναι δεκτά"},
            {"languageSetting","Επέλεξε γλώσσα"},
            {"existinUser","Υπάρχων χρήστες: "},
            {"addUser","Πρόσθεσε χρήστη"},
            {"statisticButton","Δες τα στατιστικά"},
            {"selectUser","Επέλεξε χρήστη"},
            {"noUser","Παίξε χωρίς χρήστη"},
            {"stats","Στατιστικά"},
            {"user","Ο χρήστης "},
            {"has"," έχει "},
            {"wins"," νίκες και "},
            {"loses"," ήττες"},
            {"noUserWarn","Επέλεξε χρήστη πρώτα"},
            {"inputLabel","Εισήγαγε το όνομα χρήστη (μέχρι 15 χαρακτήρες)"},
            {"add","Προσθήκη"}
    };
}
