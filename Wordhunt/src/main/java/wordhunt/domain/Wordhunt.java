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
import wordhunt.database.ScoreDao;
import wordhunt.database.UserDao;

/**
 *
 * @author katamila
 */
public class Wordhunt {

    private UserDao users;
    private ScoreDao scores;
    private User user;
    private Game game;

    public Wordhunt(UserDao userDao, ScoreDao scoreDao) {
        this.users = userDao;
        this.scores = scoreDao;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(int width, int height, String wordlist) {
        this.game = new Game(width, height, wordlist);
    }

    public boolean login(String username) throws Exception {
        User user = users.findByUsername(username);
        if (user == null) {
            return false;
        }

        this.user = user;

        return true;
    }

    public User getLoggedUser() {
        return user;
    }

    public void logout() {
        user = null;
    }

    public boolean createUser(String username, String name) throws Exception {
        if (users.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        users.create(user);

        return true;
    }

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
    
    public boolean createScore(int points, User user, LocalDate date) throws Exception {
        if (!this.getGame().gameOver()) {
            return false;
        }
        Score score = new Score(points, user, date);
        scores.create(score);

        return true;
    }

}
