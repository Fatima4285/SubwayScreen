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

/**
 * The TrainInfo class handles loading station locations and train routes,
 * updating the map data, and drawing the train routes and station locations .
 */
public class TrainInfo extends JPanel {

    private Map<String, Point> stationLocations;
    private Map<String, String> stationNames;
    private Map<String, String[]> trainRoutes;
    private String enteredTrain;
    private String currentTrainId;
    private String[] currentRoute;
    private static final String OUT_FOLDER_PATH = "subwayscreen-main/out";
    private Timer timer;

    /**
     * Constructor for the TrainInfo class.
     * 
     * @param enteredTrain The train selected by the user
     */
    public TrainInfo(String enteredTrain) {
        this.enteredTrain = enteredTrain;
        stationLocations = new HashMap<>();
        stationNames = new HashMap<>();
        trainRoutes = new HashMap<>();
        currentTrainId = null;
        currentRoute = new String[0];
        setPreferredSize(new Dimension(1200, 800)); // Initial size

        try {
            loadStationLocations(new File("SubwayScreen-main/data/subway.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearOutFolder(); // Clear the out folder before loading files
        try {
            loadInitialTrainRoutes();
        } catch (IOException e) {
            e.printStackTrace();
        } // Load initial data

        // Start the timer with a delay to ensure files are fully written
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startCheckingForNewFiles();
            }
        }, 500); // Delay of 0.5 seconds

        // Recalculate preferred size on component resize
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updatePreferredSize();
            }
        });
    }
    
    /**updates the size of the panel based ont he current height and width*/
    private void updatePreferredSize() {
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        revalidate(); // Ensure layout manager updates the panel size
    }

    /**Clears all the files in the out folder so that files from the previous run arent read accidentally*/
    private void clearOutFolder() {
        File directory = new File(OUT_FOLDER_PATH);
        File[] files = directory.listFiles(File::isFile);
        if (files != null) {
            for (File file : files) {
                file.delete(); // Delete each file in the directory
            }
        }
    }

    /**
     * Loads station locations and names from the subway.csv file.
     * 
     * @param dataFile The subway.csv file wiht all station data
     * 
     * @throws IOException If a file I/O error occurs.
     */
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
                String stationName = fields[4].trim(); 
                stationLocations.put(stationCode, new Point(x, y));
                stationNames.put(stationCode, stationName); //store stationcode and stationname
            }
        }
        reader.close();
        updatePreferredSize(); 
    }

    /**Takes the file that was most recently changed and loads the train routes from it 
     * @throws IOException if there is a file I/O error*/
    private void loadInitialTrainRoutes() throws IOException {
        File newestFile = getLastModified(OUT_FOLDER_PATH);
        if (newestFile != null) {
            loadTrainRoutes(newestFile);
        }
    }

    /**loads the train route and code from the newest file
     * @param newestFile this is the file that was most recently changed
     * @throws IOException if a file I/O error occurs
     * */
    private void loadTrainRoutes(File newestFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(newestFile));
        String line;
        boolean isFirstLine = true;
        //skip the first line. Initially always true
        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; 
            }
            String[] fields = line.split(",");
            if (fields.length >= 5 && fields[1].trim().equals(enteredTrain)) {
                currentTrainId = fields[1].trim();
                currentRoute = new String[]{fields[2].trim(), fields[4].trim()}; //current station and destination
            }
        }
        reader.close();
        repaint(); // Updates the display
    }

    /**Uses the directory to retrive the file that was recently changed
     * @param directoryFilePath the filepath directory
     * @return THe most recently modified file. might return null if no file is found
     * */
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
    
    /**Checks for a new file in the out folder, every 15 seconds */
    private void startCheckingForNewFiles() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                checkForNewFiles();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 15000); // Check every 15 seconds
    }

    /**
     * Checks for new train route files and loads the data from the most recent file.
     */
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

    /**
     * Sets the train routes and repaints the panel.
     * 
     * @param trainRoutes A hashmap of train routes where train ID and the value is an array 
     */
    public void setTrainRoutes(Map<String, String[]> trainRoutes) {
        this.trainRoutes = trainRoutes;
        repaint();
    }
    

    /**paints the componenet and draw the station location and train routes
     * */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStations(g);
        drawTrains(g);
    }

    /**Draw the stations and colour them black
     * @param g used for painting
     * */
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

    /**
     * Draws the train on the panel.
     * 
     * @param g graphics context used for painting.
     */
    private void drawTrains(Graphics g) {
        if (currentRoute.length > 0) {
            String currentStationCode = currentRoute[0];
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


