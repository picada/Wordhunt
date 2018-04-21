/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author katamila
 */
public class Score {
    
    private int id;
    private LocalDate date;
    private int score;
    private User user;
    
    public Score(int points, User player) {
        this.score = points;
        this.user = player;
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
    
    
}
