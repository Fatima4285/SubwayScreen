package ca.ucalgary.edu.ensf380;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.JLabel;

public class MainScreen extends Thread{
    private Map map;
    private Advertisement advertisement;
    private WeatherService weather;
    private NewsProvider news;
    private Announcement announcement;
    private TrainInfo trainInfo;
    private static JLabel tempLabel;
    private static JLabel windLabel;
    private static JLabel rainLabel;
    private static JLabel timeLabel;

    public MainScreen(Map map, Advertisement advertisement, WeatherService weather, NewsProvider news, Announcement announcement, TrainInfo trainInfo) {
        this.map = map;
        this.advertisement = advertisement;
        this.weather = weather;
        this.news = news;
        this.announcement = announcement;
        this.trainInfo = trainInfo;
        start();; // Starts the thread upon class initialization
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

    public void setMap(Map map) {
        this.map = map;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public void setWeather(WeatherService weather) {
        this.weather = weather;
    }

    public void setNews(NewsProvider news) {
        this.news = news;
    }

    public void setTrainInfo(TrainInfo trainInfo) {
        this.trainInfo = trainInfo;
    }

    public TrainInfo getTrainInfo() {
        return trainInfo;
    }

    public Map getMap() {
        return map;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public WeatherService getWeather() {
        return weather;
    }

    public NewsProvider getNews() {
        return news;
    }

    public static void main(String[] args) {
    	if (args.length != 1) {
    		System.out.println("please enter a command line argument");
    		return;
    	}
    	String cityName = args[0]; //set the command line argument to cityName
    	
        // Create instances of other classes using the provided constructors
        Map map = new Map("map001", 114.0719, 51.0447, "http://example.com/map.png");
        Advertisement advertisement = new Advertisement("Digital", "ad123");
        WeatherParser weatherParser = new WeatherParser(cityName);
        WeatherService weatherService = new WeatherService(weatherParser);
        Announcement announcement = new Announcement(); // Ensure this has a default constructor
        TrainInfo trainInfo = new TrainInfo(); // Ensure this has a default constructor
        
        // Create an instance of NewsProvider (assuming this class requires a URL endpoint)
        String endpoint = "https://newsapi.org/v2/top-headlines?country=us&apiKey=2fba803407f040ccb4a075a558ea4a24";
        NewsProvider newsProvider = new NewsProvider(endpoint);
        
        // Instantiate MainScreen with all the objects
        //MainScreen mainScreen = new MainScreen(map, advertisement, weatherService, newsProvider, announcement, trainInfo);
        
        // Initialize GUI components
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1));
        
        weatherService.updateWeatherData();
        
        // Get the weather data
        String temperature = weatherService.getDailyTemperature();
        String wind = weatherService.getDailyWind();
        String rain = weatherService.getDailyRain();
        

        tempLabel = new JLabel("Temperature: ");//sets the initial text
        windLabel = new JLabel("Wind: ");
        rainLabel = new JLabel("Rain: ");
        timeLabel = new JLabel("Time: ");
        
        // Update the GUI
        //replace the current text with the new text
        tempLabel.setText("Temperature: " + temperature);
        windLabel.setText("Wind: " + wind);
        rainLabel.setText("Rain: " + rain);

        displayTime();

        frame.add(tempLabel);
        frame.add(windLabel);
        frame.add(rainLabel);
        frame.add(timeLabel);

        frame.setVisible(true);
        
        
        // Test news display
        System.out.println("Testing news display:");
        // Instantiate MainScreen with all the objects
        MainScreen mainScreen = new MainScreen(map, advertisement, weatherService, newsProvider, announcement, trainInfo);
        mainScreen.getNews().display();
    }


}


