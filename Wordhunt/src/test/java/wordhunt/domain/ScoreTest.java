/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author katamila
 */
public class ScoreTest {
    
    private Score one;
    private Score two;
    
    public ScoreTest() {
    }
    
    @Before
    public void setUp() {
        one = new Score(200, new User("username1", "test1"), LocalDate.now());
        two = new Score(100, new User("username2", "test2"), LocalDate.now());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorWorks() {
        assertTrue(one!=null);
    }
    
    @Test
    public void dateSetsRightWhenConstructed() {
        assertEquals(one.getDate(), LocalDate.now());
    }

}
