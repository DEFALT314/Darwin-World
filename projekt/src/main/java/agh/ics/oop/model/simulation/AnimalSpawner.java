package agh.ics.oop.model.simulation;

import agh.ics.oop.model.genomes.GenomesFactory;
import agh.ics.oop.model.map.WorldMap;
import agh.ics.oop.model.IncorrectPositionException;
import agh.ics.oop.model.worldelements.Vector2d;
import agh.ics.oop.model.worldelements.Animal;

import java.util.Random;

public class AnimalSpawner {
    private final SimulationConfig config;
    private final WorldMap worldMap;
    private final GenomesFactory genomesFactory;
    final Random random = new Random();

    public AnimalSpawner(WorldMap worldMap, SimulationConfig config, GenomesFactory genomesFactory) {
        this.config = config;
        this.worldMap = worldMap;
        this.genomesFactory = genomesFactory;
    }

    public void spawnAnimals() {
        int xMax = config.getMapWidth();
        int yMax = config.getMapHeight();
        for (int i = 0; i < config.getStartingAnimalCount(); i++) {
            int x_random = random.nextInt(xMax);
            int y_random = random.nextInt(yMax);
            Vector2d vector2d = new Vector2d(x_random, y_random);
            try {
                worldMap.place(new Animal(vector2d, config, genomesFactory));
            } catch (IncorrectPositionException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}