/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

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
    
    public void setGame() {
        this.game = new Game();
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
    

}
