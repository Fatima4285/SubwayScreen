package ca.ucalgary.edu.ensf380;

public class MainScreen {
    private Map map;
    private Advertisement advertisement;
    private WeatherService weather;
    private NewsProvider news;
    private Announcement announcement;
    private TrainInfo trainInfo;

    public MainScreen(Map map, Advertisement advertisement, WeatherService weather, NewsProvider news, Announcement announcement, TrainInfo trainInfo) {
        this.map = map;
        this.advertisement = advertisement;
        this.weather = weather;
        this.news = news;
        this.announcement = announcement;
        this.trainInfo = trainInfo;
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
        // Create instances of other classes using the provided constructors
        Map map = new Map("map001", 114.0719, 51.0447, "http://example.com/map.png");
        Advertisement advertisement = new Advertisement("Digital", "ad123");
        WeatherService weather = new WeatherService("MST", 23.5, 21.0, "Sunny", 15.0);
        Announcement announcement = new Announcement(); // Ensure this has a default constructor
        TrainInfo trainInfo = new TrainInfo(); // Ensure this has a default constructor
        
        // Create an instance of NewsProvider (assuming this class requires a URL endpoint)
        String endpoint = "https://newsapi.org/v2/top-headlines?country=us&apiKey=2fba803407f040ccb4a075a558ea4a24";
        NewsProvider newsProvider = new NewsProvider(endpoint);
        
        // Instantiate MainScreen with all the objects
        MainScreen mainScreen = new MainScreen(map, advertisement, weather, newsProvider, announcement, trainInfo);
        
        // Test news display
        System.out.println("Testing news display:");
        mainScreen.getNews().display();
    }


}


