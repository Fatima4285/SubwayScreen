package ca.ucalgary.edu.ensf380;

public class Station {
	
	String STATIONNAME;
	double LONGITUDE;
	double LATITUDE;
	
	//Constructor
	public Station(String sTATIONNAME, double lONGITUDE, double lATITUDE) {
		super();
		STATIONNAME = sTATIONNAME;
		LONGITUDE = lONGITUDE;
		LATITUDE = lATITUDE;
	}
	
	//Getters and Setters
	public String getSTATIONNAME() {
		return STATIONNAME;
	}

	public void setSTATIONNAME(String sTATIONNAME) {
		STATIONNAME = sTATIONNAME;
	}

	public double getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(double lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

	public double getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(double lATITUDE) {
		LATITUDE = lATITUDE;
	}
}
