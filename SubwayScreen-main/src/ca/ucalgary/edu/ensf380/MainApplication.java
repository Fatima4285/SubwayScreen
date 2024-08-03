package ca.ucalgary.edu.ensf380;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.opencsv.exceptions.CsvValidationException;

public class MainApplication {
    public static void main(String[] args) throws IOException, CsvValidationException {
        JFrame frame = new JFrame("Train Map");
        MapPanel mapPanel = new MapPanel("/maps/Map.png");
        frame.add(mapPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        TrainDataLoader loader = new TrainDataLoader();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    List<Train> trains = loader.loadTrainData("/Users/aroush/ensf380/project/SubwayScreen-main/data/subway.csv");
                    mapPanel.setTrains(trains);
                } catch (IOException | CsvValidationException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 10000); // Update every 10 seconds
    }
}
