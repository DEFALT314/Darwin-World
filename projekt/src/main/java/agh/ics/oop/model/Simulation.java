package agh.ics.oop.model;


import java.util.*;

public class Simulation implements Runnable {
    private final AbstractMap worldMap;
    private final SimulationConfig conf;
    private final GenomesFactory genomesFactory;
    private final Random random = new Random();
    private final PlantSpawner plantSpawner;
    private int day = 0;

    public Simulation(AbstractMap worldMap, SimulationConfig conf, GenomesFactory genomesFactory) {
        this.worldMap = worldMap;
        this.conf = conf;
        this.genomesFactory = genomesFactory;
        this.plantSpawner = new PlantSpawner(worldMap, conf);
        spawnAnimals();
        plantSpawner.spawnPlants(conf.getStartingPlantCount());
    }

    public void run() {
        while (true) {
            removeDeadAnimals();
            moveAnimals();
            eatPlants();
            reproduce();
            plantSpawner.spawnPlants(conf.getPlantsPerDay());
            dayFinished();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void removeDeadAnimals() {
        List<Animal> deadAnimals = worldMap.getDeadAnimals();
        for (Animal animal : deadAnimals) {
            worldMap.remove(animal);
        }
    }

    private void moveAnimals() {
        List<Animal> animals = worldMap.getAnimals();
        for (Animal animal : animals) {
            worldMap.moveAnimal(animal);
        }

    }

    private void reproduce() {
        Collection<Box> values = worldMap.getBoxes().values();
        for (Box box : values) {
            box.reproduce();
        }
    }

    private void dayFinished() {
        day++;
    }

    private void eatPlants() {
        List<Box> boxesWithPlants = worldMap.getBoxesWithPlants();
        for (Box box : boxesWithPlants) {
            box.eatPlant();
        }
    }

    private void spawnAnimals() {
        int xMax = conf.getMapWidth();
        int yMax = conf.getMapHeight();
        for (int i = 0; i < conf.getStartingAnimalCount(); i++) {
            int x_random = random.nextInt(xMax);
            int y_random = random.nextInt(yMax);
            Vector2d vector2d = new Vector2d(x_random, y_random);
            try {
                worldMap.place(new Animal(vector2d, conf, genomesFactory));
            } catch (IncorrectPositionException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
