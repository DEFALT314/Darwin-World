package agh.ics.oop.model.Map;

import agh.ics.oop.model.Genomes.GenomesAbstract;
import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.Plant;
import agh.ics.oop.model.WorldElements.Vector2d;
import agh.ics.oop.model.WorldElements.WorldElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
//struktura która zarząca polami tworząc i usuwając dynamicznie
public class Boxes {
    private final Map<Vector2d, Box> boxes;

    public Boxes() {
        this.boxes = new HashMap<>();
    }
    public boolean isPlanted(Vector2d location) {
        return boxes.containsKey(location) && boxes.get(location).isPlanted();
    }
    public List<Box> getBoxes() {
        return boxes.values().stream().toList();
    }
    public Optional<Animal> strongestAnimalAt(Vector2d position) {
        if (!boxes.containsKey(position)) {
            return Optional.empty();
        }
        return boxes.get(position).getStrongestAnimal();
    }

    public Optional<WorldElement> strongestObjectAt(Vector2d pos){
        if (!boxes.containsKey(pos)){
            return Optional.empty();
        }
        var animal = strongestAnimalAt(pos);
        var plant = boxes.get(pos).getPlant();
        if( animal.isPresent()){
            return Optional.of(animal.get());
        } else if (plant.isPresent()){
            return Optional.of(plant.get());
        }
        else {
            return Optional.empty();
        }

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

    public List<Box> getBoxesWithPlants() {
        return boxes.values().stream().filter(Box::isPlanted).toList();
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

    public List<Animal> getAnimalsWithGenome(GenomesAbstract genomesAbstract) {
        return getAnimals().stream()
                .filter(animal -> animal.getGenome().equals(genomesAbstract))
                .toList();
    }
}
