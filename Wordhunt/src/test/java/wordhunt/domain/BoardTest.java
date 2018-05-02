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
public class BoardTest {
    
    private Board board;
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.board = new Board(2,2);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constuctorCreatesCorrectSizedBoard() {
        assertEquals(2, board.getBoard().length);
        assertEquals(2, board.getBoard()[0].length);
    }

    @Test
    public void setBoardFillsTheWholeBoard() {
        board.setBoard();
        assertTrue(board.getBoard()[0][0] != null);
        assertTrue(board.getBoard()[0][1] != null);
        assertTrue(board.getBoard()[1][0] != null);
        assertTrue(board.getBoard()[1][1] != null);
    }
    
    @Test
    public void isNextToFindsAllNeighbours() {
        board = new Board(10, 10);
        board.setBoard();
        board.setCurrentx(3);
        board.setCurrenty(4);
        assertTrue(board.isNextTo(4, 4));
        assertTrue(board.isNextTo(4, 5));
        assertTrue(board.isNextTo(3, 5));
        assertTrue(board.isNextTo(2, 5));
        assertTrue(board.isNextTo(2, 4));
        assertTrue(board.isNextTo(2, 3));
        assertTrue(board.isNextTo(3, 3));
        assertTrue(board.isNextTo(4, 3));
    }
    
    @Test
    public void isNextToRecognizesFalseNeighbours() {
        board = new Board(10, 10);
        board.setBoard();
        board.setCurrentx(3);
        board.setCurrenty(4);
        assertFalse(board.isNextTo(0, 0));
        assertFalse(board.isNextTo(2, 2));
        assertFalse(board.isNextTo(6, 6));
    }
    
    // not the perfect test: since the method is using random, it's basically possible that all the cells remain the same
    // after random - highly unlikely though
    
    @Test
    public void newRandomLetterChangesCellContent() {
        Character a = board.getBoard()[0][0];
        Character b = board.getBoard()[0][1];
        Character c = board.getBoard()[1][1];
        board.newRandomLetter(0, 0);
        board.newRandomLetter(0, 1);
        board.newRandomLetter(1, 1);
        Character d = board.getBoard()[0][0];
        Character e = board.getBoard()[0][1];
        Character f = board.getBoard()[1][1];
        
        boolean result = (a == d && b == e && c == f);
        
        assertFalse(result);    
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
