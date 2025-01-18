package agh.ics.oop.model.Simulation;


import agh.ics.oop.model.Genomes.GenomesFactory;
import agh.ics.oop.model.Map.WorldMap;
import agh.ics.oop.model.Map.AbstractMap;
import agh.ics.oop.model.Stats.SimulationStats;
import agh.ics.oop.model.Stats.StatisticsWriter;

public class Simulation implements Runnable {
    private final WorldMap worldMap;
    private final SimulationConfig conf;
    private final GenomesFactory genomesFactory;
    private final PlantSpawner plantSpawner;
    private final AnimalSpawner animalSpawner;
    private final SimulationStats stats;
    private final StatisticsWriter statsWriter;
    private final boolean saveStats;
    public Simulation(WorldMap worldMap, SimulationConfig conf, GenomesFactory genomesFactory, boolean saveStats) {
        this.saveStats = saveStats;
        this.worldMap = worldMap;
        this.conf = conf;
        this.genomesFactory = genomesFactory;
        this.stats = new SimulationStats(worldMap);
        this.animalSpawner = new AnimalSpawner(worldMap, conf, genomesFactory);
        this.plantSpawner = new PlantSpawner(worldMap, conf);
        this.animalSpawner.spawnAnimals();
        this.plantSpawner.spawnPlants(conf.getStartingPlantCount());
        statsWriter = new StatisticsWriter();
    }

    public void run() {
        while (true) {
            System.out.println(worldMap);
            worldMap.removeDeadAnimals();
            worldMap.moveAnimals();
            worldMap.eatPlants();
            worldMap.reproduce();
            plantSpawner.spawnPlants(conf.getPlantsPerDay());
            stats.updateStats();
            System.out.println(stats.getStatsRecord());
            if (saveStats) {
                statsWriter.saveToCsv(stats.getStatsRecord());
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
