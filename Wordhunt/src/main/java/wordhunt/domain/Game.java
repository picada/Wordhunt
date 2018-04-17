/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author katamila
 */
public class Game {

    private int points;
    private int time;
    private List<String> wordlist;
    private Character[][] board;
    private List<String> currentword;
    private int currentx;
    private int currenty;

    public Game() {
        board = new Character[10][10];
        this.wordlist = new ArrayList<String>();
        this.currentword = new ArrayList<String>();
        setWordlist("src/main/resources/sanalista.txt");
        this.time = 120;
        setBoard();
    }

    public void setWordlist(String words) {
        try {
            Files.lines(Paths.get(words)).forEach(word -> this.wordlist.add(word));
        } catch (Exception e) {
            System.out.println("Could not read file " + words + e);
        }
    }

    public void setBoard() {
        String chars = "aaaabcdeeeeefghhhiiiijjjjkkkkllllmmmmnnnoooopppqrrrsssstttyyuuuvxyzåääöö";
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = chars.charAt(new Random().nextInt(chars.length()));
            }
        }
    }

    public void mixBoard() {
        setBoard();
        time -= 20;
    }

    public Character[][] getBoard() {
        return board;
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


    public boolean isNextTo(int x, int y) {
        for (int i = y - 1; i < y + 2; i++) {
            if (i < 0 || i >= board.length) {
                continue;
            }
            for (int j = x - 1; j < x + 2; j++) {
                if (j < 0 || j >= board[i].length || (x == j && y == i)) {
                    continue;
                }
                if (board[j][i] == board[currentx][currenty]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void collectLetter(String s) {
        this.currentword.add(s);
    }

    public String buildString(List<String> chars) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < chars.size(); i++) {
            s.append(chars.get(i));
        }
        return s.toString();
    }

    public boolean isWord(String word) {
        if (wordlist.contains(word)) {
            points += word.length();
            currentword.clear();
            return true;
        }
        return false;
    }

    public List<String> getCurrentword() {
        return currentword;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<String> getWordlist() {
        return wordlist;
    }

    public void setWordlist(List<String> wordlist) {
        this.wordlist = wordlist;
    }

    public boolean gameOver() {
        if (time <= 0) {
            return true;
        }
        return false;
    }

}
