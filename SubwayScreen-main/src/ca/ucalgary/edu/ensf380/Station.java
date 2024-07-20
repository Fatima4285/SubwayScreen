package ca.ucalgary.edu.ensf380;

/**
 * The Station class represents a train station with its name, longitude, and latitude.
 * It provides a constructor to create a station and includes getter and setter methods
 * for accessing and modifying the station's properties.
 * 
 * @version 1.0
 * @since 2024-07-20
 */
public class Station {
    
    private String STATIONNAME;
    private double LONGITUDE;
    private double LATITUDE;
    
    /**
     * Constructs a Station object with the specified name, longitude, and latitude.
     * 
     * @param sTATIONNAME the name of the station
     * @param lONGITUDE the longitude of the station
     * @param lATITUDE the latitude of the station
     */
    public Station(String sTATIONNAME, double lONGITUDE, double lATITUDE) {
        super();
        this.STATIONNAME = sTATIONNAME;
        this.LONGITUDE = lONGITUDE;
        this.LATITUDE = lATITUDE;
    }
    
    /**
     * Returns the name of the station.
     * 
     * @return the name of the station
     */
    public String getSTATIONNAME() {
        return STATIONNAME;
    }

    /**
     * Sets the name of the station.
     * 
     * @param sTATIONNAME the new name of the station
     */
    public void setSTATIONNAME(String sTATIONNAME) {
        this.STATIONNAME = sTATIONNAME;
    }

    /**
     * Returns the longitude of the station.
     * 
     * @return the longitude of the station
     */
    public double getLONGITUDE() {
        return LONGITUDE;
    }

    /**
     * Sets the longitude of the station.
     * 
     * @param lONGITUDE the new longitude of the station
     */
    public void setLONGITUDE(double lONGITUDE) {
        this.LONGITUDE = lONGITUDE;
    }

    /**
     * Returns the latitude of the station.
     * 
     * @return the latitude of the station
     */
    public double getLATITUDE() {
        return LATITUDE;
    }

    /**
     * Sets the latitude of the station.
     * 
     * @param lATITUDE the new latitude of the station
     */
    public void setLATITUDE(double lATITUDE) {
        this.LATITUDE = lATITUDE;
    }
}