/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.util.Random;

/**
 *
 * @author katamila
 */
public class Puzzle {

    private Character[][] board;

    public Puzzle(int a, int b) {
        board = new Character[a][b];
    }

    public void setBoard() {
        String chars = "abcdefghijklmopqrstuvxyzåäö";
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = chars.charAt(new Random().nextInt(chars.length()));
            }
        }
    }

    public Character[][] getBoard() {
        return board;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
         for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                s.append(board[x][y] + " "); 
            }
            s.append("\n");
        }
         return s.toString();
    }

}
