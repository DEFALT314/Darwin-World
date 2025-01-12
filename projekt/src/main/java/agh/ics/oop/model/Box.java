package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public void reproduce() {

    }
}
