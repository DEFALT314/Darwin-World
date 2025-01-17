package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PlantSpawner {
    private final WorldMap worldMap;
    private final Random random = new Random();
    private final SimulationConfig conf;
    private final int preferredZoneStart;
    private final int preferredZoneEnd;

    public PlantSpawner(WorldMap worldMap, SimulationConfig conf) {
        this.worldMap = worldMap;
        this.conf = conf;
        preferredZoneStart = (int) (conf.getMapHeight() * (0.5 - 0.2 / 2));
        preferredZoneEnd = (int) (conf.getMapHeight() * (0.5 + 0.2 / 2));
    }


    public void spawnPlants(int cnt) {
        List<Vector2d> equatorCells = new ArrayList<Vector2d>();
        List<Vector2d> otherCells = new ArrayList<Vector2d>();
        partitionCells(equatorCells, otherCells);
        Collections.shuffle(equatorCells);
        Collections.shuffle(otherCells);
        int plantedCount = 0;
        while (plantedCount < cnt && (!equatorCells.isEmpty() || !otherCells.isEmpty())) {
            double randomValue = random.nextDouble();
            if (!equatorCells.isEmpty() && (randomValue <= 0.8 || otherCells.isEmpty())) {
                placePlantOnCell(equatorCells);
            } else if (!otherCells.isEmpty()) {
                placePlantOnCell(otherCells);
            }
            plantedCount++;
        }
    }

    private void partitionCells(List<Vector2d> equatorCells, List<Vector2d> otherCells) {
        for (int y = 0; y < conf.getMapHeight(); y++) {
            for (int x = 0; x < conf.getMapWidth(); x++) {
                Vector2d cell = new Vector2d(x, y);
                if (worldMap.isNotPlanted(cell)) {
                    if (y >= preferredZoneStart && y <= preferredZoneEnd) {
                        equatorCells.add(cell);
                    } else {
                        otherCells.add(cell);
                    }
                }
            }
        }
    }

    private void placePlantOnCell(List<Vector2d> cells) {
        Vector2d cell = cells.removeLast();
        simulation.getWorldMap().placePlant(new Plant(cell, simulation.getConf().getPlantEnergy()));
    }
}