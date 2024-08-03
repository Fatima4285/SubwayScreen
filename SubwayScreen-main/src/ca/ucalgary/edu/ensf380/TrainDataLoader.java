package ca.ucalgary.edu.ensf380;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainDataLoader {
    public List<Train> loadTrainData(String filePath) throws IOException, CsvValidationException {
        List<Train> trains = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String id = line[0];
                double longitude = Double.parseDouble(line[1]);
                double latitude = Double.parseDouble(line[2]);
                trains.add(new Train(id, longitude, latitude));
            }
        }
        return trains;
    }
}
