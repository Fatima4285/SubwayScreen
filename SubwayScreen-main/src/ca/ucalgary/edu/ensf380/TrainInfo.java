package ca.ucalgary.edu.ensf380;

/**
 * The TrainInfo class represents information about a train,
 * including its ID, latitude, longitude, line, and associated stations.
 * It provides constructors to create train information with various combinations
 * of parameters and includes getter and setter methods to access and modify
 * the train's properties.
 * 
 * @version 1.0
 * @since 2024-07-20
 */

public class TrainInfo {
    private String trainID;
    private double trainLatitude;
    private double trainLongitude;
    private String trainLine;
    private Station currentStation;
    private Station nextStation;
    private Station[] stations;

    /**
     * Constructs a TrainInfo object with all possible parameters.
     * 
     * @param trainID the ID of the train
     * @param trainLatitude the latitude of the train
     * @param trainLongitude the longitude of the train
     * @param trainLine the line the train is on
     * @param currentStation the current station of the train
     * @param nextStation the next station of the train
     * @param stations an array of stations the train stops at
     */
    public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
            Station currentStation, Station nextStation, Station[] stations) {
        this.trainID = trainID;
        this.trainLatitude = trainLatitude;
        this.trainLongitude = trainLongitude;
        this.trainLine = trainLine;
        this.currentStation = currentStation;
        this.nextStation = nextStation;
        this.stations = stations;
    }

    /**
     * Constructs a TrainInfo object with the specified train ID, latitude,
     * longitude, line, and current station.
     * 
     * @param trainID the ID of the train
     * @param trainLatitude the latitude of the train
     * @param trainLongitude the longitude of the train
     * @param trainLine the line the train is on
     * @param currentStation the current station of the train
     */
    public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
            Station currentStation) {
        this(trainID, trainLatitude, trainLongitude, trainLine, currentStation, null, null);
    }

    /**
     * Constructs a TrainInfo object with the specified train ID, latitude,
     * longitude, line, and next station.
     * 
     * @param trainID the ID of the train
     * @param trainLatitude the latitude of the train
     * @param trainLongitude the longitude of the train
     * @param trainLine the line the train is on
     * @param nextStation the next station of the train
     */
    public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
            Station nextStation) {
        this(trainID, trainLatitude, trainLongitude, trainLine, null, nextStation, null);
    }

    /**
     * Constructs a TrainInfo object with the specified train ID, latitude,
     * longitude, line, and an array of stations.
     * 
     * @param trainID the ID of the train
     * @param trainLatitude the latitude of the train
     * @param trainLongitude the longitude of the train
     * @param trainLine the line the train is on
     * @param stations an array of stations the train stops at
     */
    public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
            Station[] stations) {
        this(trainID, trainLatitude, trainLongitude, trainLine, null, null, stations);
    }

    /**
     * Constructs a TrainInfo object with the specified train ID, latitude,
     * longitude, line, current station, and an array of stations.
     * 
     * @param trainID the ID of the train
     * @param trainLatitude the latitude of the train
     * @param trainLongitude the longitude of the train
     * @param trainLine the line the train is on
     * @param currentStation the current station of the train
     * @param stations an array of stations the train stops at
     */
    public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
            Station currentStation, Station[] stations) {
        this(trainID, trainLatitude, trainLongitude, trainLine, currentStation, null, stations);
    }

    /**
     * Constructs a TrainInfo object with the specified train ID, latitude,
     * longitude, line, current station, and next station.
     * 
     * @param trainID the ID of the train
     * @param trainLatitude the latitude of the train
     * @param trainLongitude the longitude of the train
     * @param trainLine the line the train is on
     * @param currentStation the current station of the train
     * @param nextStation the next station of the train
     */
    public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
            Station currentStation, Station nextStation) {
        this(trainID, trainLatitude, trainLongitude, trainLine, currentStation, nextStation, null);
    }

    
    //Getter and Setters
    /**
     * Returns the ID of the train.
     * 
     * @return the ID of the train
     */
    public String getTrainID() {
        return trainID;
    }
    
    /**
     * Sets the ID of the train.
     * 
     * @param trainID the new ID of the train
     */
    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }
    
    /**
     * Returns the latitude of the train.
     * 
     * @return the latitude of the train
     */
    public double getTrainLatitude() {
        return trainLatitude;
    }
    
    /**
     * Sets the latitude of the train.
     * 
     * @param trainLatitude the new latitude of the train
     */
    public void setTrainLatitude(double trainLatitude) {
        this.trainLatitude = trainLatitude;
    }
    
    /**
     * Returns the longitude of the train.
     * 
     * @return the longitude of the train
     */
    public double getTrainLongitude() {
        return trainLongitude;
    }
    
    /**
     * Sets the longitude of the train.
     * 
     * @param trainLongitude the new longitude of the train
     */
    public void setTrainLongitude(double trainLongitude) {
        this.trainLongitude = trainLongitude;
    }
    
    /**
     * Returns the line the train is on.
     * 
     * @return the line the train is on
     */
    public String getTrainLine() {
        return trainLine;
    }
    
    /**
     * Sets the line the train is on.
     * 
     * @param trainLine the new line the train is on
     */
    public void setTrainLine(String trainLine) {
        this.trainLine = trainLine;
    }
    
    /**
     * Returns the current station of the train.
     * 
     * @return the current station of the train
     */
    public Station getCurrentStation() {
        return currentStation;
    }
    
    /**
     * Sets the current station of the train.
     * 
     * @param currentStation the new current station of the train
     */
    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }
    
    /**
     * Returns the next station of the train.
     * 
     * @return the next station of the train
     */
    public Station getNextStation() {
        return nextStation;
    }
    
    /**
     * Sets the next station of the train.
     * 
     * @param nextStation the new next station of the train
     */
    public void setNextStation(Station nextStation) {
        this.nextStation = nextStation;
    }
    
    /**
     * Returns an array of stations the train stops at.
     * 
     * @return an array of stations the train stops at
     */
    public Station[] getStations() {
        return stations;
    }
    
    /**
     * Sets the stations the train stops at.
     * 
     * @param stations the new stations the train stops at
     */
    public void setStations(Station[] stations) {
        this.stations = stations;
    }    
}
