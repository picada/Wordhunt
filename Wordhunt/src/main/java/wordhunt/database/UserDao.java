/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import wordhunt.domain.User;

/**
 *
 * @author katamila
 */
public class UserDao implements Dao<User, Integer> {
    
    private Database database;
    
    public UserDao(Database db) {
        database = db;
    }
    
    @Override
    public User findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * Saves a new user to the database
     * 
     * @param user the new user to be saved
     * 
     * @return a new User fetched from the database
     * 
     */
    
    @Override
    public User create(User user) throws SQLException {

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (username, name) VALUES (?, ?)");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getName());
            stmt.executeUpdate();
        }

        return findByUsername(user.getUsername());
    }
    /**
     *
     * Finds a user from the database based on the username
     * 
     * @param username the given username
     * 
     * @return a new User fetched from the database based on the username
     * 
     */
    
    public User findByUsername(String username) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT username, name FROM User WHERE username = ?");
            stmt.setString(1, username);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }
            User user = new User(result.getString("username"), result.getString("name"));
            result.close();
            conn.close();
            return user;
        }
    }

}
