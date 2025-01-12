package agh.ics.oop.model;



import java.util.*;

public class Simulation implements Runnable {
    private int day = 0;
    private final int center;
    private final AbstractMap worldMap;
    private final SimulationConfig conf;
    private final GenomesFactory genomesFactory;
    private final int preferredZoneStart;
    private final int preferredZoneEnd;
    private final Random random = new Random();
    public Simulation(AbstractMap worldMap, SimulationConfig conf, GenomesFactory genomesFactory){
        this.center = conf.getMapHeight()/2;
        preferredZoneStart = (int) (conf.getMapHeight() * (0.5 - 0.2 / 2));
        preferredZoneEnd = (int) (conf.getMapHeight() * (0.5 + 0.2 / 2));
        this.worldMap = worldMap;
        this.conf = conf;
        this.genomesFactory = genomesFactory;
        spawnAnimals();
        spawnPlants(conf.getStartingPlantCount());
    }
    public void run() {
        while (true) {
            removeDeadAnimals();
            moveAnimals();
            eatPlants();
            reproduce();
            dayFinished();
        }
    }

    private void dayFinished() {
        day++;
    }

    private void reproduce() {
        Collection<Box> values = worldMap.getBoxes().values();
        for (Box box: values){
            box.reproduce();
        }

    }

    private void eatPlants() {
        List<Box> boxesWithPlants = worldMap.getBoxesWithPlants();
        for (Box box: boxesWithPlants){
            box.eatPlant();
        }
    }

    private void moveAnimals(){
        List<Animal> animals = worldMap.getAnimals();
        for (Animal animal: animals){
            worldMap.moveAnimal(animal);
        }

    }
    private void removeDeadAnimals(){
        List<Animal> deadAnimals = worldMap.getDeadAnimals();
        for (Animal animal: deadAnimals){
            worldMap.remove(animal);
            animal.markAsDead();
        }
    }

    private void spawnAnimals(){
        int xMax = conf.getMapWidth();
        int yMax = conf.getMapHeight();
        for (int i = 0; i< conf.getStartingAnimalCount(); i++){
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

    private void spawnPlants(int cnt) {
        List<Vector2d> equatorCells = new ArrayList<>();
        List<Vector2d> otherCells = new ArrayList<>();
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
        worldMap.place(new Plant(cell, conf.getPlantEnergy()));
    }
//    public void spawnPlants(int cnt){
//        int i= 0;
//        List<Vector2d> equatorCells = new ArrayList<>();
//        List<Vector2d> otherCells = new ArrayList<>();
//        for (int y = 0; y < conf.getMapHeight(); y++) {
//            for (int x = 0; x < conf.getMapWidth(); x++) {
//                Vector2d vector2d = new Vector2d(x,y);
//                if (y >= preferredZoneStart && y <= preferredZoneEnd && worldMap.isNotPlanted(vector2d)) {
//                    equatorCells.add(new Vector2d(x,y));
//                } else if (worldMap.isNotPlanted(vector2d)) {
//                    otherCells.add(new Vector2d(x,y));
//                }
//            }
//        }
//        Collections.shuffle(equatorCells);
//        Collections.shuffle(otherCells);
//        while (i<cnt){
//            double v = random.nextDouble();
//            if (otherCells.isEmpty() && equatorCells.isEmpty()){
//                return;
//            }
//            else if (otherCells.isEmpty()){
//                Vector2d vector2d = equatorCells.removeLast();
//                worldMap.place(new Plant(vector2d, conf.getPlantEnergy()));
//                i++;
//            }
//            else if (equatorCells.isEmpty()){
//                Vector2d vector2d = otherCells.removeLast();
//                worldMap.place(new Plant(vector2d, conf.getPlantEnergy()));
//                i++;
//            }
//            else if (v <= 0.8){
//                Vector2d vector2d = equatorCells.removeLast();
//                worldMap.place(new Plant(vector2d, conf.getPlantEnergy()));
//                i++;
//            }
//            else if (v>0.8){
//                Vector2d vector2d = otherCells.removeLast();
//                worldMap.place(new Plant(vector2d, conf.getPlantEnergy()));
//                i++;
//            }
//        }
//
//
//    }



}
