package ca.ucalgary.edu.ensf380;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Station class.
 * These tests verify that the Station class methods correctly handle setting and getting station properties.
 * 
 * @version 1.0
 * @since 2024-08-02
 */
public class StationTest {

    /**
     * Tests the constructor and getters of the Station class.
     * It ensures that the station's name, longitude, and latitude are correctly initialized.
     */
    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String expectedName = "Central Station";
        double expectedLongitude = -114.0719;
        double expectedLatitude = 51.0447;
        
        // Act
        Station station = new Station(expectedName, expectedLongitude, expectedLatitude);
        
        // Assert
        assertEquals(expectedName, station.getSTATIONNAME(), "Station name should be Central Station");
        assertEquals(expectedLongitude, station.getLONGITUDE(), "Station longitude should be -114.0719");
        assertEquals(expectedLatitude, station.getLATITUDE(), "Station latitude should be 51.0447");
    }

    /**
     * Tests the setters of the Station class.
     * It ensures that the station's name, longitude, and latitude are correctly set.
     */
    @Test
    public void testSetters() {
        // Arrange
        Station station = new Station("Initial Station", 0.0, 0.0);
        
        // Act
        station.setSTATIONNAME("New Station");
        station.setLONGITUDE(100.1234);
        station.setLATITUDE(45.6789);
        
        // Assert
        assertEquals("New Station", station.getSTATIONNAME(), "Station name should be New Station");
        assertEquals(100.1234, station.getLONGITUDE(), "Station longitude should be 100.1234");
        assertEquals(45.6789, station.getLATITUDE(), "Station latitude should be 45.6789");
    }
}

