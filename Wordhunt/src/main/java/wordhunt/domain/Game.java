/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private List<String> collectedWords;
    private Character[][] board;
    private List<String> currentword;
    private int currentx;
    private int currenty;
    private boolean gameOn;

    public Game(int width, int height, String wordlist) {
        board = new Character[width][height];
        this.wordlist = new ArrayList<String>();
        this.currentword = new ArrayList<String>();
        this.collectedWords = new ArrayList<String>();
        setWordlist(wordlist);
        this.time = 10;
        setBoard();
        this.gameOn = false;
    }

    public List<String> getCollectedWords() {
        return collectedWords;
    }

    public void setWordlist(String words) {

        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream in = cl.getResourceAsStream(words);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                wordlist.add(line);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Could not read file " + words + e);
            e.printStackTrace();
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

    public void newRandomLetter(int x, int y) {
        String chars = "aaaabcdeeeeefghhhiiiijjjjkkkkllllmmmmnnnoooopppqrrrsssstttyyuuuvxyzåääöö";
        board[x][y] = chars.charAt(new Random().nextInt(chars.length()));
    }

    public void mixBoard() {
        setBoard();
        points -= 10;
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
                if (j == currentx && i == currenty) {
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
            collectedWords.add(word);
            currentword.clear();
            return true;
        }
        return false;
    }

    public boolean isNewWord(String word) {
        if (!collectedWords.contains(word)) {
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
        this.points += points;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public void tick() {
        this.time --;
    }
    
    public String showTimeMinSec() {
        int minutes = this.time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public List<String> getWordlist() {
        return wordlist;
    }

    public void setWordlist(List<String> wordlist) {
        this.wordlist = wordlist;
    }
    
    public void startGame() {
        this.gameOn = true;
    }

    public boolean gameOver() {
        if (time <= 0) {
            this.gameOn = false;
            return true;    
        }
        return false;
    }
    
    public boolean getGameOn() {
        return this.gameOn;
    }

}
