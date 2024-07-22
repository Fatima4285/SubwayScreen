package ca.ucalgary.edu.ensf380;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The NewsAPI class extends DataRetriever and is responsible for
 * interacting with news data APIs. This class is designed to retrieve news data
 * from a specified endpoint.
 * 
 */
public class NewsAPI extends DataRetriever { 
    /**
     * Constructs a NewsAPI instance with the specified endpoint.
     *
     * @param ENDPOINT The endpoint URL for the news data API.
     */
    public NewsAPI(String ENDPOINT) {
        super(ENDPOINT);
    }

}

