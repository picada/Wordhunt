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

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> commands = sqliteCommands();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String command : sqliteCommands()) {
                System.out.println("Running command >> " + command);
                st.executeUpdate(command);
            }

        } catch (Throwable t) {

            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteCommands() {
        ArrayList<String> commands = new ArrayList<>();

        commands.add("CREATE TABLE User (username varchar(10) PRIMARY KEY, name varchar(50))");
        commands.add("CREATE TABLE Score (id integer PRIMARY KEY, user varchar(10), score integer, time date, FOREIGN KEY (user) REFERENCES User(username))");

        return commands;
    }
}
