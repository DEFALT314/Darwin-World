package agh.ics.oop.model;

import java.util.*;

public class Box {
    private final List<Animal> animals = new ArrayList<>();
    private Plant plant;
    public void addAnimal(Animal animal){
        animals.add(animal);
    }
    public void removeAnimal(Animal animal){
        animals.remove(animal);
    }
    public List<Animal> getAnimals(){
        return animals;
    }
    public void setPlant(Plant plant){
        this.plant = plant;
    }
    public boolean isPlanted(){
        return plant != null;
    }
    public boolean isEmpty(){
        return animals.isEmpty() && plant == null;
    }

    public Optional<Plant> getPlant() {
        return Optional.of(plant);
    }

    public void eatPlant() {
        if (plant == null || animals.isEmpty()) return;
        sortAnimals();
        Animal animal = animals.get(0);
        if(!animal.isDead()){
            animal.addEnergy(plant.getEnergy());
            plant = null;
        }
    }

    private void sortAnimals(){
        Collections.sort(animals, Comparator.comparingInt(Animal::getEnergy).thenComparingInt(Animal::getAge)
                .thenComparingInt(Animal::getChildrenCnt));
        Collections.reverse(animals);
    }

    public Optional<Animal> reproduce() {
        sortAnimals();
        if (animals.size() <2 || animals.get(0).getEnergy() <0 || animals.get(0).getEnergy() <0) return Optional.empty();
        Optional<Animal> animal =  animals.getFirst().reproduce(animals.get(1));
        animal.ifPresent(this::addAnimal);
        return animal;


    }
}
