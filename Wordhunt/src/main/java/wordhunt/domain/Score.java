/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.time.LocalDate;


/**
 *
 * @author katamila
 */

public class Score {
    
    private int id;
    private LocalDate date;
    private int points;
    private User user;
    
    public Score(int points, User player, LocalDate date) {
        this.points = points;
        this.user = player;
        this.date = date;
    }
    
    /**
     *
     * Getter for the score's date
     * 
     * @return the date when the score was submitted
     * 
     */

    public LocalDate getDate() {
        return date;
    }
    
    /**
     *
     * Getter for the score's points
     * 
     * @return the saved score
     * 
     */

    public int getPoints() {
        return points;
    }

    /**
     *
     * Getter for the User who owns the score
     * 
     * @return the User that owns the score
     * 
     */
    
    public User getUser() {
        return user;
    }
    
    
}
