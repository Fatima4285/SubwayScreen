package ca.ucalgary.edu.ensf380;

import javax.imageio.ImageIO;
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
    private Map<String, String[]> trainRoutes;
    private String enteredTrain;
    private String currentTrainId;
    private String[] currentRoute;
    private static final String OUT_FOLDER_PATH = "C:\\Users\\hoode\\Downloads\\ensf380fall\\SubwayScreen\\SubwayScreen-main\\out";
    private Timer timer;
    private Dimension preferredSize;
    private Image image;

    public TrainInfo(String enteredTrain) {
        this.enteredTrain = enteredTrain;
        stationLocations = new HashMap<>();
        trainRoutes = new HashMap<>();
        currentTrainId = null;
        currentRoute = new String[0];
        setPreferredSize(new Dimension(1200, 800)); // Initial size
        updatePreferredSize(); // Calculate preferred size based on station locations

        try {
        	image = ImageIO.read(new File("C:\\Users\\hoode\\Downloads\\ensf380fall\\SubwayScreen\\SubwayScreen-main\\maps\\Trains.png"));
            loadStationLocations(new File("C:\\Users\\hoode\\Downloads\\ensf380fall\\SubwayScreen\\SubwayScreen-main\\data\\subway.csv"));
            clearOutFolder(); // Clear the out folder before loading files
            loadInitialTrainRoutes(); // Load initial data

            // Start the timer with a delay to ensure files are fully written
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    startCheckingForNewFiles();
                }
            }, 500); // Delay of 0.5 seconds

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Recalculate preferred size on component resize
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updatePreferredSize();
            }
        });
    }

    private void updatePreferredSize() {
        int minWidth = 0, minHeight = 0;
        for (Point location : stationLocations.values()) {
            minWidth = Math.max(minWidth, location.x);
            minHeight = Math.max(minHeight, location.y);
        }
        // Add some padding
        setPreferredSize(new Dimension(minWidth + 100, minHeight + 100));
        revalidate(); // Ensure layout manager updates the panel size
    }

    private void clearOutFolder() {
        File directory = new File(OUT_FOLDER_PATH);
        File[] files = directory.listFiles(File::isFile);
        if (files != null) {
            for (File file : files) {
                file.delete(); // Delete each file in the directory
            }
        }
    }

    private void loadStationLocations(File dataFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dataFile));
        String line;
        boolean isFirstLine = true; // Skip the header line
        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // Skip the header line
            }
            String[] fields = line.split(",");
            if (fields.length >= 7) {
                String stationCode = fields[3].trim();
                int x = (int) Double.parseDouble(fields[5].trim());
                int y = (int) Double.parseDouble(fields[6].trim());
                stationLocations.put(stationCode, new Point(x, y));
            }
        }
        reader.close();
        updatePreferredSize(); // Update panel size based on station locations
    }

    private void loadInitialTrainRoutes() throws IOException {
        File newestFile = getLastModified(OUT_FOLDER_PATH);
        if (newestFile != null) {
            loadTrainRoutes(newestFile);
        }
    }

    private void loadTrainRoutes(File newestFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(newestFile));
        String line;
        boolean isFirstLine = true;
        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // Skip the header line
            }
            String[] fields = line.split(",");
            if (fields.length >= 5 && fields[1].trim().equals(enteredTrain)) {
                currentTrainId = fields[1].trim();
                currentRoute = new String[]{fields[2].trim(), fields[4].trim()};
            }
        }
        reader.close();
        repaint(); // Update the display
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

    private void startCheckingForNewFiles() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                checkForNewFiles();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 15000); // Check every 15 seconds
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

    public void setTrainRoutes(Map<String, String[]> trainRoutes) {
        this.trainRoutes = trainRoutes;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, this);
        }
        drawStations(g);
        drawTrains(g);
    }

    private void drawStations(Graphics g) {
        int dotSize = 6;
        int labelOffset = 8;

        g.setColor(Color.BLACK);
        for (Map.Entry<String, Point> entry : stationLocations.entrySet()) {
            String stationCode = entry.getKey();
            Point location = entry.getValue();
            int x = (int) ((double) location.x / getWidth() * getWidth());
            int y = (int) ((double) location.y / getHeight() * getHeight());
            g.fillOval(x - dotSize / 2, y - dotSize / 2, dotSize, dotSize);
            g.drawString(stationCode, x + labelOffset, y);
        }
    }

    private void drawTrains(Graphics g) {
        if (currentRoute.length > 0) {
            String currentStationCode = currentRoute[0];
            Point currentLocation = stationLocations.get(currentStationCode);

            if (currentLocation != null) {
                int x = (int) ((double) currentLocation.x / getWidth() * getWidth());
                int y = (int) ((double) currentLocation.y / getHeight() * getHeight());
                g.setColor(Color.RED);
                g.fillOval(x - 10, y - 10, 20, 20);
                g.drawString(currentTrainId, x + 15, y);
            }

            g.setColor(Color.BLACK);
            int currentIndex = -1;
            for (int i = 0; i < currentRoute.length; i++) {
                if (currentRoute[i].equals(currentStationCode)) {
                    currentIndex = i;
                    break;
                }
            }

            for (int i = -1; i <= 4; i++) {
                if (currentIndex + i >= 0 && currentIndex + i < currentRoute.length) {
                    String nextStationCode = currentRoute[currentIndex + i];
                    Point location = stationLocations.get(nextStationCode);
                    if (location != null) {
                        int x = (int) ((double) location.x / getWidth() * getWidth());
                        int y = (int) ((double) location.y / getHeight() * getHeight());
                        g.fillOval(x - 5, y - 5, 10, 10);
                    }
                }
            }
        }
    }
}

