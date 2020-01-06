package gr.auth.csd.sudoku.gui;

import java.util.ListResourceBundle;

public class Language_en_CA extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return contents;
    }
    private Object[][] contents = {
            { "hint", "Hint" },
            { "greeting","Let's play sudoku!" },
            {"classic","Classic Sudoku"},
            { "kill","Killer Sudoku" },
            {"duidoku","Duidoku"},
            {"settings","Settings"},
            {"userB", "Select user"},
            {"hintwind" ,"The following are acceptable"},
            {"languageSetting","Select language"},
            {"existinUser","Existing users: "},
            {"addUser","Add user"},
            {"statisticButton","View Stats"},
            {"selectUser","Select user"},
            {"noUser","Play without a user"},
            {"stats","Statistics"},
            {"user","User "},
            {"has"," has "},
            {"wins"," wins and "},
            {"loses"," losses"},
            {"noUserWarn","Please select a user first"},
            {"inputLabel","Enter your username (up to 15 characters)"},
            {"add","Add"}
    };
}
