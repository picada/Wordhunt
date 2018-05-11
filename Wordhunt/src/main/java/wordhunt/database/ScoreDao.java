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
import wordhunt.domain.Score;
import wordhunt.domain.User;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author katamila
 */
public class ScoreDao implements Dao<Score, Integer> {

    private Database database;
    private UserDao userdao;

    public ScoreDao(Database db) {
        database = db;
        this.userdao = new UserDao(db);
    }
    
     /**
     *
     * Searches for a specific score from the database by the given key/id
     *
     * @param key the score's primary key
     * 
     * @return a new Score object fetched from the database based on the key
     * 
     */

    @Override
    public Score findOne(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT user, score, time FROM Score WHERE id = ?");
            stmt.setInt(1, key);

            ResultSet result = stmt.executeQuery();

            if (!result.next()) {
                return null;
            }

            return new Score(result.getInt("score"), userdao.findByUsername(result.getString("user")), result.getDate("time").toLocalDate());
        }
    }

    
    @Override
    public List<Score> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * Finds and lists the top scores (max 10) for the given user
     *
     * @param user The user who is logged in
     * 
     * @return list of the top scores (max 10)
     * 
     */

    public ArrayList<Score> userTopTen(User user) throws SQLException {

        ArrayList<Score> topTen = new ArrayList<Score>();

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT user, score, time FROM Score WHERE user = ? "
                    + "ORDER BY score DESC "
                    + "LIMIT 10");

            stmt.setString(1, user.getUsername());

            ResultSet result = stmt.executeQuery();

            while (result.next()) {

                topTen.add(new Score(result.getInt("score"),
                        userdao.findByUsername(result.getString("user")), result.getDate("time").toLocalDate()));
            }

        }
        return topTen;
    }
    
    /**
     *
     * Finds and lists the top scores (max 10) from all users
     *
     * 
     * @return list of the top scores among all users (max 10)
     * 
     */
    
    public ArrayList<Score> topTen() throws SQLException {

        ArrayList<Score> topTen = new ArrayList<Score>();

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT user, score, time FROM Score "
                    + "ORDER BY score DESC "
                    + "LIMIT 10");

            ResultSet result = stmt.executeQuery();

            while (result.next()) {

                topTen.add(new Score(result.getInt("score"),
                        userdao.findByUsername(result.getString("user")), result.getDate("time").toLocalDate()));
            }

        }
        return topTen;
    }
    
    /**
     *
     * Saves a new score to the database
     *
     * @param score The score that is created when the game ends
     * 
     * @return a new Score fetched from the database
     * 
     */

    @Override
    public Score create(Score score) throws SQLException {

        int scoreid;

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Score (id, user, score, time) VALUES (?, ?, ?, ?)");
            stmt.setString(2, score.getUser().getUsername());
            stmt.setInt(3, score.getPoints());
            stmt.setDate(4, Date.valueOf(score.getDate()));
            stmt.executeUpdate();
            stmt = conn.prepareStatement("SELECT MAX(id) AS id FROM Score");
            ResultSet rs = stmt.executeQuery();
            scoreid = rs.getInt("id");
            rs.close();
        }
        return findOne(scoreid);
    }


}
