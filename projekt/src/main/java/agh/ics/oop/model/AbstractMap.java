package agh.ics.oop.model;

import java.util.*;

public abstract class AbstractMap implements WorldMap{
    private final Boundary boundary;
    private final int height;
    private final int width;
    private final Map<Vector2d, Box> boxes;


    public AbstractMap(int width, int height) {
        this.height = height;
        this.width = width;
        this.boundary = new Boundary(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
        this.boxes = new HashMap<>();
    }
    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition())) {
            addBoxIfDontExist(animal.getPosition());
            boxes.get(animal.getPosition()).addAnimal(animal);
        }

    }

    @Override
    public void moveAnimal(Animal animal) {
        boxes.get(animal.getPosition()).removeAnimal(animal);
        removeBoxIfEmpty(animal.getPosition());
        reduceEnergyToMove(animal);
        animal.move(boundary);
        addBoxIfDontExist(animal.getPosition());
        boxes.get(animal.getPosition()).addAnimal(animal);
    }

    public void eat(Plant plant) {
        
    }
    protected abstract void reduceEnergyToMove(Animal animal);

    private void addBoxIfDontExist(Vector2d position) {
        if (checkIfBoxExists(position)) {
            boxes.put(position, new Box());
        }
    }
    private boolean checkIfBoxExists(Vector2d position) {
        return !boxes.containsKey(position);
    }
    public void remove(Animal animal) {
        if (canMoveTo(animal.getPosition()) && checkIfBoxExists(animal.getPosition())) {
            boxes.get(animal.getPosition()).removeAnimal(animal);
            removeBoxIfEmpty(animal.getPosition());
        }
    }
    public void removeBoxIfEmpty(Vector2d position) {
        if (boxes.get(position).isEmpty()) {
            boxes.remove(position);
        }
    }
    public boolean isNotPlanted(Vector2d location) {
        return boxes.get(location) == null || boxes.get(location).isPlanted();
    }

    public List<Vector2d> getEmptyPositions() {
        return boxes.entrySet().stream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .toList();
    }
    public List<Box> getBoxesWithPlants(){
        return boxes.values().stream().filter(Box::isPlanted).toList();
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public Boundary getCurrentBounds() {
        return null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return null;
    }


    public Map<Vector2d, Box> getBoxes() {
        return boxes;
    }
    public List<Animal> getDeadAnimals() {
        return  getAnimals().stream().filter((Animal A) -> A.isDead()).toList();
    }
    public List<Animal> getAliveAnimals() {
        return getAnimals().stream().filter((Animal A) -> !A.isDead()).toList();
    }
    public List<Animal> getAnimals() {
        return boxes.values().stream()
                .flatMap(box -> box.getAnimals().stream())
                .toList();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    public void place(Plant plant) {
        addBoxIfDontExist(plant.getPosition());
        boxes.get(plant.getPosition()).setPlant(plant);
    }
}
