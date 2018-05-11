/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.sql.Connection;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wordhunt.database.Database;
import wordhunt.database.ScoreDao;
import wordhunt.database.UserDao;

/**
 *
 * @author katamila
 */
public class WordhuntServiceTest {

    private WordhuntService wordhunt;
    private UserDao users;
    private ScoreDao scores;
    private Database db;
    private User test;

    public WordhuntServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        db = new Database("jdbc:sqlite:src/main/resources/test.db");
        db.init();
        users = new UserDao(db);
        scores = new ScoreDao(db);
        wordhunt = new WordhuntService(users, scores);
        test = new User("test", "testtest");
        users.create(test);
    }

    @Test
    public void nonExistingUserCanLogIn() throws Exception {
        boolean result = wordhunt.login("none");
        assertFalse(result);

        assertEquals(null, wordhunt.getLoggedUser());
    }

    @Test
    public void existingUserCanLogIn() throws Exception {
        boolean result = wordhunt.login("test");
        assertTrue(result);

        User loggedIn = wordhunt.getLoggedUser();
        assertEquals("testtest", loggedIn.getName());
    }

    @Test
    public void loggedInUserCanLogout() throws Exception {
        wordhunt.login("test");
        wordhunt.logout();

        assertEquals(null, wordhunt.getLoggedUser());
    }

    @Test
    public void userCreationFailsIfUsernameNotUnique() throws Exception {
        boolean result = wordhunt.createUser("test", "ttt");
        assertFalse(result);
    }
    
    @Test
    public void userCreationWorks() throws Exception {
        boolean result = wordhunt.createUser("testtest", "test");
        assertTrue(result);
        assertFalse(users.findByUsername("testtest") == null);
    }
    
    @Test
    public void gameNotNullAfterSetGame() throws Exception {
        wordhunt.setGame(10, 10, "sanalista.txt");
        assertFalse(wordhunt.getGame() == null);
    }
    
    @Test 
    public void createScoreWorks() throws Exception {
        wordhunt.login("test");
        wordhunt.setGame(0, 0, "sanalista.txt");
        wordhunt.getGame().setTime(0);
        boolean result = wordhunt.createScore();
        assertTrue(result);
    }
    
    @Test
    public void createScoreDoesntWorkIfGameNotOver() throws Exception {
        wordhunt.login("test");
        wordhunt.setGame(0, 0, "sanalista.txt");
        wordhunt.getGame().startGame();
        boolean result = wordhunt.createScore();
        assertFalse(result);
    }

    @After
    public void tearDown() {
        try (Connection conn = db.getConnection()) {
            Statement st = conn.createStatement();
            st.executeUpdate("DROP TABLE User");
            st.executeUpdate("DROP TABLE Score");
            st.close();
            conn.close();
        } catch (Throwable t) {
            System.out.println("Error >> " + t.getMessage());
        }
    }

}
