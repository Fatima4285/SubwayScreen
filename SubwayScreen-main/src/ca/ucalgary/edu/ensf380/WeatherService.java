package ca.ucalgary.edu.ensf380;

class WeatherService {
	
	/**
	 * Represents the daily weather information such as temperature, description, feels like, timezone and wind gust.
	 */
	private String timezone;
	private double temp;
	private double feelsLike;
	private String description;
	private double windGust;
	
	//constructors
	/**
	 * Constructs a new WeatherService with the specified weather details.
	 * 
	 * @param timezone the timezone for the weather report
	 * @param temp the current temperature
	 * @param feelsLike the feelsLike temperature
	 * @param description a description of the weather conditions
	 * @param windGust the wind gust speed
	 */
	public WeatherService(String timezone, double temp, double feelsLike, String description, double windGust) {
		this.timezone = timezone;
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.description = description;
		this.windGust = windGust;
	}
	
	//setters
	/**
	 * Sets the timezone for the weather report.
	 * 
	 * @param timezone the new timezone
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	/**
	 * Sets the current temperature.
	 * 
	 * @param temp the new temperature
	 */
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	/**
	 * Sets the feels like temperature.
	 * 
	 * @param feelsLike the new feels-like temperature
	 */
	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}
	
	/**
	 * Sets the description of the weather conditions.
	 * 
	 * @param description the new weather description
	 */

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Sets the wind gust speed.
	 * 
	 * @param windGust the new wind gust speed
	 */
	public void setWindGust(double windGust) {
		this.windGust = windGust;
	}
	
	//getters
	/**
	 * Gets the timezone for the weather report.
	 * 
	 * @return the timezone
	 */
	public String getTimezone() {
		return this.timezone;
	}
	
	/**
	 * Gets the current temperature.
	 * 
	 * @return the temperature
	 */
	public double getTemp() {
		return this.temp;
	}
	
	/**
	 * Gets the feels like temperature.
	 * 
	 * @return the feels-like temperature
	 */
	public double getFeelsLike() {
		return this.feelsLike;
	}
	
	/**
	 * Gets the description of the weather conditions.
	 * 
	 * @return the weather description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Gets the wind gust speed.
	 * 
	 * @return the wind gust speed
	 */
	public double getWindGust() {
		return this.windGust;
	}
	

}
