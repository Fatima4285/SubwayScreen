package ca.ucalgary.edu.ensf380;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a provider of news with a headline and content.
 * Implements the Displayable interface to allow displaying news.
 * 
 * @version 1.0
 * @since 2024-07-20
 */
public class NewsProvider extends NewsAPI implements Displayable {

    private String headline;
    private String content;
	private JPanel newsPanel;

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
        this.newsPanel = new JPanel();
        this.newsPanel.setLayout(new BoxLayout(newsPanel, BoxLayout.Y_AXIS));
        this.newsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    // Overloaded constructor for testing purposes
    public NewsProvider(String ENDPOINT) {
        super(ENDPOINT, "");
        this.headline = "Default Headline";
        this.content = "Default Content";
        this.newsPanel = new JPanel();
        this.newsPanel.setLayout(new BoxLayout(newsPanel, BoxLayout.Y_AXIS));
        this.newsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        newsPanel.removeAll(); // Clear previous news content

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

            String title = article.has("title") ? article.get("title").asText() : "No title available";
            String description = article.has("description") && !article.get("description").isNull() ? article.get("description").asText() : "";//IF THERE IS NO DESCRIPTION THEN SHOW NOTHING
            String imageUrl = article.has("urlToImage") && !article.get("urlToImage").isNull() ? article.get("urlToImage").asText() : null;

            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>");
            descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            descriptionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            newsPanel.add(titleLabel);
            newsPanel.add(descriptionLabel);

            if (imageUrl != null && !imageUrl.isEmpty() && (imageUrl.startsWith("http://") || imageUrl.startsWith("https://"))) {
                try {
                    URL url = new URL(imageUrl);
                    ImageIcon imageIcon = new ImageIcon(url);
                    Image img = imageIcon.getImage();
                    Image scaledImg = img.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImg);
                    JLabel imageLabel = new JLabel(scaledIcon);
                    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    newsPanel.add(imageLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to load image: " + e.getMessage());
                }
            }

            newsPanel.revalidate();
            newsPanel.repaint();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching news: " + e.getMessage());
        }
    }

    public JPanel getNewsPanel() {
        return newsPanel;
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
