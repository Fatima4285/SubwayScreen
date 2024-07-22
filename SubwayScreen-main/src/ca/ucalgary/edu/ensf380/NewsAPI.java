package ca.ucalgary.edu.ensf380;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * The NewsAPI class extends DataRetriever and is responsible for
 * interacting with news data APIs. This class is designed to retrieve news data
 * from a specified endpoint.
 * 
 */
public class NewsAPI extends DataRetriever { 

    private final ObjectMapper objectMapper;

    /**
     * Constructs a NewsAPI instance with the specified endpoint.
     *
     * @param ENDPOINT The endpoint URL for the news data API.
     */
    public NewsAPI(String ENDPOINT) {
        super(ENDPOINT);
        this.objectMapper = new ObjectMapper(); // Instantiate ObjectMapper
    }

    /**
     * Retrieves and parses the news data from the API.
     * 
     * @return JsonNode containing the news data
     * @throws IOException if there is an issue with data retrieval or parsing
     */
    public JsonNode getNewsData() throws IOException {
        // Example of fetching data - replace with actual implementation
        String jsonResponse = fetchDataFromAPI(); // Replace with actual method to get the data
        return objectMapper.readTree(jsonResponse);
    }

    /**
     * Example method to print out news headlines.
     */
    public void printNewsHeadlines() {
        try {
            JsonNode response = getNewsData();
            JsonNode articles = response.get("articles");

            for (JsonNode article : articles) {
                String title = article.get("title").asText();
                String description = article.get("description").asText();
                String url = article.get("url").asText();
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("URL: " + url);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to simulate fetching data from API
    private String fetchDataFromAPI() {
        // Replace with actual API call logic
        return "{ \"articles\": [{ \"title\": \"Sample Title\", \"description\": \"Sample Description\", \"url\": \"http://example.com\" }] }";
    }

    public static void main(String[] args) {
        String endpoint = "https://newsapi.org/v2/top-headlines?country=us&apiKey=YOUR_NEWSAPI_KEY";
        NewsAPI newsAPI = new NewsAPI(endpoint);
        newsAPI.printNewsHeadlines();
    }
}
