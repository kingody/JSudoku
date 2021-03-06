package gr.auth.csd.sudoku.utilities.locale.languages;

import gr.auth.csd.sudoku.utilities.locale.Language;

/**
 * This class contains the english translation
 */
public class Language_en_US extends Language {

    public Language_en_US() {
        charSet = new char[]{' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        contents = new Object[][] {
                {"menuTitle", "Sudoku"},
                {"greeting", "Let's play Sudoku!"},
                {"classic", "Classic Sudoku"},
                {"kill", "Killer Sudoku"},
                {"duidoku", "Duidoku"},
                {"hint", "Hint"},
                {"acceptable", "The following are acceptable:"},
                {"settings", "Settings"},
                {"wordoku", "Wordoku"},
                {"english", "English"},
                {"greek", "Greek"},
                {"userB", "Select user"},
                {"languageSelect", "Select language"},
                {"existingUser", "Existing users: "},
                {"addUser", "Add user"},
                {"newUser", "New user"},
                {"userExists", " already exists"},
                {"currentUser", "Current User: "},
                {"statisticButton", "View Stats"},
                {"selectUser", "Select user"},
                {"noUser", "Play without a user"},
                {"stats", "Statistics"},
                {"user", "User "},
                {"has", " has "},
                {"wins", " wins and "},
                {"loses", " losses"},
                {"noUserWarn", "Please select a user first"},
                {"add", "Add"},
                {"inputLabel", "Enter your username (up to 15 characters)"},
                {"wonMessage","Congrats, you won!!"},
                {"lostMessage","Seems like you lost"},
                {"mainMenu","Return to main menu"}
        };
    }
}
