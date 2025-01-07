package agh.ics.oop.model;

import java.util.*;

public class Map implements WorldMap{
    private final Boundary boundary;
    private final int height;
    private final int width;
    private final HashMap<Vector2d, Box> boxes;


    public Map(int width, int height) {
        this.height = height;
        this.width = width;
        this.boundary = new Boundary(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
        this.boxes = new HashMap<>();
    }
    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition())) {
            boxes.get(animal.getPosition()).addAnimal(animal);
        }

    }
    public void remove(Animal animal) throws IncorrectPositionException {

        if (canMoveTo(animal.getPosition())) {
            boxes.get(animal.getPosition()).removeAnimal(animal);
        }

    }
    public Optional<Plant> getPlant(Vector2d location) {
        return  Optional.of(boxes.get(location).getPlant());
    }


    @Override
    public void move(Animal animal) {

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

    @Override
    public Collection<WorldElement> getElements() {
        return List.of();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }
}
