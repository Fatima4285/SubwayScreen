package ca.ucalgary.edu.ensf380;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a provider of news with a headline and content.
 * Implements the Displayable interface to allow displaying news.
 */
public class NewsProvider extends NewsAPI implements Displayable {

    private String headline;
    private String content;

    /**
     * Constructs a new NewsProvider with the specified endpoint, headline, and content.
     * 
     * @param ENDPOINT the endpoint URL for the news data API
     * @param headline the headline of the news
     * @param content the content of the news
     */
    public NewsProvider(String ENDPOINT, String headline, String content) {
        super(ENDPOINT, "");
        this.headline = headline;
        this.content = content;
    }

    // Overloaded constructor for testing purposes
    public NewsProvider(String ENDPOINT) {
        super(ENDPOINT, "");
        this.headline = "Default Headline";
        this.content = "Default Content";
    }

    // Setters
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getters
    public String getHeadline() {
        return this.headline;
    }

    public String getContent() {
        return this.content;
    }

    /**
     * Displays the news using a Swing JFrame.
     */
    @Override
    public void display() {
        try {
            JsonNode response = getNewsData();
            if (response == null) {
                JOptionPane.showMessageDialog(null, "Failed to get news data.");
                return;
            }

            JsonNode articles = response.get("articles");
            if (articles == null || articles.size() == 0) {
                JOptionPane.showMessageDialog(null, "No articles found.");
                return;
            }

            // Display only the first article
            JsonNode article = articles.get(0); // Get the first article

            // Debug output
            System.out.println("Raw Article Data: " + article.toPrettyString());

            String title = article.has("title") ? article.get("title").asText() : "No title available";
            String description = article.has("description") && !article.get("description").isNull() ? 
                                 article.get("description").asText() : "No description available.";
            String imageUrl = article.has("urlToImage") && !article.get("urlToImage").isNull() ? 
                              article.get("urlToImage").asText() : null;

            // Debug output
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Image URL: " + imageUrl);

            JFrame frame = new JFrame("News Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>");
            descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            descriptionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            contentPanel.add(titleLabel);
            contentPanel.add(descriptionLabel);

            if (imageUrl != null && !imageUrl.isEmpty() && (imageUrl.startsWith("http://") || imageUrl.startsWith("https://"))) {
                try {
                    URL url = new URL(imageUrl);
                    ImageIcon imageIcon = new ImageIcon(url);
                    Image img = imageIcon.getImage();
                    Image scaledImg = img.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImg);
                    JLabel imageLabel = new JLabel(scaledIcon);
                    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel.add(imageLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to load image: " + e.getMessage());
                }
            } else {
                System.out.println("No image URL available.");
            }

            frame.add(contentPanel, BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching news: " + e.getMessage());
        }
    }




    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java NewsProvider <endpoint>");
            return;
        }
        String endpoint = args[0];
        NewsProvider newsProvider = new NewsProvider(endpoint);
        newsProvider.display();
    }
}
