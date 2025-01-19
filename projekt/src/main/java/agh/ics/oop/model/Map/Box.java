package agh.ics.oop.model.Map;

import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.Plant;

import java.util.List;
import java.util.Optional;

public class Box {
    private final AnimalBox animalGroup = new AnimalBox();
    private final PlantBox plantBox = new PlantBox();

    public void addAnimal(Animal animal) {
        animalGroup.addAnimal(animal);
    }

    public void removeAnimal(Animal animal) {
        animalGroup.removeAnimal(animal);
    }

    public List<Animal> getAnimals() {
        return animalGroup.getAnimals();
    }

    public boolean isPlanted() {
        return plantBox.isPlanted();
    }

    public boolean isEmpty() {
        return animalGroup.isEmpty() && plantBox.isEmpty();
    }

    public Optional<Plant> getPlant() {
        return plantBox.getPlant();
    }

    public void setPlant(Plant plant) {
        plantBox.setPlant(plant);
    }

    public void eatPlant() {
        if (!plantBox.isPlanted() || animalGroup.isEmpty()) return;
        Optional<Animal> strongestAnimal = animalGroup.getStrongestAnimal();
        strongestAnimal.ifPresent(animal -> {
            if (!animal.isDead()) {
                animal.eatPlant(plantBox.consumePlant());
            }
        });
    }

    public Optional<Animal> reproduce() {
        return animalGroup.reproduceAnimals();
    }
}
