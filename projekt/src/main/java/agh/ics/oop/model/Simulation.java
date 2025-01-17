package agh.ics.oop.model;


public class Simulation implements Runnable {
    private final WorldMap worldMap;
    private final SimulationConfig conf;
    private final GenomesFactory genomesFactory;
    private final PlantSpawner plantSpawner;
    private final AnimalSpawner animalSpawner;
    private int day = 0;

    public Simulation(AbstractMap worldMap, SimulationConfig conf, GenomesFactory genomesFactory) {
        this.worldMap = worldMap;
        this.conf = conf;
        this.genomesFactory = genomesFactory;
        this.animalSpawner = new AnimalSpawner(worldMap, conf, genomesFactory);
        this.plantSpawner = new PlantSpawner(worldMap, conf);
        this.animalSpawner.spawnAnimals();
        this.plantSpawner.spawnPlants(conf.getStartingPlantCount());
    }

    public void run() {
        while (true) {
            worldMap.removeDeadAnimals();
            worldMap.moveAnimals();
            worldMap.eatPlants();
            worldMap.reproduce();
            plantSpawner.spawnPlants(conf.getPlantsPerDay());
            dayFinished();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void dayFinished() {
        day++;
    }


}
