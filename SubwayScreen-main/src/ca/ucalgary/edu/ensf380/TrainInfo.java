package ca.ucalgary.edu.ensf380;

public class TrainInfo {
	private String trainID;
	private double trainLatitude;
	private double trainLongitude;
	private String trainLine;
	private Station currentStation;
	private Station nextStation;
	private Station[] stations;
	
	//Costructors
	public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
			Station currentStation, Station nextStation, Station[] stations) {
		super();
		this.trainID = trainID;
		this.trainLatitude = trainLatitude;
		this.trainLongitude = trainLongitude;
		this.trainLine = trainLine;
		this.currentStation = currentStation;
		this.nextStation = nextStation;
		this.stations = stations;
	}
	
	public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
			Station currentStation) {
		super();
		this.trainID = trainID;
		this.trainLatitude = trainLatitude;
		this.trainLongitude = trainLongitude;
		this.trainLine = trainLine;
		this.currentStation = currentStation;
	}
	
	public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
			Station currentStation, Station[] stations) {
		super();
		this.trainID = trainID;
		this.trainLatitude = trainLatitude;
		this.trainLongitude = trainLongitude;
		this.trainLine = trainLine;
		this.currentStation = currentStation;
		this.stations = stations;
	}

	public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
			Station currentStation, Station nextStation) {
		super();
		this.trainID = trainID;
		this.trainLatitude = trainLatitude;
		this.trainLongitude = trainLongitude;
		this.trainLine = trainLine;
		this.currentStation = currentStation;
		this.nextStation = nextStation;
	}
	
	public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
			Station nextStation) {
		super();
		this.trainID = trainID;
		this.trainLatitude = trainLatitude;
		this.trainLongitude = trainLongitude;
		this.trainLine = trainLine;
		this.nextStation = nextStation;
	}

	public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
			Station nextStation, Station[] stations) {
		super();
		this.trainID = trainID;
		this.trainLatitude = trainLatitude;
		this.trainLongitude = trainLongitude;
		this.trainLine = trainLine;
		this.nextStation = nextStation;
		this.stations = stations;
	}

	public TrainInfo(String trainID, double trainLatitude, double trainLongitude, String trainLine,
			Station[] stations) {
		super();
		this.trainID = trainID;
		this.trainLatitude = trainLatitude;
		this.trainLongitude = trainLongitude;
		this.trainLine = trainLine;
		this.stations = stations;
	}
	
	//Setters and getters
	public String getTrainID() {
		return trainID;
	}
	
	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}
	
	public double getTrainLatitude() {
		return trainLatitude;
	}
	
	public void setTrainLatitude(double trainLatitude) {
		this.trainLatitude = trainLatitude;
	}
	
	public double getTrainLongitude() {
		return trainLongitude;
	}
	
	public void setTrainLongitude(double trainLongitude) {
		this.trainLongitude = trainLongitude;
	}
	
	public String getTrainLine() {
		return trainLine;
	}
	
	public void setTrainLine(String trainLine) {
		this.trainLine = trainLine;
	}
	
	public Station getCurrentStation() {
		return currentStation;
	}
	
	public void setCurrentStation(Station currentStation) {
		this.currentStation = currentStation;
	}
	
	public Station getNextStation() {
		return nextStation;
	}
	
	public void setNextStation(Station nextStation) {
		this.nextStation = nextStation;
	}
	
	public Station[] getStations() {
		return stations;
	}
	
	public void setStations(Station[] stations) {
		this.stations = stations;
	}	
}
