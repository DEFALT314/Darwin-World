package agh.ics.oop.model.simulation;


import agh.ics.oop.model.genomes.GenomesFactory;
import agh.ics.oop.model.map.WorldMap;
import agh.ics.oop.model.stats.SimulationStats;
import agh.ics.oop.model.stats.StatisticsWriter;
import agh.ics.oop.model.worldelements.Animal;
import agh.ics.oop.model.worldelements.Vector2d;

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
        this.stats = new SimulationStats(worldMap);
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
    public List<Vector2d> getPreferredPlantLocations() {
        return plantSpawner.getPreferredPlantLocations();
    }
    public boolean isActive() {
        return active;
    }

    public void run() {
        while (running) {

            if( !active){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
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
                Thread.sleep(conf.getDayLength());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

        }
    }
    public void stop(){
        running = false;
    }
    public synchronized void pause(){
        active = !active;
    }


    public WorldMap getWorldMap() {
        return worldMap;
    }

    public SimulationConfig getConfig() {
        return conf;
    }

    public List<Animal> getAnimalsWithCommonGenome() {
        return worldMap.getAnimalsWithGenome(stats.getStatsRecord().mostCommonGenome());
    }
}
