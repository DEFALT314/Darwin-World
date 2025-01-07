package agh.ics.oop.model;

import javax.naming.ConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class Box {
    private final List<Animal> animals = new ArrayList<>();
    private Plant plant;
    private int energyDecreas= 1;

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
    public Plant getPlant(){
        return plant;
    }
    public boolean isEmpty(){
        return animals.isEmpty() && plant == null;
    }

}
