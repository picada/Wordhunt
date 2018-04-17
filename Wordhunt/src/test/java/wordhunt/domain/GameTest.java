/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mila
 */
public class GameTest {
    
    private Game game;
    private User user;
    
    public GameTest() {
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() {
        game = new Game();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorWorks() {
        assertTrue(game!=null);
    }
    
    @Test
    public void wordlistNotEmptyAfterConstruction() {
        assertTrue(!game.getWordlist().isEmpty());
    }
    
    @Test
    public void currentWordNotNullAfterConstruction() {
        assertTrue(game.getWordlist()!=null);
    }
    
    @Test
    public void boardNotNullAfterConstruction() {
        assertTrue(game.getBoard()!=null);
    }

    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
