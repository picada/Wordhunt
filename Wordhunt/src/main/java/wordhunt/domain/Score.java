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
//        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMdd");
//        this.date = f.toString();
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    

    public int getPoints() {
        return points;
    }
//
//    public void setScore(int points) {
//        this.points = points;
//    }
//
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
    
    
}
