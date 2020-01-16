package gr.auth.csd.sudoku.utilities;

import gr.auth.csd.sudoku.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void modifyUser() {
        User fail = User.loadUser("doesntExist");
        assertNull(fail);

        User user = User.newUser("testUser");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());

        user = User.loadUser("testUser");

        User.setCurrentUser(user);
        assertEquals(user, User.getCurrentUser());

        ArrayList<User> users = User.loadAll();
        assertNotNull(users);
        assertEquals(1, users.size());


        int oldWins = user.getWins(), oldLosses = user.getLosses();
        user.incrementWins();
        for (int i = 0; i < 5; i++)
            user.incrementLosses();

        assertEquals(oldWins + 1, user.getWins());
        assertEquals(oldLosses + 5, user.getLosses());

        user.addToSolved("classic1.txt");
        assertTrue(user.hasSolved("classic1.txt"));
    }

    @AfterAll
    static void deleteFile() {
        File file = new File("Users/testUser.txt");
        file.deleteOnExit();
    }
}