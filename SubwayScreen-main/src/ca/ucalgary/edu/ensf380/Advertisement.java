package ca.ucalgary.edu.ensf380;

/** 
 * Advertisement class is responsible for presenting advertisements 
 * (with specific media type, id, and display duration) on the main screen. 
 */
class Advertisement implements Displayable{
	private String mediaType;
	private String adID;
	private static final int DISPLAYDURATION = 10;
	
	 /**
     * Constructs an Advertisement with the specified media type and ad ID.
     *
     * @param mediaType the type of media for the advertisement
     * @param adID the ID of the advertisement
     */
	
	public Advertisement(String mediaType, String adID) {
		this.mediaType = mediaType;
		this.adID = adID;
	}
	
	/**
     * Setting the media type for the advertisement.
     *
     * @param mediaType the new media type
     */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	

    /**
     * Setting the ad ID of the advertisement.
     *
     * @param adID the new ad ID
     */
	public void setAdID(String adID) {
		this.adID = adID;
	}
	
	/**
     * Retrieving the media type of an advertisement.
     *
     * @return the media type
     */
	public String getMediaType() {
		return mediaType;
	}
	
	 /**
     * Retrieving the ad ID of an advertisement.
     *
     * @return the ad ID
     */
	public String getAdID() {
		return adID;
	}
	
	 /**
     * Retrieving the display duration of an advertisement.
     *
     * @return the display duration in seconds
     */
	public static int getDisplayDuration() {
		return DISPLAYDURATION;
	}
	
	
	public void display() {
		//body still needs to be made for this method
	}
}
