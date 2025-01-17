package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AnimalContainer {
    private final List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public boolean isEmpty() {
        return animals.isEmpty();
    }

    public Optional<Animal> getStrongestAnimal() {
        if (animals.isEmpty()) return Optional.empty();
        sortAnimals();
        return Optional.of(animals.getFirst());
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
