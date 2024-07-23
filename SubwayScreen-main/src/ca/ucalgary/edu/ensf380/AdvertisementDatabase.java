package ca.ucalgary.edu.ensf380;

import java.sql.*;


/**
 * The AdvertisementDatabase class is responsible for establishing a connection
 * to a database using the provided URL, username, and password.
 * This class is used to interact with the database for advertisement-related operations.
 */
public class AdvertisementDatabase {
	private String URL = "jdbc:mysql://localhost:3306/advertisement";
    private String USERNAME = "Mahdi";
    private String PASSWORD = "ensf380";
    private Connection dbConnect;
    private ResultSet results; //store the results of the query here
    
    /**
     * Constructs an AdvertisementDatabase object with the specified database URL, username, and password.
     * 
     * @param url the database URL
     * @param username the database username
     * @param password the database password
     */
    public AdvertisementDatabase() {

    }

    /**
     * Initializes and returns a connection to the database.
     * 
     * @throws SQLException if a database access error occurs or the URL is null
     */
    public void initializeConnection() throws SQLException {
        try{
        	dbConnect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException e) {
        	e.printStackTrace();
        }
    } 
    
    /**
     * selects and returns all the information associated with each Ad
     * 
     * @throws SQLException if the fields of the database cannot be accessed
     */
    public String selectAD() throws SQLException{
    	StringBuffer adInfo = new StringBuffer(); //THis is a class that just allows us to modify strings
    	try {
    		Statement myStmt = dbConnect.createStatement(); //create a statement
    		results = myStmt.executeQuery("Select * from advertisements"); //from the advertisements table select all the fields
    		
    		while(results.next()) {
    			System.out.println("Print Results: " + results.getString("title") + "," + results.getString("description") + "," +
    		results.getString("path") + "," + results.getString("media_type"));
    			
    			adInfo.append(results.getString("title") + "," + results.getString("description") + "," +
    		results.getString("path") + "," + results.getString("media_type") + "\n"); //for each iteration we append this string to the adInfo of type StringBuffer
    			
    		}
    		myStmt.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return adInfo.toString();
    }
    
    /**
     * selects and returns the path associated with each Ad
     * 
     * @throws SQLException if the fields of the database cannot be accessed
     */
    public String selectPath() throws SQLException{
    	StringBuffer adPath = new StringBuffer(); //THis is a class that just allows us to modify strings
    	try {
    		Statement myStmt = dbConnect.createStatement(); //create a statement
    		results = myStmt.executeQuery("Select path from advertisements"); //from the advertisements table select Path field
    		
    		while(results.next()) {
    			System.out.println("Path Results: " + results.getString("path"));
    			
    			adPath.append(results.getString("path")); //for each iteration we append this string to the adPath of type StringBuffer
    			
    		}
    		myStmt.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return adPath.toString();
    }
    
    /**
     * selects and returns the id and mediaType associated with each Ad
     * 
     * @throws SQLException if the fields of the database cannot be accessed
     */
    ///might wnat to take out id since that doesnt really matter
    public String selectMediaType() {
    	StringBuffer adMT = new StringBuffer(); //THis is a class that just allows us to modify strings
    	try {
    		Statement myStmt = dbConnect.createStatement(); //create a statement
    		results = myStmt.executeQuery("Select media_type,id from advertisements"); //from the advertisements table select Path field
    		
    		while(results.next()) {
    			System.out.println("ID and Media type: " + results.getString("id") + ","+ results.getString("media_type"));
    			
    			adMT.append(results.getString("id") + ","+ results.getString("media_type")); //for each iteration we append this string to the adPath of type StringBuffer
    			
    		}
    		myStmt.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return adMT.toString();
    }
    
    /**
     * CLose the connection to the database
     * @throws SQLException if there is an error closing database connection
     */
    
    public void close() {
    	try {
    		results.close();
    		dbConnect.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
}