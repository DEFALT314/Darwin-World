package agh.ics.oop.model.Simulation;

import agh.ics.oop.model.Genomes.GenomesAbstract;
import agh.ics.oop.model.Genomes.GenomesFactory;
import agh.ics.oop.model.Map.WorldMap;
import agh.ics.oop.model.IncorrectPositionException;
import agh.ics.oop.model.WorldElements.Vector2d;
import agh.ics.oop.model.WorldElements.Animal;

import java.util.List;
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