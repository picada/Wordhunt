/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
    
    /**
     *
     * Creates a connection to this.databasAddress
     *
     * @return connection to database
     * 
     */

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
     /**
     *
     * Init method for the database, performs given commands (in this case creates
     * necessary tables if they don't exist)
     *
     * 
     */

    public void init() {
        List<String> commands = sqliteCommands();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String command : sqliteCommands()) {
                //System.out.println("Running command >> " + command);
                st.executeUpdate(command);
            }

        } catch (Throwable t) {

            System.out.println("Error >> " + t.getMessage());
        }
    }
    
     /**
     *
     * Creates a list of sqlite commands
     *
     * @return list of sqlite commands, in this case CREATE TABLE commands
     * 
     */

    private List<String> sqliteCommands() {
        ArrayList<String> commands = new ArrayList<>();

        commands.add("CREATE TABLE IF NOT EXISTS User (username varchar(8) PRIMARY KEY, name varchar(50))");
        commands.add("CREATE TABLE IF NOT EXISTS Score (id integer PRIMARY KEY, user varchar(8), score integer, time date, FOREIGN KEY (user) REFERENCES User(username))");

        return commands;
    }
    
}
