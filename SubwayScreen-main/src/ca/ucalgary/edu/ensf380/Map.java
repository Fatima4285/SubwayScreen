package ca.ucalgary.edu.ensf380;

class Map implements Displayable{
	
	/**Map class has a unique Identifier, coordinates for the train and URL for the map Image*/
	
	private String mapID;
	private double longitude;
	private double latitude;
	private String mapImg;
	
	//constructor
	
	/**
	 * Constructs a new Map with the specified ID, coordinates, and image.
	 * 
	 * @param mapID the unique identifier for the map
	 * @param longitude the longitude coordinate of the train on the map
	 * @param latitude the latitude coordinate of the train on the map
	 * @param mapImg the associated map Image
	 */
	public Map(String mapID, double longitude, double latitude, String mapImg) {
		this.mapID = mapID;
		this.longitude = longitude;
		this.latitude = latitude;
		this.mapImg = mapImg;
	}
	
	//setters
	
	/**
	 * Sets the map ID for the map.
	 * 
	 * @param mapID the new map ID
	 */
	public void setMapID(String mapID) {
		this.mapID = mapID;
	}
	
	/**
	 * Sets the longitude coordinate for the train
	 * 
	 * @param longitude the new longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Sets the latitude coordinate for the train
	 * 
	 * @param latitude the new latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * Sets the image associated with the map.
	 * 
	 * @param mapImg the new map image
	 */
	public void setMapImg(String mapImg) {
		this.mapImg = mapImg;
	}
	
	//getters
	/**
	 * Gets the MapID for the map.
	 * 
	 * @return the map ID
	 */

	public String getMapID() {
		return this.mapID;
	}
	
	/**
	 * Gets the longitude coordinate of the train
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return this.longitude;
	}
	
	/**
	 * Gets the latitude coordinate of the train
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return this.latitude;
	}
	
	/**
	 * Gets the image associated with the map.
	 * 
	 * @return the map image
	 */
	public String getMapImg() {
		return this.mapImg;
	}
	
	//Other methods
	
	/**
	 * Displays the map. Inherited from displayable
	 */
	public void display() {
		//Still need to add functionality
	}

}
