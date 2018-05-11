package wordhunt.database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wordhunt.domain.User;

/**
 *
 * @author katamila
 */
public class UserDaoTest {
    
    private UserDao users;
    private Database db;
    
    public UserDaoTest() {
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
    }
    
    @Test
    public void createWorks() throws Exception {
        User one = new User("chuckee", "Chuck Norris");
        User two = new User("stevie<3", "Steven Seagal");
        assertEquals(one, users.create(one));
        assertEquals(two, users.create(two));
    }
    
    @Test
    public void existingUserIsFound() throws Exception {
        User one = new User("chuckee", "Chuck Norris");
        users.create(one);
        assertEquals(one, users.findByUsername("chuckee"));
    }
    
    @Test
    public void nonExistingUserIsFound() throws Exception {
        User user = users.findByUsername("notthere");
        assertEquals(null, user);
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
