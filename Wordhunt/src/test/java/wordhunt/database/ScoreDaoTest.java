/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.database;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wordhunt.domain.Score;
import wordhunt.domain.User;

/**
 *
 * @author katamila
 */
public class ScoreDaoTest {

    private UserDao users;
    private ScoreDao scores;
    private Database db;

    public ScoreDaoTest() {
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
    }

    @Test
    public void createWorks() throws Exception {
        User test = new User("test", "testtest");
        users.create(test);
        Score one = new Score(200, test, LocalDate.now());
        Score testOne = scores.create(one);
        assertEquals(one.getUser(), testOne.getUser());
        assertEquals(one.getPoints(), scores.create(one).getPoints());
        assertEquals(one.getDate(), scores.create(one).getDate());
    }

    @Test
    public void userTopTenPointsCorrect() throws Exception {
        User test = new User("test", "testtest");
        ArrayList<Score> testList = new ArrayList<>();
        for (int i = 10; i > 0; i--) {
            testList.add(new Score(i, test, LocalDate.now()));
        }
        for (int i = 1; i <= 10; i++) {
            scores.create(new Score(i, test, LocalDate.now()));
        }
        ArrayList<Score> testTopTen = scores.userTopTen(test);
        assertTrue(testTopTen.size() == 10);
        for (int i = 0; i < 10; i++) {
            assertEquals(testList.get(i).getPoints(), testTopTen.get(i).getPoints());
        }
    }

    @Test
    public void TopTenPointsCorrect() throws Exception {
        User test = new User("test", "testtest");
        User testTwo = new User("testtest", "test");
        users.create(test);
        users.create(testTwo);
        
        ArrayList<Score> testList = new ArrayList<>();
        testList.add(new Score(20, test, LocalDate.now()));
        testList.add(new Score(15, test, LocalDate.now()));
        scores.create(new Score(20, test, LocalDate.now()));
        scores.create(new Score(15, test, LocalDate.now()));
        
        for (int i = 10; i > 2; i--) {
            testList.add(new Score(i, testTwo, LocalDate.now()));
        }
        for (int i = 3; i <= 10; i++) {
            scores.create(new Score(i, testTwo, LocalDate.now()));
        }
        
        ArrayList<Score> testTopTen = scores.topTen();
        System.out.println(testTopTen.size());
        assertTrue(testTopTen.size() == 10);
        
        for (int i = 0; i < 10; i++) {
            assertEquals(testList.get(i).getPoints(), testTopTen.get(i).getPoints());
            assertEquals(testList.get(i).getUser(), testTopTen.get(i).getUser());
        }
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
