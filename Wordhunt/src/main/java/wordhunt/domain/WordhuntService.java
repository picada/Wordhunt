/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import wordhunt.database.ScoreDao;
import wordhunt.database.UserDao;

/**
 *
 * @author katamila
 */
public class WordhuntService {

    private UserDao users;
    private ScoreDao scores;
    private User user;
    private Game game;
    

    public WordhuntService(UserDao userDao, ScoreDao scoreDao) {
        this.users = userDao;
        this.scores = scoreDao;
    }
    
    /**
     *
     * Getter for the current game
     * 
     * @return current game
     * 
     */

    public Game getGame() {
        return this.game;
    }
    
    /**
     *
     * Creates a new Game and sets it as the current game
     * 
     * @param width the width of the board
     * @param height the height of the board
     * @param wordlist the wordlist to be used in the game
     * 
     */

    public void setGame(int width, int height, String wordlist) {
        this.game = new Game(new Board(width, height), wordlist);
    }
    
    /**
     *
     * Checks if the user exists in the database
     * 
     * @param username the username that the user enters
     * 
     * @return true if a match for the username is found from the database, else false 
     * 
     * 
     */

    public boolean login(String username) throws Exception {
        User user = users.findByUsername(username);
        if (user == null) {
            return false;
        }

        this.user = user;

        return true;
    }

    /**
     *
     * Getter for the current logged user
     * 
     * @return currently logged user
     * 
     */

    
    public User getLoggedUser() {
        return user;
    }
    
    /**
     *
     * Logs out the current user and sets user to null
     * 
     * 
     */

    public void logout() {
        user = null;
    }
    
    /**
     *
     * Creates a new user and saves it to the database if the entered username isn't
     * already at use
     * 
     * @param username new username entered by the user
     * @param name the user's name entered by the user
     * 
     * @return true if the username is new, else false
     * 
     * 
     */

    public boolean createUser(String username, String name) throws Exception {
        if (users.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        users.create(user);

        return true;
    }

    /**
     *
     * Reads the rules of the game from a file and creates a String
     * 
     * @param file the file path as a String
     * 
     * @return String from the contents of the given file
     * 
     */
    
    public String rulesFromFile(String file) {
        StringBuilder s = new StringBuilder();

        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream in = cl.getResourceAsStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                s.append(line + "\n");
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Could not read file " + file + e);

        }
        return s.toString();
    }

    /**
     *
     * Creates a new score after the game and saves it to the database
     * 
     * @param points the points at the end of the game
     * @param user the user who played the game
     * @param date current date
     * 
     * @return false if game is not over, true if it is
     * 
     * 
     */
    
    public boolean createScore(int points, User user, LocalDate date) throws Exception {
        if (!this.getGame().gameOver()) {
            return false;
        }
        Score score = new Score(points, user, date);
        scores.create(score);

        return true;
    }
    
    /**
     *
     * Brings a list of the logged users top10 scores from the database and 
     * creates a String of the scores showing the points and the date
     * 
     * 
     * @return the logged user's top10 scores as a String
     * 
     */

    public String printUserTopTen() throws Exception {
        List<Score> topTen = scores.userTopTen(this.getLoggedUser());
        StringBuilder s = new StringBuilder();
        s.append("Sija\t" + "Pisteet\t" + "Pvm\n");
        for (int i = 0; i < topTen.size(); i++) {
            int place = i + 1;
            s.append((i + 1) + ".\t" + topTen.get(i).getPoints()
                    + "\t\t" + topTen.get(i).getDate() + "\n");
        }
        return s.toString();
    }
    
    /**
     *
     * Brings a list of the top10 scores of all users from the database and 
     * creates a String of the scores showing the username, the points and the date
     * 
     * 
     * @return top10 scores among all users as a String
     * 
     * 
     */

    public String printTopTen() throws Exception {
        List<Score> topTen = scores.topTen();
        StringBuilder s = new StringBuilder();
        s.append("Sija\t" + "Käyttäjä\t" + "Pisteet\t" + "Pvm\n");
        for (int i = 0; i < topTen.size(); i++) {
            int place = i + 1;
            s.append((i + 1) + ".\t" + topTen.get(i).getUser().getUsername() + "\t\t"
                    + topTen.get(i).getPoints() + "\t\t" + topTen.get(i).getDate() + "\n");
        }
        return s.toString();
    }

}
