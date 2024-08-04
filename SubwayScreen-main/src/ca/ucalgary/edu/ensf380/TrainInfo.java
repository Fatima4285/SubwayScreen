package ca.ucalgary.edu.ensf380;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TrainInfo extends JPanel {

    private Map<String, Point> stationLocations;
    private Map<String, String> stationNames;
    private Map<String, String[]> trainRoutes;
    private String currentTrainId;
    private String[] currentRoute;
    private static final String OUT_FOLDER_PATH = "subwayscreen-main/out";
    private Timer timer;

    public TrainInfo() {
        stationLocations = new HashMap<>();
        stationNames = new HashMap<>();
        trainRoutes = new HashMap<>();
        currentTrainId = null;
        currentRoute = new String[0];
        setPreferredSize(new Dimension(1200, 800));

        try {
            loadStationLocations(new File("SubwayScreen-main/data/subway.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        clearOutFolder(); // Clear the out folder

        // Load initial train routes
        try {
            loadInitialTrainRoutes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Timer to check for new files periodically
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkForNewFiles();
            }
        }, 0, 500); // Start immediately and check every 15 seconds

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updatePreferredSize();
            }
        });
    }

    private void updatePreferredSize() {
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        revalidate();
    }

    private void clearOutFolder() {
        File directory = new File(OUT_FOLDER_PATH);
        File[] files = directory.listFiles(File::isFile);
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    private void loadStationLocations(File dataFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length >= 7) {
                    String stationCode = fields[3].trim();
                    int x = (int) Double.parseDouble(fields[5].trim());
                    int y = (int) Double.parseDouble(fields[6].trim());
                    String stationName = fields[4].trim();

                    stationLocations.put(stationCode, new Point(x, y));
                    stationNames.put(stationCode, stationName);
                }
            }
        }

        repaint(); // Ensure stations are displayed immediately
    }

    private void loadInitialTrainRoutes() throws IOException {
        File newestFile = getLastModified(OUT_FOLDER_PATH);
        if (newestFile != null) {
            loadTrainRoutes(newestFile);
        }
    }
    
    public void setTrainRoutes(Map<String, String[]> trainRoutes) {
        this.trainRoutes = trainRoutes;
        repaint(); // Ensure the panel is repainted with the new data
    }


    private void loadTrainRoutes(File newestFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(newestFile))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    String trainId = fields[1].trim();
                    String currentStationCode = fields[2].trim();
                    String destination = fields[4].trim();
                    trainRoutes.put(trainId, new String[]{currentStationCode, destination});
                }
            }
        }

        repaint(); // Ensure trains are displayed immediately
    }

    private File getLastModified(String directoryFilePath) {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;
    }

    private void checkForNewFiles() {
        File newestFile = getLastModified(OUT_FOLDER_PATH);
        if (newestFile != null) {
            try {
                loadTrainRoutes(newestFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStations(g);
        drawTrains(g);
    }

    private void drawStations(Graphics g) {
        int dotSize = 6;
        g.setColor(Color.BLACK);

        for (Map.Entry<String, Point> entry : stationLocations.entrySet()) {
            String stationCode = entry.getKey();
            Point location = entry.getValue();

            if (getWidth() > 0 && getHeight() > 0) {
                int x = (int) ((double) location.x / 1000 * getWidth());
                int y = (int) ((double) location.y / 1000 * getHeight());
                g.fillOval(x - dotSize / 2, y - dotSize / 2, dotSize, dotSize);
            }
        }
    }

    private void drawTrains(Graphics g) {
        for (Map.Entry<String, String[]> entry : trainRoutes.entrySet()) {
            String trainId = entry.getKey();
            String[] route = entry.getValue();
            String currentStationCode = route[0];
            Point currentLocation = stationLocations.get(currentStationCode);

            if (currentLocation != null) {
                int x = (int) ((double) currentLocation.x / 1000 * getWidth());
                int y = (int) ((double) currentLocation.y / 1000 * getHeight());

                g.setColor(Color.RED);
                g.fillOval(x - 10, y - 10, 20, 20);

                // Display the station name
                String stationName = stationNames.get(currentStationCode);
                if (stationName != null) {
                    g.drawString(stationName, x + 15, y);
                }
            }
        }
    }
}
