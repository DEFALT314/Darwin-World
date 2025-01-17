package agh.ics.oop.model;

import java.util.List;
import java.util.Optional;

public class Box {
    private final AnimalContainer animalGroup = new AnimalContainer();
    private final PlantContainer plantContainer = new PlantContainer();

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
        return plantContainer.isPlanted();
    }

    public boolean isEmpty() {
        return animalGroup.isEmpty() && plantContainer.isEmpty();
    }

    public Optional<Plant> getPlant() {
        return plantContainer.getPlant();
    }

    public void setPlant(Plant plant) {
        plantContainer.setPlant(plant);
    }

    public void eatPlant() {
        if (!plantContainer.isPlanted() || animalGroup.isEmpty()) return;
        Optional<Animal> strongestAnimal = animalGroup.getStrongestAnimal();
        strongestAnimal.ifPresent(animal -> {
            if (!animal.isDead()) {
                animal.addEnergy(plantContainer.consumePlant());
            }
        });
    }

    public Optional<Animal> reproduce() {
        return animalGroup.reproduceAnimals();
    }
}
