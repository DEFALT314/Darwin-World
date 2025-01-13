package agh.ics.oop.model;

import java.util.*;

public class AnimalContainer {
    private final List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals); // Return a copy to prevent external modification
    }

    public boolean isEmpty() {
        return animals.isEmpty();
    }

    public Optional<Animal> getStrongestAnimal() {
        if (animals.isEmpty()) return Optional.empty();
        sortAnimals();
        return Optional.of(animals.get(0));
    }

    public Optional<Animal> reproduceAnimals() {
        sortAnimals();
        if (animals.size() < 2 || animals.get(0).getEnergy() <= 0 || animals.get(1).getEnergy() <= 0) {
            return Optional.empty();
        }
        Optional<Animal> newAnimal = animals.get(0).reproduce(animals.get(1));
        newAnimal.ifPresent(animals::add);
        return newAnimal;
    }

    private void sortAnimals() {
        animals.sort(Comparator.comparingInt(Animal::getEnergy)
                .thenComparingInt(Animal::getAge)
                .thenComparingInt(Animal::getChildrenCnt).reversed());
    }
}
