package ca.ucalgary.edu.ensf380;

public class Train {
    private String id;
    private double longitude;
    private double latitude;

    public Train(String id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
