package ca.ucalgary.edu.ensf380;

class Announcement {
	
	/**
	 * Contains information regarding the announcement of trains this includes the announcement text,
	 * train information, and an associated audio file.
	 */
	
	private String announcement;
	private TrainInfo trainInfo;
	private String audioFile;
	
	//constructor
	/**
	 * Constructs a new Announcement object with the specified details.
	 * 
	 * @param announcement the speech for the announcement
	 * @param trainInfo the train information associated with the announcement
	 * @param audioFile the filepath to the audio file for the announcement
	 */
	public Announcement(String announcement, TrainInfo trainInfo, String audioFile) {
		this.announcement = announcement;
		this.trainInfo = trainInfo;
		this.audioFile = audioFile;
	}
	
	//setters
	/**
	 * Sets the speech for the announcement.
	 * 
	 * @param announcement the new announcement speech
	 */
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	
	/**
	 * Sets the train information associated with the announcement.
	 * 
	 * @param trainInfo the new train information
	 */
	public void setTrainInfo(TrainInfo trainInfo) {
		this.trainInfo = trainInfo;
	}
	
	/**
	 * Sets the path to the audio filepath for the announcement.
	 * 
	 * @param audioFile the new audio file path
	 */
	public void setAudioFile(String audioFile) {
		this.audioFile = audioFile;
	}
	
	
	//getters
	/**
	 * Gets the speech for the announcement.
	 * 
	 * @return the announcement speech
	 */
	public String getAnnouncement() {
		return this.announcement;
	}
	
	/**
	 * Gets the train information associated with the announcement.
	 * 
	 * @return the train information
	 */

	public TrainInfo getTrainInfo() {
		return this.trainInfo;
	}
	
	/**
	 * Gets the path to the audio filepath for the announcement.
	 * 
	 * @return the audio file path
	 */
	public String getAudioFile() {
		return this.audioFile;
	}
}

