/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.util.Random;

/**
 *
 * @author mila
 */
public class Board {

    private Character[][] board;
    private int currentx;
    private int currenty;
    private String chars;

    public Board(int width, int height) {
        board = new Character[width][height];
        chars = "aaaaaabcdeeeeeeefghhhhiieeiijjjjkkkkllllmmmmnnnnnnooooopppppqrrrrrssssstttttyyyuuuuuvxyzåäääöö";

    }
    
    /**
     *
     * Sets up the game board by inserting a random letter in each cell of the
     * Character[][] table
     *
     */

    public void setBoard() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = chars.charAt(new Random().nextInt(chars.length()));
            }
        }
    }
    
        /**
     *
     * Inserts a random letter to the cell [x][y] of the board
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */


    public void newRandomLetter(int x, int y) {
        board[x][y] = chars.charAt(new Random().nextInt(chars.length()));
    }
    
    /**
     *
     * The method checks if the given (x,y) coordinate is a neigbour to the
     * (currentx,currenty) cell in the Chararcter[][] table
     *
     * @param x the x coordinate
     * @param y the y coordinate
     *
     * @return true if (x,y) is next to (currentx, currenty), false if not
     * 
     */


    public boolean isNextTo(int x, int y) {
        for (int i = y - 1; i < y + 2; i++) {
            if (i < 0 || i >= board.length) {
                continue;
            }
            for (int j = x - 1; j < x + 2; j++) {
                if (j < 0 || j >= board[i].length || (x == j && y == i)) {
                    continue;
                }
                if (j == currentx && i == currenty) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *
     * Getter for the current board
     *
     * @return current board as Character[][]
     */


    public Character[][] getBoard() {
        return board;
    }
    
     /**
     *
     * Getter for currentx
     *
     * @return the current saved x coordinate on the board
     * 
     */


    public int getCurrentx() {
        return currentx;
    }
    
        /**
     *
     * Sets a new x coordinate as currentx
     *
     * @param currentx the new x coordinate
     * 
     */


    public void setCurrentx(int currentx) {
        this.currentx = currentx;
    }
    
    /**
     *
     * Getter for currenty
     *
     * @return the current saved y coordinate on the board
     * 
     */


    public int getCurrenty() {
        return currenty;
    }
    
    
    /**
     *
     * Sets a new y coordinate as currentx
     *
     * @param currenty the new y coordinate
     * 
     */


    public void setCurrenty(int currenty) {
        this.currenty = currenty;
    }

}
