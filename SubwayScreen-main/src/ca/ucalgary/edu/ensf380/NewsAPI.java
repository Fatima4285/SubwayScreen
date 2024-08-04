package ca.ucalgary.edu.ensf380;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * The NewsAPI class extends DataRetriever and is responsible for
 * interacting with news data APIs. This class is designed to retrieve news data
 * from a specified endpoint.
 * 
 * @version 1.0
 * @since 2024-07-20
 */
public class NewsAPI extends DataRetriever { 

    private final ObjectMapper objectMapper;
    private final String keyword;

    /**
     * Constructs a NewsAPI instance with the specified endpoint.
     *
     * @param ENDPOINT The endpoint URL for the news data API.
     * @param keyword The keyword to filter news articles.
     */
    public NewsAPI(String ENDPOINT, String keyword) {
        super(ENDPOINT);
        this.objectMapper = new ObjectMapper(); // Instantiate ObjectMapper
        this.keyword = keyword;
    }

    /**
     * Retrieves and parses the news data from the API.
     * 
     * @return JsonNode containing the news data
     * @throws IOException if there is an issue with data retrieval or parsing
     */
    public JsonNode getNewsData() throws IOException, InterruptedException {
        String jsonResponse = fetchDataFromAPI();
        return objectMapper.readTree(jsonResponse);
    }

    /**
     * Filters news articles based on the keyword.
     * 
     * @param articles JsonNode containing the articles to be filtered
     * @return List<JsonNode> of filtered articles
     */
    private List<JsonNode> filterArticles(JsonNode articles) {
        List<JsonNode> filteredArticles = new ArrayList<>();
        for (JsonNode article : articles) {
            String title = article.get("title").asText().toLowerCase();
            String description = article.get("description").asText().toLowerCase();
            if (title.contains(keyword.toLowerCase()) || description.contains(keyword.toLowerCase())) {
                filteredArticles.add(article);
            }
        }
        return filteredArticles;
    }

    /**
     * Example method to print out news headlines.
     */
    public void printNewsHeadlines() {
        try {
            JsonNode response = getNewsData();
            JsonNode articles = response.get("articles");

            List<JsonNode> filteredArticles = filterArticles(articles);

            for (JsonNode article : filteredArticles) {
                String title = article.get("title").asText();
                String description = article.get("description").asText();
                String url = article.get("url").asText();
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("URL: " + url);
                System.out.println();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches data from the API.
     * 
     * @return The JSON response from the API as a String.
     * @throws IOException, InterruptedException if there is an issue with data retrieval.
     */
    private String fetchDataFromAPI() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(getENDPOINT()))
            .header("Accept", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
