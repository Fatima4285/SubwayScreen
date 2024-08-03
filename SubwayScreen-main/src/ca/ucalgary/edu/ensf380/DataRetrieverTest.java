package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataRetrieverTest {

private DataRetriever dataRetriever;
    
    /**
     * Sets up the test environment by initializing a new {@link DataRetriever} instance.
     */
    @BeforeEach
    public void setUp() {
        dataRetriever = new DataRetriever("http://example.com/api");
    }
    
    /**
     * Tests the constructor of the {@link DataRetriever} class to ensure it sets the endpoint correctly.
     */
    @Test
    public void testConstructor() {
        assertEquals("http://example.com/api", dataRetriever.getENDPOINT(), 
            "The endpoint should be initialized correctly by the constructor.");
    }
    
    /**
     * Tests the getter and setter for the API key.
     */
    @Test
    public void testGetSetApiKey() {
        Short apiKey = 12345;
        dataRetriever.setApiKey(apiKey);
        assertEquals(apiKey, dataRetriever.getApiKey(), 
            "The API key should be set and retrieved correctly.");
    }

    /**
     * Tests the getter and setter for the endpoint URL.
     */
    @Test
    public void testGetSetEndpoint() {
        String newEndpoint = "http://example.com/newapi";
        dataRetriever.setENDPOINT(newEndpoint);
        assertEquals(newEndpoint, dataRetriever.getENDPOINT(), 
            "The endpoint URL should be updated and retrieved correctly.");
    }

    /**
     * Tests the getter and setter for longitude.
     */
    @Test
    public void testGetSetLongitude() {
        double longitude = 123.456;
        dataRetriever.setLongitude(longitude);
        assertEquals(longitude, dataRetriever.getLongitude(), 
            "The longitude should be set and retrieved correctly.");
    }

    /**
     * Tests the getter and setter for latitude.
     */
    @Test
    public void testGetSetLatitude() {
        double latitude = 78.910;
        dataRetriever.setLatitude(latitude);
        assertEquals(latitude, dataRetriever.getLatitude(), 
            "The latitude should be set and retrieved correctly.");
    }

    /**
     * Tests the getter and setter for the date.
     */
    @Test
    public void testGetSetDate() {
        Date date = new Date();
        dataRetriever.setDate(date);
        assertEquals(date, dataRetriever.getDate(), 
            "The date should be set and retrieved correctly.");
    }

}

