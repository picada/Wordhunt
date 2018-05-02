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

    /**
     *
     * Reads the given wordlist from a file and saves it as the wordlist for the
     * game
     *
     * @param words the path of the file
     *
     */
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

    /**
     *
     * Sets the given ArrayList as the game's wordlist
     *
     * @param words new wordlist
     *
     */
    public void setWordlist(ArrayList<String> words) {
        this.wordlist = words;
    }

    /**
     *
     * Shuffles the board and reduces the player's points
     *
     */
    public void mixBoard() {
        board.setBoard();
        if (points <= 10) {
            points = 0;
        } else {
            points -= 10;
        }
    }

    /**
     *
     * Getter for the current board
     *
     * @return current Board
     *
     */
    public Board getBoard() {
        return board;
    }

    /**
     *
     * Takes the given letter as a String and adds it to the list of currently
     * collected letters/word
     *
     * @param s The letter the player has selected
     *
     */
    public void collectLetter(String s) {
        this.currentword.add(s);
    }

    /**
     *
     * Builds a solid String out of the elements in the currentword list
     *
     *
     * @return a String built of all the characters in the currentword list
     *
     */
    public String buildString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < currentword.size(); i++) {
            s.append(currentword.get(i));
        }
        return s.toString();
    }

    /**
     *
     * Checks if the given word is found in the game's wordlist
     *
     * @param word the word the player submits
     *
     * @return true if wordlist contains the given word, false if it doesn't
     *
     */
    public boolean isWord(String word) {
        if (wordlist.contains(word)) {
            collectedWords.add(word);
            currentword.clear();
            return true;
        }
        return false;
    }

    /**
     *
     * Checks if the given word is already in the collectedWords list
     *
     * @param word the word the player submits
     *
     * @return true if collectedWords doesn't contain the given word, false if
     * it does
     *
     */
    public boolean isNewWord(String word) {
        if (!collectedWords.contains(word)) {
            return true;
        }
        return false;
    }

    /**
     *
     * Getter for the collectedWords list
     *
     * @return the list of all the collected valid words
     *
     */
    public List<String> getCollectedWords() {
        return collectedWords;
    }

    /**
     *
     * Getter for the currentWord list
     *
     * @return the list of all the collected characters
     *
     */
    public List<String> getCurrentword() {
        return currentword;
    }

    /**
     *
     * Getter for the current points
     *
     * @return the player's current points
     *
     */
    public int getPoints() {
        return points;
    }

    /**
     *
     * Takes the given word length and adds points with the given formula (at
     * this point multiplication by two)
     *
     * @param wordLength length of the submitted word as int
     *
     */
    public void setPoints(int wordLength) {
        this.points += wordLength * 2;
    }

    /**
     *
     * Getter for the current time
     *
     * @return the current time left in the game
     *
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * Setter for the time
     *
     * @param time new time as seconds
     *
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     *
     * Reduces the time by one second
     *
     */
    public void tick() {
        this.time--;
    }

    /**
     *
     * Formats the time into mm:ss
     *
     * @return the time left in the game in mm:ss
     *
     */
    public String showTimeMinSec() {
        int minutes = this.time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     *
     * Getter for the game's current wordlist
     *
     * @return the current wordlist as a list of Strings
     *
     */
    public List<String> getWordlist() {
        return wordlist;
    }

    /**
     *
     * Switches gameOn to true when a new game starts
     *
     */
    public void startGame() {
        this.gameOn = true;
    }

    /**
     *
     * Checks if there's still time left and if not, changes gameOn to false
     *
     * @return true if time is greater than 0, else false
     *
     */
    public boolean gameOver() {
        if (time <= 0) {
            this.gameOn = false;
            return true;
        }
        return false;
    }

    /**
     *
     * Getter for the game status
     *
     * @return true if gameOn, else false
     *
     */
    public boolean getGameOn() {
        return this.gameOn;
    }

}
