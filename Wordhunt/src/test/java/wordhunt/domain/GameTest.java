/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.util.ArrayList;
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
//        
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() throws Exception {

        game = new Game(new Board(2, 2), "sanalista.txt");
        game.setTime(120);
    }

    @Test
    public void wordlistNotNullOrEmptyAfterConstruction() {
        assertTrue(game.getWordlist() != null);
        assertTrue(!game.getWordlist().isEmpty());
    }

    @Test
    public void currentWordNotNullAfterConstruction() {
        assertTrue(game.getWordlist() != null);
    }

    @Test
    public void boardNotNullAfterConstruction() {
        assertTrue(game.getBoard() != null);
    }

    
    @Test 
    public void mixBoardReducesPoints() {
        game.setPoints(20);
        game.mixBoard();
        assertEquals(30, game.getPoints());
        game.mixBoard();
        assertEquals(20, game.getPoints());
    }
    
    @Test 
    public void mixBoardDoesntTakePointsBelowZero() {
       game.mixBoard();
       assertEquals(0, game.getPoints());
       game.setPoints(2);
       game.mixBoard();
       assertEquals(0, game.getPoints());
    }

    // not the most optimized test for this, especially if at some point more words are added to the list
    @Test
    public void setWordListCollectsAllFromFile() {
        assertEquals(94110, game.getWordlist().size());
    }
    
    
    @Test 
    public void isWordRecognizesWords() {
        ArrayList<String> testWords = new ArrayList<String>();
        game.setWordlist(testWords);
        testWords.add("lets");
        testWords.add("test");
        testWords.add("this");
        testWords.add("thing");
        assertTrue(game.isWord("lets"));
        assertTrue(game.isWord("test"));
        assertTrue(game.isWord("this"));
        assertTrue(game.isWord("thing"));
    }
    
    @Test 
    public void recognizesFalseWords() {
        ArrayList<String> testWords = new ArrayList<String>();
        game.setWordlist(testWords);
        testWords.add("lets");
        testWords.add("test");
        testWords.add("this");
        testWords.add("thing");
        assertFalse(game.isWord("alets"));
        assertFalse(game.isWord("testa"));
        assertTrue(game.isWord("this"));
        assertTrue(game.isWord("thing"));
    }
    
    @Test 
    public void isNewWordsRecognizesDoubles() {
        game.getCollectedWords().add("lets");
        game.getCollectedWords().add("test");
        game.getCollectedWords().add("this");
        game.getCollectedWords().add("thing");
        assertFalse(game.isNewWord("lets"));
        assertFalse(game.isNewWord("test"));
        assertTrue(game.isNewWord("thisa"));
        assertTrue(game.isNewWord("thinga"));
    }
    
    
    @Test
    public void collectLetterCollects() {
        game.collectLetter("c");
        assertEquals("c", game.getCurrentword().get(game.getCurrentword().size()-1));
        game.collectLetter("a");
        assertEquals("a", game.getCurrentword().get(game.getCurrentword().size()-1));
        game.collectLetter("t");
        assertEquals("t", game.getCurrentword().get(game.getCurrentword().size()-1));
    }
    
    @Test 
    public void buildStringReturnsCorrectStringFromList() {
        game.getCurrentword().add("c");
        game.getCurrentword().add("a");
        game.getCurrentword().add("t");
        assertEquals("cat", game.buildString());
        game.getCurrentword().clear();
        game.getCurrentword().add("d");
        game.getCurrentword().add("o");
        game.getCurrentword().add("g");
        assertEquals("dog", game.buildString());
    } 
    
    @Test
    public void timeShowsRight() {
        assertEquals("02:00", game.showTimeMinSec());
        game.setTime(60);
        assertEquals("01:00", game.showTimeMinSec());
        game.setTime(38);
        assertEquals("00:38", game.showTimeMinSec());
    }
    
    @Test 
    public void gameOverReturnsCorrectValue() {
        assertFalse(game.getGameOn());
        game.startGame();
        assertTrue(game.getGameOn());
        game.setTime(0);
        game.gameOver();
        assertFalse(game.getGameOn());
    }
    
    @Test 
    public void tickWorks() {
        game.tick();
        assertEquals(119, game.getTime());
        game.tick();
        game.tick();
        assertEquals(117, game.getTime());
    }
    

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
