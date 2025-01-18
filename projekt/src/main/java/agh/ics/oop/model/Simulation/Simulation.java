package agh.ics.oop.model.Simulation;


import agh.ics.oop.model.Genomes.GenomesFactory;
import agh.ics.oop.model.Map.WorldMap;
import agh.ics.oop.model.Stats.SimulationStats;
import agh.ics.oop.model.Stats.StatisticsWriter;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    private final WorldMap worldMap;
    private final SimulationConfig conf;
    private final GenomesFactory genomesFactory;
    private List<SimulationListener> listeners = new ArrayList<>();
    private final PlantSpawner plantSpawner;
    private final AnimalSpawner animalSpawner;
    private final SimulationStats stats;
    private final StatisticsWriter statsWriter;
    private final boolean saveStats;
    private boolean active = true;
    private boolean running = true;

    public Simulation(WorldMap worldMap, SimulationConfig conf, GenomesFactory genomesFactory, boolean saveStats) {
        this.saveStats = saveStats;
        this.worldMap = worldMap;
        this.conf = conf;
        this.genomesFactory = genomesFactory;
        this.stats = new SimulationStats(worldMap, conf.getMapWidth(), conf.getMapHeight());
        this.animalSpawner = new AnimalSpawner(worldMap, conf, genomesFactory);
        this.plantSpawner = new PlantSpawner(worldMap, conf);
        this.animalSpawner.spawnAnimals();
        this.plantSpawner.spawnPlants(conf.getStartingPlantCount());
        statsWriter = new StatisticsWriter();
    }
    public void addListener(SimulationListener listener) {
        listeners.add(listener);
    }
    public void notifyListeners() {
        for (SimulationListener listener : listeners) {
            listener.dayPassed(worldMap, stats.getStatsRecord());
        }
    }

    public void run() {
        while (running) {
            if( !active){
                continue;
            }
            worldMap.removeDeadAnimals();
            worldMap.moveAnimals();
            worldMap.eatPlants();
            worldMap.reproduce();
            plantSpawner.spawnPlants(conf.getPlantsPerDay());
            stats.updateStats();
            notifyListeners();
            if (saveStats) {
                statsWriter.saveToCsv(stats.getStatsRecord());
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

        }
    }
    public void stop(){
        running = false;
    }
    public void pause(){
        active = !active;
    }


    public WorldMap getWorldMap() {
        return worldMap;
    }

    public SimulationConfig getCofig() {
        return conf;
    }
}
