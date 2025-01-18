package agh.ics.oop.model.Stats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatisticsWriter {
    private final File csvFile;

    public StatisticsWriter(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        csvFile = new File("src/main/resources/stats_" + currentDateTime.format(formatter) + ".csv");
    }

    public void saveToCsv(SimulationStatsRecord record){
        try (FileWriter fw = new FileWriter(csvFile, true)){
            if (csvFile.length() == 0) {
                fw.write("Day,AnimalCount,PlantCount,EmptyBoxesCount,AverageEnergy,AverageLifeLength,AverageChildrenCount,MostCommonGenome\n");
            }
            fw.write(toCsvRow(record) + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to CSV file: " + e.getMessage(), e);
        }
    }

    private String toCsvRow(SimulationStatsRecord record) {
        return record.day() + "," + record.animalCount() + "," + record.plantCount() + "," +
                record.emptyBoxesCount() + "," +
                formatDouble(record.averageEnergy()) + "," + formatDouble(record.averageLifeLength()) +
                "," + formatDouble(record.averageChildrenCount()) + "," + record.mostCommonGenome();
    }

    private String formatDouble(Double num) {
        return String.format("%.2f", num);
    }
}
