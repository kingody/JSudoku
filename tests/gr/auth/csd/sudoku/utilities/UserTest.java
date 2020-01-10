package gr.auth.csd.sudoku.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void modifyUser() {
        User fail = User.loadUser("doesntExist");
        assertNull(fail);

        User newuser = User.newUser("newTestUser");

        User user = User.loadUser("testUser");
        assertEquals("testUser", user.getUsername());

        ArrayList<User> users = User.loadAll();
        assertNotNull(users);
        assertEquals(2, users.size());


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
        File file = new File("Users/newTestUser.txt");
        file.deleteOnExit();
    }
}