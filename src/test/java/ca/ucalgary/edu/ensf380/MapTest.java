package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Map class.
 */
public class MapTest {
    private Map map;
    private final String mapID = "map123";
    private final double longitude = -114.0719;
    private final double latitude = 51.0447;
    private final String mapImg = "http://example.com/map.png";

    /**
     * Sets up a new Map object before each test.
     */
    @BeforeEach
    public void setUp() {
        map = new Map(mapID, longitude, latitude, mapImg);
    }

    /**
     * Tests the getMapID method.
     */
    @Test
    public void testGetMapID() {
        String expected = mapID;
        String actual = map.getMapID();
        assertEquals(expected, actual, "The map ID should be 'map123'.");
    }

    /**
     * Tests the setMapID method.
     */
    @Test
    public void testSetMapID() {
        String newMapID = "map456";
        map.setMapID(newMapID);
        String actual = map.getMapID();
        assertEquals(newMapID, actual, "The map ID should be updated to 'map456'.");
    }

    /**
     * Tests the getLongitude method.
     */
    @Test
    public void testGetLongitude() {
        double expected = longitude;
        double actual = map.getLongitude();
        assertEquals(expected, actual, "The longitude should be -114.0719.");
    }

    /**
     * Tests the setLongitude method.
     */
    @Test
    public void testSetLongitude() {
        double newLongitude = -115.0719;
        map.setLongitude(newLongitude);
        double actual = map.getLongitude();
        assertEquals(newLongitude, actual, "The longitude should be updated to -115.0719.");
    }

    /**
     * Tests the getLatitude method.
     */
    @Test
    public void testGetLatitude() {
        double expected = latitude;
        double actual = map.getLatitude();
        assertEquals(expected, actual, "The latitude should be 51.0447.");
    }

    /**
     * Tests the setLatitude method.
     */
    @Test
    public void testSetLatitude() {
        double newLatitude = 52.0447;
        map.setLatitude(newLatitude);
        double actual = map.getLatitude();
        assertEquals(newLatitude, actual, "The latitude should be updated to 52.0447.");
    }

    /**
     * Tests the getMapImg method.
     */
    @Test
    public void testGetMapImg() {
        String expected = mapImg;
        String actual = map.getMapImg();
        assertEquals(expected, actual, "The map image URL should be 'http://example.com/map.png'.");
    }

    /**
     * Tests the setMapImg method.
     */
    @Test
    public void testSetMapImg() {
        String newMapImg = "http://example.com/newmap.png";
        map.setMapImg(newMapImg);
        String actual = map.getMapImg();
        assertEquals(newMapImg, actual, "The map image URL should be updated to 'http://example.com/newmap.png'.");
    }

    /**
     * Tests the display method.
     */
    @Test
    public void testDisplay() {
        map.display();
        // This method prints the map details to the console.
        // You may check the console output manually or use a library to capture and verify console output.
    }
}

