package ca.ucalgary.edu.ensf380;

/**
 * The MainScreen class represents a screen that displays various types of information 
 * such as a map, advertisement, weather, news, announcements, and train information.
 */

class MainScreen {
	
	private Map map;
	private Advertisement advertisement;
	private WeatherService weather;
	private NewsProvider news;
	private Announcement announcement;
	private TrainInfo trainInfo;
	
	/**
     * Constructs a MainScreen with the specified map, advertisement, weather service, news provider,
     * announcement, and train information.
     *
     * @param map the map to be displayed
     * @param advertisement the advertisement to be displayed
     * @param weather the weather service providing weather information
     * @param news the news provider
     * @param announcement the announcement to be displayed
     * @param trainInfo the train information to be displayed
     */
	
	MainScreen(Map map, Advertisement advertisement, WeatherService weather, NewsProvider news, Announcement announcement, TrainInfo trainInfo) {
		this.map = map;
		this.advertisement = advertisement;
		this.weather = weather;
		this.news = news;
		this.announcement = announcement;
		this.trainInfo = trainInfo;
	}
	
	/**
     * Sets the map to be displayed on the main screen.
     *
     * @param map the new map
     */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
     * Sets the announcement to be displayed on the main screen.
     *
     * @param announcement the new announcement
     */
	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}
	
	/**
     * Sets the advertisement to be displayed on the main screen.
     *
     * @param advertisement the new advertisement
     */
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

    /**
     * Sets the weather service providing weather information on the main screen.
     *
     * @param weather the new weather service
     */
	public void setWeather(WeatherService weather) {
		this.weather = weather;
	}
	
	/**
     * Sets the news provider to be displayed on the main screen.
     *
     * @param news the new news provider
     */
	public void setNews(NewsProviders news) {
		this.news = news;
	}
	
	 /**
     * Sets the train information to be displayed on the main screen.
     *
     * @param trainInfo the new train information
     */
	
	public void setTrainInfo(TrainInfo trainInfo) {
		this.trainInfo = trainInfo;
	}
	
	/**
     * Returns the train information displayed on the main screen.
     *
     * @return the train information
     */
	public TrainInfo getTrainInfo() {
		return trainInfo;
	}
	

    /**
     * Returns the map displayed on the main screen.
     *
     * @return the map
     */
	public Map getMap() {
		return map;
	}
	
	/**
     * Returns the announcement displayed on the main screen.
     *
     * @return the announcement
     */
	public Announcement getAnnouncement() {
		return announcement;
	}
	
	 /**
     * Returns the advertisement displayed on the main screen.
     *
     * @return the advertisement
     */
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	
	/**
     * Returns the weather service providing weather information on the main screen.
     *
     * @return the weather service
     */
	public WeatherService getWeather() {
		return weather;
	}
	

    /**
     * Returns the news provider displayed on the main screen.
     *
     * @return the news provider
     */
	public NewsProvider getNews() {
		return news;
	}
	
	public static void main(String[] args) { 
		System.out.println("Hello world!");

	}

}


