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
    
    public Board(int width, int height) {
        board = new Character[width][height];
    }
    
    public void setBoard() {
        String chars = "aaaaabcdeeeeeefghhhhiiiijjjjkkkkllllmmmmnnnnnoooopppppqrrrrrssssstttttyyyuuuuuvxyzåääöö";
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = chars.charAt(new Random().nextInt(chars.length()));
            }
        }
    }
    
    public void newRandomLetter(int x, int y) {
        String chars = "aaaaabcdeeeeeefghhhhiiiijjjjkkkkllllmmmmnnnnnoooopppppqrrrrrssssstttttyyyuuuuuvxyzåääöö";
        board[x][y] = chars.charAt(new Random().nextInt(chars.length()));
    }
    
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

    public Character[][] getBoard() {
        return board;
    }

    public void setBoard(Character[][] board) {
        this.board = board;
    }

    public int getCurrentx() {
        return currentx;
    }

    public void setCurrentx(int currentx) {
        this.currentx = currentx;
    }

    public int getCurrenty() {
        return currenty;
    }

    public void setCurrenty(int currenty) {
        this.currenty = currenty;
    }
    
}
