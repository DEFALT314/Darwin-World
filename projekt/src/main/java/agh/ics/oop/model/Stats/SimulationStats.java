package agh.ics.oop.model.Stats;

import agh.ics.oop.model.Genomes.GenomesAbstract;
import agh.ics.oop.model.Map.WorldMap;
import agh.ics.oop.model.WorldElements.Animal;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimulationStats {
    private final WorldMap map;
    private final int allBoxesCount;
    private final Map<GenomesAbstract, Integer> genomesCounter = new HashMap<>();
    private SimulationStatsRecord statsRecord;

    public SimulationStats(WorldMap map, int mapWidth, int mapHeight) {
        this.allBoxesCount = mapHeight * mapWidth;
        this.map = map;
        updateStats();
    }

    public void updateStats() {
        int day = statsRecord == null ?0 : statsRecord.day() + 1;
        int animalCount = map.aliveAnimalsCount();
        int plantCount = map.plantsCount();
        int emptyBoxesCount =allBoxesCount- map.notEmptyPositionsCount();
        double averageLifeLength = getAverageDeadAnimalAge(map.deadAnimalsCount(), map.deadAnimalsAgeSum());
        GenomesAbstract mostCommonGenome = calculateMostCommonGenome();
        double averageEnergy = map.getAliveAnimals().stream().mapToInt(Animal::getEnergy).average().orElse(0);
        double averageChildrenCount = map.getAliveAnimals().stream().mapToInt(Animal::getChildrenCnt).average().orElse(0);
        statsRecord = new SimulationStatsRecord(day, animalCount, plantCount, emptyBoxesCount, averageEnergy, averageLifeLength, averageChildrenCount, mostCommonGenome);
    }

    private GenomesAbstract calculateMostCommonGenome() {
        Map<GenomesAbstract, Long> genomesCounter = map.getAliveAnimals().stream().map(Animal::getGenome)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return genomesCounter.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public double getAverageDeadAnimalAge(int deadAnimalsCount, int deadAnimalsAgeSum) {
        if (deadAnimalsCount == 0) {
            return 0;
        }
        return (double) deadAnimalsAgeSum / deadAnimalsCount;
    }

    public SimulationStatsRecord getStatsRecord() {
        return statsRecord;
    }
}