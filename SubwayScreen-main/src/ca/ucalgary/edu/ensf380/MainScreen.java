package ca.ucalgary.edu.ensf380;

//main included in this file
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class MainScreen extends Thread {
    private static JLabel tempLabel;
    private static JLabel windLabel;
    private static JLabel rainLabel;
    private static JLabel timeLabel;
    private static JPanel newsPanel;
    private static JLabel mapLabel;
    private static JPanel mapPanel;
    private static List<Advertisement> ads = new ArrayList<>();
    private static int currentAdIndex = 0;

    public MainScreen() {
        start(); // Starts the thread upon class initialization
    }

    // Starts a new thread
    public void run() {
        while (true) {
            displayTime();
        }
    }

    public static void displayTime() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a");
        String dtfTime = dtf.format(localDate);
        timeLabel.setText("Time: " + dtfTime);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter a command line argument for city name");
            return;
        }
        String cityName = args[0]; // Set the command line argument to cityName

        // Create instances of other classes using the provided constructors
        WeatherParser weatherParser = new WeatherParser(cityName);
        WeatherService weatherService = new WeatherService(weatherParser);
        String endpoint = "https://newsapi.org/v2/top-headlines?country=us&apiKey=2fba803407f040ccb4a075a558ea4a24";
        NewsProvider newsProvider = new NewsProvider(endpoint);

        // Initialize GUI components
        JFrame frame = new JFrame("Subway Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout()); // Changed layout to BorderLayout

        // Weather section
        weatherService.updateWeatherData();
        String temperature = weatherService.getDailyTemperature();
        String wind = weatherService.getDailyWind();
        String rain = weatherService.getDailyRain();

        tempLabel = new JLabel("Temperature: " + temperature);
        windLabel = new JLabel("Wind: " + wind);
        rainLabel = new JLabel("Rain: " + rain);
        timeLabel = new JLabel("Time: ");
        displayTime(); // UNNECESSARY BUT KEEP UNTIL THE VERY END

        // Add weather components to a weather panel
        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));
        weatherPanel.add(tempLabel);
        weatherPanel.add(windLabel);
        weatherPanel.add(rainLabel);
        weatherPanel.add(timeLabel);

        frame.add(weatherPanel, BorderLayout.NORTH);

        // News section
        newsProvider.display(); // Make sure Info for the display method is there
        newsPanel = newsProvider.getNewsPanel(); // Get the news panel from NewsProvider
        newsPanel.setBackground(Color.ORANGE);
        newsPanel.setPreferredSize(new Dimension(frame.getWidth(), 100)); // Set preferred size for the news panel
        frame.add(newsPanel, BorderLayout.SOUTH); // Add news panel to the bottom

        // Map display section
        mapPanel = new JPanel();
        mapPanel.setBackground(Color.WHITE);
        mapLabel = new JLabel();
        mapPanel.add(mapLabel);
        frame.add(mapPanel, BorderLayout.CENTER); // Add map panel to center
        
        // Load Ads
        loadAdsFromDatabase();
        
        // Timer to handle map and advertisement display
        Timer timer = new Timer();
        TimerTask adTask = new TimerTask() {
            @Override
            public void run() {
                if (currentAdIndex < ads.size()) {
                    showAd(ads.get(currentAdIndex));
                    currentAdIndex++;
                } else {
                    currentAdIndex = 0;
                    if (!ads.isEmpty()) {
                        showAd(ads.get(currentAdIndex));
                        currentAdIndex++;
                    }
                }

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        showMapImage();
                    }
                }, 10000); // Delay of 10 seconds
            }
        };
        timer.schedule(adTask, 0, 15000);

        frame.setVisible(true);

        // Instantiate MainScreen with all the objects
        MainScreen mainScreen = new MainScreen();
    }
    
    private static void loadAdsFromDatabase() {
        try (Connection conn = AdvertisementDatabase.initializeConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Advertisements")) {

            while (rs.next()) {
                String filePath = rs.getString("filepath");
                ads.add(new Advertisement(filePath));
                }
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void showMapImage() {
        // Load the map image. Update this path as needed.
        String mapImagePath = "SubwayScreen-main/maps/Trains.png";
        ImageIcon mapImageIcon = new ImageIcon(mapImagePath);
        Image img = mapImageIcon.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, 500, Image.SCALE_SMOOTH); // Adjust size as needed
        mapLabel.setIcon(new ImageIcon(img));
    }
    
    public static void showAd(Advertisement ad) {
        // Load image
        File file = new File(ad.getFilePath());
        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return;
        }

        ImageIcon adImageIcon = new ImageIcon(ad.getFilePath());
        Image img = adImageIcon.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, 500, Image.SCALE_SMOOTH); // Adjust size as needed
        mapLabel.setIcon(new ImageIcon(img));
    }
}
