package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimulationStats {
    private final Map<GenomesAbstract, Integer> genomesCounter= new HashMap<>();
    private int day = 0;
    private int animalCount = 0;
    private int plantCount = 0;
    private int emptyBoxesCount = 0;
    private double averageEnergy = 0;
    private double averageLifeLength = 0;
    private double averageChildrenCount = 0;
    private GenomesAbstract mostCommonGenome = null;
    private final WorldMap map;
    SimulationStats(WorldMap map){
        this.map = map;
    }
    public void nextDay(){
        day++;
        animalCount = map.aliveAnimalsCount();
        plantCount = map.deadAnimalsCount();
        emptyBoxesCount = map.emptyPositionsCount();
        averageLifeLength = getAverageDeadAnimalAge(map.deadAnimalsCount(), map.deadAnimalsAgeSum());
        mostCommonGenome = calculateMostCommonGenome();
        averageEnergy = map.getAliveAnimals().stream().mapToInt(Animal::getEnergy).average().orElse(0);
        averageChildrenCount = map.getAliveAnimals().stream().mapToInt(Animal::getChildrenCnt).average().orElse(0);
    }

    private GenomesAbstract calculateMostCommonGenome() {
        Map<GenomesAbstract, Long> genomesCounter = map.getAliveAnimals().stream().map(Animal::getGenome)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return genomesCounter.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public void addGenome(GenomesAbstract genome) {
        genomesCounter.put(genome, genomesCounter.getOrDefault(genome, 0) + 1);
    }

    public double getAverageDeadAnimalAge(int deadAnimalsCount, int deadAnimalsAgeSum) {
        if (deadAnimalsCount == 0) {
            return 0;
        }
        return (double) deadAnimalsAgeSum / deadAnimalsCount;
    }

}
