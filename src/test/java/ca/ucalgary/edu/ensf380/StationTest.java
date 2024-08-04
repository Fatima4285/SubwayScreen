package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Station class.
 */
public class StationTest {

    private Station station;

    /**
     * Sets up a new Station object before each test.
     */
    @BeforeEach
    public void setUp() {
        station = new Station("Central Station", 123.456, 78.910);
    }

    /**
     * Tests the constructor and getSTATIONNAME method.
     */
    @Test
    public void testGetSTATIONNAME() {
        assertEquals("Central Station", station.getSTATIONNAME(), "The station name should be 'Central Station'.");
    }

    /**
     * Tests the setSTATIONNAME method.
     */
    @Test
    public void testSetSTATIONNAME() {
        station.setSTATIONNAME("New Station Name");
        assertEquals("New Station Name", station.getSTATIONNAME(), "The station name should be updated to 'New Station Name'.");
    }

    /**
     * Tests the getLONGITUDE method.
     */
    @Test
    public void testGetLONGITUDE() {
        assertEquals(123.456, station.getLONGITUDE(), 0.001, "The longitude should be 123.456.");
    }

    /**
     * Tests the setLONGITUDE method.
     */
    @Test
    public void testSetLONGITUDE() {
        station.setLONGITUDE(654.321);
        assertEquals(654.321, station.getLONGITUDE(), 0.001, "The longitude should be updated to 654.321.");
    }

    /**
     * Tests the getLATITUDE method.
     */
    @Test
    public void testGetLATITUDE() {
        assertEquals(78.910, station.getLATITUDE(), 0.001, "The latitude should be 78.910.");
    }

    /**
     * Tests the setLATITUDE method.
     */
    @Test
    public void testSetLATITUDE() {
        station.setLATITUDE(12.345);
        assertEquals(12.345, station.getLATITUDE(), 0.001, "The latitude should be updated to 12.345.");
    }
}

