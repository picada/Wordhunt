package wordhunt.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class UserTest {
    
    public UserTest() {
    }
    
    private User one;
    private User two;
    private User three;
    
    
    @Before
    public void setUp() {
        one = new User("chuckee", "Chuck Norris");
        two = new User("stevie<3", "Steven Seagal");
        three = new User("chuckee", "Chuck E. Cheese");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorWorks() {
        assertTrue(one!=null);
    }
    
    @Test
    public void nameGetterReturnsCorrectValue() {
        assertEquals("Chuck Norris", one.getName());
        assertEquals("Steven Seagal", two.getName());
    }
    
    @Test
    public void usernameGetterReturnsCorrectValue() {
        assertEquals("chuckee", three.getUsername());
        assertEquals("stevie<3", two.getUsername());
    }
    
     @Test
    public void nameSetterSetsCorrectValue() {
        one.setName("Chuck");
        two.setName("Stevie");
        assertEquals("Chuck", one.getName());
        assertEquals("Stevie", two.getName());
    }
    
    
    @Test
    public void usernameSetterReturnsCorrectValue() {
        one.setUsername("mrChuckToYou");
        two.setUsername("Seagul");
        assertEquals("mrChuckToYou", one.getUsername());
        assertEquals("Seagul", two.getUsername());
    }

    @Test
    public void equalsWhenSameUsername() {
        assertTrue(one.equals(three));
    }
    
    @Test
    public void notEqualWhenDifferentUsername() {
        assertFalse(one.equals(two));
    }
    
    @Test
    public void notEqualWhenOnlyContains() {
        User four = new User("chucke", "Cthulhu");
        assertFalse(one.equals(four));
    }
    
    @Test
    public void notEqualWhenDifferentType() {
        Object o = new Object();
        assertFalse(one.equals(o));
    }
    
    
    
}
