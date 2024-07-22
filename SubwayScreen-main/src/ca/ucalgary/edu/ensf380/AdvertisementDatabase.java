package ca.ucalgary.edu.ensf380;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The AdvertisementDatabase class is responsible for establishing a connection
 * to a database using the provided URL, username, and password.
 * This class is used to interact with the database for advertisement-related operations.
 */
public class AdvertisementDatabase {
    private String URL = "url";
    private String USERNAME = "Username";
    private String PASSWORD = "Password";
    private Connection dbconnect;
    
    /**
     * Constructs an AdvertisementDatabase object with the specified database URL, username, and password.
     * 
     * @param url the database URL
     * @param username the database username
     * @param password the database password
     */
    public AdvertisementDatabase(String url, String username, String password) {
        this.URL = url;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    /**
     * Initializes and returns a connection to the database.
     * 
     * @return a Connection object to the database
     * @throws SQLException if a database access error occurs or the URL is null
     */
    public Connection initializeConnection() throws SQLException {
        dbconnect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return dbconnect;
    } 
}