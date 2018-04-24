/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author mila
 */
public class GameTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

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

        game = new Game(2, 2, "sanalista.txt");
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
    public void constuctorCreatesCorrectSizedBoard() {
        Character[][] board = game.getBoard();
        assertEquals(2, board.length);
        assertEquals(2, board[0].length);
    }

    @Test
    public void setBoardFillsTheWholeBoard() {
        assertTrue(game.getBoard()[0][0] != null);
        assertTrue(game.getBoard()[0][1] != null);
        assertTrue(game.getBoard()[1][0] != null);
        assertTrue(game.getBoard()[1][1] != null);
    }
    
    @Test 
    public void mixBoardReducesPoints() {
        game.setPoints(20);
        game.mixBoard();
        assertEquals(10, game.getPoints());
        game.mixBoard();
        assertEquals(0, game.getPoints());
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
    public void isNextToFindsAllNeighbours() {
        game = new Game(10, 10, "sanalista.txt");
        game.setCurrentx(3);
        game.setCurrenty(4);
        assertTrue(game.isNextTo(4, 4));
        assertTrue(game.isNextTo(4, 5));
        assertTrue(game.isNextTo(3, 5));
        assertTrue(game.isNextTo(2, 5));
        assertTrue(game.isNextTo(2, 4));
        assertTrue(game.isNextTo(2, 3));
        assertTrue(game.isNextTo(3, 3));
        assertTrue(game.isNextTo(4, 3));
    }
    
    @Test
    public void isNextToRecognizesFalseNeighbours() {
        game = new Game(10, 10, "sanalista.txt");
        game.setCurrentx(3);
        game.setCurrenty(4);
        assertFalse(game.isNextTo(0, 0));
        assertFalse(game.isNextTo(2, 2));
        assertFalse(game.isNextTo(6, 6));
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
    
    // not the perfect test: since the method is using random, it's basically possible that all the cells remain the same
    // after random - highly unlikely though
    
    @Test
    public void newRandomLetterChangesCellContent() {
        Character a = game.getBoard()[0][0];
        Character b = game.getBoard()[0][1];
        Character c = game.getBoard()[1][1];
        game.newRandomLetter(0, 0);
        game.newRandomLetter(0, 1);
        game.newRandomLetter(1, 1);
        Character d = game.getBoard()[0][0];
        Character e = game.getBoard()[0][1];
        Character f = game.getBoard()[1][1];
        
        boolean result = (a == d && b == e && c == f);
        
        assertFalse(result);    
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
    public void buildStringReturnsCollectStringFromList() {
        game.getCurrentword().add("c");
        game.getCurrentword().add("a");
        game.getCurrentword().add("t");
        assertEquals("cat", game.buildString(game.getCurrentword()));
        game.getCurrentword().clear();
        game.getCurrentword().add("d");
        game.getCurrentword().add("o");
        game.getCurrentword().add("g");
        assertEquals("dog", game.buildString(game.getCurrentword()));
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
