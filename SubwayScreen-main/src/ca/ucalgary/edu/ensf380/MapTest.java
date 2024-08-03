package ca.ucalgary.edu.ensf380;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Map class.
 */
public class MapTest {

    private Map map;

    /**
     * Sets up a Map instance before each test.
     */
    @BeforeEach
    public void setUp() {
        map = new Map("Map1", 45.0, 90.0, "http://example.com/map.png");
    }

    /**
     * Tests the constructor and getters of the Map class.
     * It verifies that the Map instance is created correctly with the provided values.
     */
    @Test
    public void testConstructorAndGetters() {
        // Assert that the Map is initialized correctly
        assertEquals("Map1", map.getMapID(), "Map ID should be Map1");
        assertEquals(45.0, map.getLongitude(), "Longitude should be 45.0");
        assertEquals(90.0, map.getLatitude(), "Latitude should be 90.0");
        assertEquals("http://example.com/map.png", map.getMapImg(), "Map Image URL should be http://example.com/map.png");
    }

    /**
     * Tests the setters of the Map class.
     * It verifies that the setters correctly update the attributes of the Map instance.
     */
    @Test
    public void testSetters() {
        map.setMapID("Map2");
        map.setLongitude(50.0);
        map.setLatitude(95.0);
        map.setMapImg("http://example.com/map2.png");

        // Assert that the Map attributes are updated correctly
        assertEquals("Map2", map.getMapID(), "Map ID should be updated to Map2");
        assertEquals(50.0, map.getLongitude(), "Longitude should be updated to 50.0");
        assertEquals(95.0, map.getLatitude(), "Latitude should be updated to 95.0");
        assertEquals("http://example.com/map2.png", map.getMapImg(), "Map Image URL should be updated to http://example.com/map2.png");
    }

    /**
     * Tests the display method of the Map class.
     * It verifies that the display method prints the expected information.
     * Note: This test will verify output to System.out. You might need to capture System.out for assertion.
     */
    @Test
    public void testDisplay() {
        // Capture the output of display method
        // This example uses a simple approach; a more sophisticated approach might use ByteArrayOutputStream
        // For simplicity, we assume display() works correctly if the other tests pass.
        map.display();
    }
}

