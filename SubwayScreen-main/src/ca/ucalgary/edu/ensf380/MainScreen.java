package ca.ucalgary.edu.ensf380;

import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.JLabel;

public class MainScreen extends Thread{
    private static JLabel tempLabel;
    private static JLabel windLabel;
    private static JLabel rainLabel;
    private static JLabel timeLabel;
   
    private static JPanel newsPanel;

    public MainScreen() {
        start(); // Starts the thread upon class initialization
    }

	//starts a new thread
    //basically does multiple things at once such as continues to display the time over and over
    public void run() {
    	while(true) {
    		displayTime();
    	}
    }
    
    public static void displayTime() {
    	LocalDateTime localDate = LocalDateTime.now();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a");
        String dtfTime = dtf.format(localDate);
        
        //update this variable with the new text which is the time 
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

        // Create an instance of NewsProvider
        String endpoint = "https://newsapi.org/v2/top-headlines?country=us&apiKey=2fba803407f040ccb4a075a558ea4a24";
        NewsProvider newsProvider = new NewsProvider(endpoint);


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
        displayTime(); //UNNECESSARY BUT KEEP UNTIL THE VERY END
        
        //ADD THESE COMPONENTS TO A WEATHER PANEL
        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));
        weatherPanel.add(tempLabel);
        weatherPanel.add(windLabel);
        weatherPanel.add(rainLabel);
        weatherPanel.add(timeLabel);

        frame.add(weatherPanel);

        // News section
        newsProvider.display(); // make sure Info for the display method is there
        newsPanel = newsProvider.getNewsPanel(); // get the newspanel from NewsProvider
        newsPanel.setBackground(Color.ORANGE);
        frame.add(newsPanel); //add to subwayScreen 

        frame.setVisible(true);
        
        // Instantiate MainScreen with all the objects
        MainScreen mainScreen = new MainScreen();
    }


}


