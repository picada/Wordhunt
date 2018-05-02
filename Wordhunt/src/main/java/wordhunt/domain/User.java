/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.domain;

/**
 *
 * @author katamila
 * This class represents the user of the game
 */

public class User {
    
    private String name;
    private String username;
    
    public User(String username, String name) {
        this.name = name;
        this.username = username;
    }

    /**
     *
     * Getter for the user's name
     * 
     * @return name
     * 
     */
    
    public String getName() {
        return name;
    }
    
    /**
     *
     * Setter for the username
     * 
     * @param name name as a String
     * 
     */

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *
     * Getter for the username
     * 
     * @return username
     * 
     */

    public String getUsername() {
        return username;
    }

    /**
     *
     * Setter for the username
     * 
     * @param username username as a String
     * 
     */
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     *
     * Checks if two objects are equal by checking if both are Users
     * and if the usernames match
     * 
     * @param obj the comparable object
     * 
     */
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User other = (User) obj;
        return username.equals(other.username);
    }
    
    
}
