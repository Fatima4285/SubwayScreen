package ca.ucalgary.edu.ensf380;

/**
 * The WeatherParser class extends DataRetriever and is responsible for
 * interacting with weather data APIs. This class provides methods
 * for making API calls to retrieve weather forecasts, aggregations, and overviews.
 * 
 */
public class WeatherParser extends DataRetriever {
    
    /**
     * Constructs a WeatherParser instance with the specified endpoint.
     *
     * @param Endpoint The endpoint URL for the weather data API.
     */
    public WeatherParser(String Endpoint) {
        super(Endpoint);
    }
    
    /**
     * Makes an API call to retrieve weather forecast data.
     *
     * @param longitude The longitude coordinate for the weather forecast.
     * @param latitude  The latitude coordinate for the weather forecast.
     * @param apiKey    The API key used for authentication with the weather data API.
     * @return
     */
    public String apiCallForecast(double longitude, double latitude, short apiKey) {
        return "still need to implement";
    }
    
    /**
     * Makes an API call to retrieve aggregated weather data.
     *
     * @param longitude The longitude coordinate for the aggregated weather data.
     * @param latitude  The latitude coordinate for the aggregated weather data.
     * @param apiKey    The API key used for authentication with the weather data API.
     * @return 
     */
    public String apiCallAggregation(double longitude, double latitude, short apiKey) {
        return "still need to implement";
    }
    
    /**
     * Makes an API call to retrieve a weather overview.
     *
     * @param longitude The longitude coordinate for the weather overview.
     * @param latitude  The latitude coordinate for the weather overview.
     * @param apiKey    The API key used for authentication with the weather data API.
     * @return
     */
    public String apiCallWeatherOverview(double longitude, double latitude, short apiKey) {
        return "still need to implement";
    }
}
