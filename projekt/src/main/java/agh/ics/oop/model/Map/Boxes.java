package agh.ics.oop.model.Map;

import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.Plant;
import agh.ics.oop.model.WorldElements.Vector2d;
import agh.ics.oop.model.WorldElements.WorldElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Boxes {
    private final Map<Vector2d, Box> boxes;

    public Boxes() {
        this.boxes = new HashMap<>();
    }
    public boolean isNotPlanted(Vector2d location) {
        return boxes.get(location) == null || boxes.get(location).isPlanted();
    }
    public Map<Vector2d, Box> getBoxes() {
        return boxes;
    }
    public Optional<WorldElement> objectAt(Vector2d position) {
        if (!boxes.containsKey(position)) {
            return Optional.empty();
        }
        Optional<Animal> first = boxes.get(position).getAnimals().stream().findFirst();
        if (first.isPresent()) {
            return Optional.of(first.get());
        }
        Optional<Plant> plant = boxes.get(position).getPlant();
        if (plant.isPresent()) {
            return Optional.of(plant.get());
        }
        return Optional.empty();
    }
    public List<Vector2d> getEmptyPositions() {
        return boxes.entrySet().stream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<Box> getBoxesWithPlants() {
        return boxes.values().stream().filter(Box::isPlanted).toList();
    }

    public void addAnimal(Animal animal) {
        addBoxIfDontExist(animal.getPosition());
        boxes.get(animal.getPosition()).addAnimal(animal);
    }
    public void removeAnimal(Animal animal) {
        if ( boxes.containsKey(animal.getPosition()) ) {
            boxes.get(animal.getPosition()).removeAnimal(animal);
            removeBoxIfEmpty(animal.getPosition());
        }
    }
    public void removeBoxIfEmpty(Vector2d position) {
        if (boxes.get(position).isEmpty()) {
            boxes.remove(position);
        }
    }


    private void addBoxIfDontExist(Vector2d position) {
        boxes.computeIfAbsent(position, pos -> new Box());
    }

    public List<Animal> getDeadAnimals() {
        return getAnimals().stream().filter(Animal::isDead).toList();
    }

    public List<Animal> getAliveAnimals() {
        return getAnimals().stream().filter((Animal A) -> !A.isDead()).toList();
    }

    public List<Animal> getAnimals() {
        return boxes.values().stream()
                .flatMap(box -> box.getAnimals().stream())
                .toList();
    }

    public void addPlant(Plant plant) {
        addBoxIfDontExist(plant.getPosition());
        boxes.get(plant.getPosition()).setPlant(plant);
    }

    public int size() {
        return boxes.size();
    }
}
