/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author katamila
 */
public class Game {

    private int points;
    private int time;
    private List<String> wordlist;
    private List<String> collectedWords;
    private Board board;
    private List<String> currentword;
    private boolean gameOn;

    public Game(Board board, String wordlist) {
        this.board = board;
        this.board.setBoard();
        this.wordlist = new ArrayList<String>();
        this.currentword = new ArrayList<String>();
        this.collectedWords = new ArrayList<String>();
        setWordlist(wordlist);
        this.time = 120;
        this.gameOn = false;
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
    
    public void setWordlist(ArrayList<String> words) {
        this.wordlist = wordlist;
    }
    
        public List<String> getCollectedWords() {
        return collectedWords;
    }

    public void mixBoard() {
        board.setBoard();
        if (points <= 10) {
            points = 0;
        } else {
            points -= 10;
        }
    }

    public Board getBoard() {
        return board;
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
        this.time--;
    }

    public String showTimeMinSec() {
        int minutes = this.time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public List<String> getWordlist() {
        return wordlist;
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
