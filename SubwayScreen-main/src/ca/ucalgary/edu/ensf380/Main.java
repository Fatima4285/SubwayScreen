package ca.ucalgary.edu.ensf380;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//comitting a comment
public class Main extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextArea outputTextArea;
    private Process process;
    private SimpleDateFormat dateFormat;
    private TrainInfo trainMapPanel;
    private ExecutorService executorService;
    private static JLabel tempLabel;
    private static JLabel windLabel;
    private static JLabel rainLabel;
    private static JLabel timeLabel;
    private static JPanel newsPanel;
    private static JLabel mapLabel;
    private static List<Advertisement> ads = new ArrayList<>();
    private static int currentAdIndex = 0;
    private static String cityName;
    private static JPanel adPanel;
    private static AdvertisementDatabase advertisementDatabase;
    private static String enteredTrain;
    private Announcement announcement; // Add this line

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Main(String cityName, String enteredTrain) {
        super("Subway Simulator Screen");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dateFormat = new SimpleDateFormat("HH:mm:ss");

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        trainMapPanel = new TrainInfo();

        advertisementDatabase = new AdvertisementDatabase();
        
        this.enteredTrain = enteredTrain; // Save the enteredTrain value
        announcement = new Announcement(Integer.parseInt(enteredTrain), trainMapPanel); // Initialize Announcement

        adPanel = new JPanel();
        adPanel.setLayout(new CardLayout());

        WeatherParser weatherParser = new WeatherParser(cityName);
        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));

        tempLabel = new JLabel("Temperature: ");
        windLabel = new JLabel("Wind: ");
        rainLabel = new JLabel("Rain: ");
        timeLabel = new JLabel("Time: ");

        weatherPanel.add(timeLabel);
        weatherPanel.add(tempLabel);
        weatherPanel.add(windLabel);
        weatherPanel.add(rainLabel);

        JPanel mapPanel = new JPanel();
        mapPanel.setBackground(Color.WHITE);
        mapLabel = new JLabel();
        mapPanel.add(mapLabel);

        adPanel.add(mapPanel, "Map");
        adPanel.add(new JPanel(), "Ad");

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(adPanel, "AdPanel");
        mainPanel.add(trainMapPanel, "TrainMapPanel");

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(weatherPanel, BorderLayout.NORTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
        setSize(1200, 600);
        setVisible(true);

        executorService = Executors.newSingleThreadExecutor();

        try {
            String[] command = {
                "java", "-jar",
                "subwayscreen-main/exe/SubwaySimulator.jar",
                "--in", "subwayscreen-main/data/subway.csv",
                "--out", "subwayscreen-main/out"
            };
            process = new ProcessBuilder(command).start();
            executorService.submit(() -> {
                try (InputStream inputStream = process.getInputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        final String finalLine = line;
                        final String formattedLine = formatOutput(finalLine);
                        SwingUtilities.invokeLater(() -> {
                            outputTextArea.append(formattedLine + "\n");
                            outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength());
                            updateMapData(finalLine);
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (process != null) {
                    process.destroy();
                }
                dispose();
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (process != null) {
                process.destroy();
            }
            System.exit(0);
        }));

        loadAdsFromDatabase();
        Timer adTimer = new Timer();
        TimerTask adTask = new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    // Show the ad panel
                    cardLayout.show(mainPanel, "AdPanel");

                    // Schedule the transition to the train map panel after 10 seconds
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SwingUtilities.invokeLater(() -> {
                                // Show the train map panel
                                cardLayout.show(mainPanel, "TrainMapPanel");
                            });
                        }
                    }, 10000); // Show ad panel for 10 seconds

                    // Update the ad after 10 seconds
                    new Timer().schedule(new TimerTask() {
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
                        }
                    }, 10000); // Update ad after 10 seconds
                });
            }
        };
        adTimer.schedule(adTask, 0, 15000); // Run the task every 15 seconds

        WeatherService weatherService = new WeatherService(weatherParser);
        String endpoint = "https://newsapi.org/v2/top-headlines?country=us&apiKey=2fba803407f040ccb4a075a558ea4a24";
        NewsProvider newsProvider = new NewsProvider(endpoint);

        weatherService.updateWeatherData();
        String temperature = weatherService.getDailyTemperature();
        String wind = weatherService.getDailyWind();
        String rain = weatherService.getDailyRain();

        tempLabel.setText("Temperature: " + temperature);
        windLabel.setText("Wind: " + wind);
        rainLabel.setText("Rain: " + rain);

        Timer timeTimer = new Timer();
        timeTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> displayTime());
            }
        }, 0, 1000);

        newsProvider.display();
        newsPanel = newsProvider.getNewsPanel();
        newsPanel.setBackground(Color.ORANGE);
        newsPanel.setPreferredSize(new Dimension(getWidth(), 100));
        add(newsPanel, BorderLayout.SOUTH);
    }

    private String formatOutput(String line) {
        String timestamp = dateFormat.format(new Date());
        return String.format("[%s] %s", timestamp, line);
    }

    private void updateMapData(String line) {
        if (line.startsWith("Train positions:")) {
            line = line.replace("Train positions:", "").trim();
            String[] routes = line.split("\n");
            HashMap<String, String[]> trainRoutes = new HashMap<>();

            for (String route : routes) {
                String[] parts = route.split(":");
                if (parts.length > 1) {
                    String trainId = parts[0].trim();
                    String[] stations = parts[1].split(",");
                    trainRoutes.put(trainId, stations);
                }
            }
            trainMapPanel.setTrainRoutes(trainRoutes);
        }
    }

    private void loadAdsFromDatabase() {
        try (Connection conn = advertisementDatabase.initializeConnection();
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

    public static void showAd(Advertisement ad) {
        File file = new File(ad.getFilePath());
        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return;
        }
        ImageIcon adImageIcon = new ImageIcon(ad.getFilePath());
        Image img = adImageIcon.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, 500, Image.SCALE_SMOOTH);
        mapLabel.setIcon(new ImageIcon(img));
    }

    public static void displayTime() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm:ss a");
        String dtfTime = dtf.format(localDate);
        timeLabel.setText("Time: " + dtfTime);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter command line arguments for city name and train");
            return;
        }

        cityName = args[0];
        enteredTrain = args[1];

        SwingUtilities.invokeLater(() -> new Main(cityName, enteredTrain));
    }
}