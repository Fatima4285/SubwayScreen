package ca.ucalgary.edu.ensf380;

import java.util.Map;


import java.io.*;
import java.util.*;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class Announcement {

    private String announcement;
    private TrainInfo trainInfo;
    private Map<String, String> stationNamesAndType;
    private String stationName;
    private static final String VOICES_KEY = "freetts.voices";
    private static final String VOICES_VALUE = "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory";
    private static final String CENTRAL_TTS = "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral";
    private static final String SUBWAY_FILE = "SubwayScreen-main/data/subway.csv";
    private static final String OUT_FOLDER = "SubwayScreen-main/out";
    private int enteredTrain;
    private Synthesizer synthesizer;
    private Map<Integer, String> trainToStationMap;

    public Announcement(int enteredTrain, TrainInfo trainInfo) {
        this.enteredTrain = enteredTrain;
        this.trainInfo = trainInfo;
        this.stationNamesAndType = new HashMap<>();
        this.trainToStationMap = new HashMap<>();

        populateStationNames();
        populateTrainToStationMap();
        updateStationInfo();
        createAndSpeakAnnouncement();

        // Start a thread to periodically check for new files
        new Thread(this::periodicUpdate).start();
    }

    private void periodicUpdate() {
        while (true) {
            try {
                Thread.sleep(10000); // Check every 10 seconds
                populateTrainToStationMap(); // Reload data from the latest file
                updateStationInfo();
                createAndSpeakAnnouncement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateStationInfo() {
        String currentStationCode = trainToStationMap.get(enteredTrain);
        if (currentStationCode != null) {
            this.stationName = stationNamesAndType.get(currentStationCode);
            if (this.stationName == null) {
                this.stationName = "Unknown";
            }
        } else {
            this.stationName = "Unknown";
        }
    }

    private void populateStationNames() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SUBWAY_FILE))) {
            String line;
            boolean headerSkipped = false;
            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                String[] columns = line.split(",");
                if (columns.length > 4) {
                    String code = columns[3];
                    String name = columns[4];
                    stationNamesAndType.put(code, name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateTrainToStationMap() {
        trainToStationMap.clear();
        File latestFile = getLatestTrainFile();
        if (latestFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(latestFile))) {
                String line;
                boolean headerSkipped = false;
                while ((line = reader.readLine()) != null) {
                    if (!headerSkipped) {
                        headerSkipped = true;
                        continue;
                    }
                    String[] columns = line.split(",");
                    if (columns.length > 2) {
                        try {
                            int trainNumber = Integer.parseInt(columns[1]);
                            String stationCode = columns[2];
                            trainToStationMap.put(trainNumber, stationCode);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File getLatestTrainFile() {
        File outFolder = new File(OUT_FOLDER);
        File[] files = outFolder.listFiles((dir, name) -> name.startsWith("Trains_") && name.endsWith(".csv"));
        if (files != null && files.length > 0) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
            return files[0];
        }
        return null;
    }

    private String determineAnnouncementType(String stationName) {
        if (stationNamesAndType.containsValue(stationName)) {
            return "short";
        } else {
            return "short";
        }
    }

    private String createAnnouncement() {
        if ("Unknown".equals(stationName)) {
            return "No current station available for the train.";
        }

        String type = determineAnnouncementType(stationName);
        if ("long".equals(type)) {
            return "Next stop: " + stationName + ". You can change your train line.";
        } else {
            return "Next stop: " + stationName + ".";
        }
    }

    private void createAndSpeakAnnouncement() {
        announcement = createAnnouncement();
        try {
            if (synthesizer == null) {
                System.setProperty(VOICES_KEY, VOICES_VALUE);
                Central.registerEngineCentral(CENTRAL_TTS);
                synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
                synthesizer.allocate();
                synthesizer.resume();
            }

            if (synthesizer.getEngineState() != Synthesizer.ALLOCATED) {
                synthesizer.allocate();
            }

            synthesizer.speakPlainText(announcement, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAnnouncement() {
        return this.announcement;
    }

}
