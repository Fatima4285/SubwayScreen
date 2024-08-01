package ca.ucalgary.edu.ensf380;

import java.sql.*;

/**
 * The AdvertisementDatabase class is responsible for establishing a connection
 * to a database using the provided URL, username, and password.
 * This class is used to interact with the database for advertisement-related operations.
 */
public class AdvertisementDatabase {
	private static String URL = "jdbc:mysql://localhost:3306/advertisement";
    private static String USERNAME = "Mahdi";
    private static String PASSWORD = "ensf380";
    private static Connection dbConnect;
    private static ResultSet results; //store the results of the query here
    
    /**
     * Default constructor
     */
    public AdvertisementDatabase() {

    }

    /**
     * Initializes and returns a connection to the database.
     * @return 
     * 
     * @throws SQLException if a database access error occurs or the URL is null
     */
    public static Connection initializeConnection() throws SQLException {
        try{
        	dbConnect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return dbConnect;
    } 
    
    /**
     * selects and returns all the information associated with each Ad
     * 
     * @throws SQLException if the fields of the database cannot be accessed
     */
    public static String selectAd() throws SQLException{
    	StringBuffer adInfo = new StringBuffer(); //THis is a class that just allows us to modify strings
    	try {
    		Statement myStmt = dbConnect.createStatement(); //create a statement
    		results = myStmt.executeQuery("Select * from advertisements"); //from the advertisements table select all the fields
    		
    		while(results.next()) {
    		System.out.println("SelectAd() print results: " + results.getString("title") + ", " + results.getString("media_type") + ", " +
    		results.getString("filepath"));
    			
    			adInfo.append(results.getString("title") + "," +
    		results.getString("filepath") + "," + results.getString("media_type") + "\n"); //for each iteration we append this string to the adInfo of type StringBuffer
    			
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
    public static String selectPath() throws SQLException{
    	StringBuffer adPath = new StringBuffer(); //THis is a class that just allows us to modify strings
    	try {
    		Statement myStmt = dbConnect.createStatement(); //create a statement
    		results = myStmt.executeQuery("Select filepath from advertisements"); //from the advertisements table select Path field
    		
    		while(results.next()) {
    			System.out.println("SelectPath() print results: " + results.getString("filepath"));
    			
    			adPath.append(results.getString("filepath")); //for each iteration we append this string to the adPath of type StringBuffer
    			
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
    public static String selectMediaTypeAndId() {
    	StringBuffer adMT = new StringBuffer(); //THis is a class that just allows us to modify strings
    	try {
    		Statement myStmt = dbConnect.createStatement(); //create a statement
    		results = myStmt.executeQuery("Select media_type,id from advertisements"); //from the advertisements table select Path field
    		
    		while(results.next()) {
    			System.out.println("SelectMediaTypeAndId() print results: " + results.getString("id") + ","+ results.getString("media_type"));
    			
    			adMT.append(results.getString("id") + ", "+ results.getString("media_type")); //for each iteration we append this string to the adPath of type StringBuffer
    			
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
    
    public static void close() {
    	try {
    		results.close();
    		dbConnect.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
     //Uncomment main method to see results of queries
    /*public static void main(String[] args) throws SQLException {
    	new AdvertisementDatabase();
    	try {
			Connection conn = AdvertisementDatabase.initializeConnection();
	    	selectAd();
	    	selectPath();
	    	selectMediaTypeAndId();
	    	close();
    	} catch (SQLException e) {
			e.printStackTrace();
		}        
    } */
}