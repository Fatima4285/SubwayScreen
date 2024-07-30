package ca.ucalgary.edu.ensf380;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class MainScreen extends Thread {
    private static JLabel tempLabel;
    private static JLabel windLabel;
    private static JLabel rainLabel;
    private static JLabel timeLabel;
    private static JPanel adPanel;

    public MainScreen() {
        start(); // Starts the thread upon class initialization
    }

    public void run() {
        while (true) {
            displayTime();
            try {
                Thread.sleep(1000); // Update time every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

        // Initialize GUI components
        JFrame frame = new JFrame("Subway Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(2, 1)); // Adjusted layout to display both sections

        // Weather section
        weatherService.updateWeatherData();
        String temperature = weatherService.getDailyTemperature();
        String wind = weatherService.getDailyWind();
        String rain = weatherService.getDailyRain();

        tempLabel = new JLabel("Temperature: " + temperature);
        windLabel = new JLabel("Wind: " + wind);
        rainLabel = new JLabel("Rain: " + rain);
        timeLabel = new JLabel("Time: ");
        displayTime(); 

        // Add these components to a weather panel
        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));
        weatherPanel.add(tempLabel);
        weatherPanel.add(windLabel);
        weatherPanel.add(rainLabel);
        weatherPanel.add(timeLabel);

        frame.add(weatherPanel);

        // Advertisement section
        Advertisement advertisement = new Advertisement();
        advertisement.display();
        adPanel = advertisement.getAdPanel();
        frame.add(adPanel);

        frame.setVisible(true);

        // Instantiate MainScreen
        MainScreen mainScreen = new MainScreen();
    }
}

