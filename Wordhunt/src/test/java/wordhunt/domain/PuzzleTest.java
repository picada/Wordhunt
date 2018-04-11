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
 * @author katamila
 */
public class PuzzleTest {
    
    public PuzzleTest() {
    }
    
    private Puzzle puzzle;
    
    @Before
    public void setUp() {
        puzzle = new Puzzle(10, 8);
    }
    
    @Test
    public void constuctorWorks() {
        assertTrue(puzzle!=null);
    }
    
    @Test
    public void constuctorCreatesCorrectSizedBoard() {
        Character[][] board = puzzle.getBoard();
        assertEquals(10, board.length);
        assertEquals(8, board[0].length);    
    }
    
    @Test
    public void setterFillsTheWholeBoard() {
        Puzzle puzzletwo = new Puzzle(2, 2);
        puzzletwo.setBoard();
        Character[][] board = puzzletwo.getBoard();
        assertTrue(board[0][0]!=null);
        assertTrue(board[0][1]!=null);
        assertTrue(board[1][0]!=null);
        assertTrue(board[1][1]!=null);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
