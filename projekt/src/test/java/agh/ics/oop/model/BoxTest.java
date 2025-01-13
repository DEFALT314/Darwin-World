package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {
    SimulationConfig conf = new SimulationConfig(4, 4, "ziemia",
            0, 0, 0, "0", 0,
            20, 30, 20, 2, 5,
            0, 4, "0");
    GenomesFactory factory = new NormalGenomesFactory();

    @Test
    void isBoxEmpty() {
        Box box = new Box();
        assertTrue(box.isEmpty());
    }

    @Test
    void addAnimalTest() {
        Box box = new Box();
        Animal animal = new Animal(new Vector2d(2, 2), conf, factory);
        box.addAnimal(animal);
        assertTrue(box.getAnimals().contains(animal));
    }

    @Test
    void removeAnimalTest() {
        Box box = new Box();
        Animal animal = new Animal(new Vector2d(2, 2), conf, factory);
        box.addAnimal(animal);
        assertTrue(box.getAnimals().contains(animal));
        box.removeAnimal(animal);
        assertFalse(box.getAnimals().contains(animal));
    }

    @Test
    void addPlantTest() {
        Box box = new Box();
        Plant plant = new Plant(new Vector2d(2, 2), 5);
        box.setPlant(plant);
        assertTrue(box.isPlanted());
        assertEquals(plant, box.getPlant().get());
    }

    @Test
    void eatingPlantRemovesItFromTheBox() {
        Box box = new Box();
        Animal animal = new Animal(new Vector2d(2, 2), conf, factory);
        Plant plant = new Plant(new Vector2d(2, 2), 5);
        box.addAnimal(animal);
        box.setPlant(plant);
        box.eatPlant();
        assertFalse(box.isPlanted());
    }

    @Test
    void eatingPlantIncreasesAnimalsEnergy() {
        Box box = new Box();
        Animal animal = new Animal(new Vector2d(2, 2), conf, factory);
        Plant plant = new Plant(new Vector2d(2, 2), 5);
        box.addAnimal(animal);
        box.setPlant(plant);
        box.eatPlant();
        assertEquals(25, box.getAnimals().getFirst().getEnergy());
    }

    @Test
    void reproductionOnTheBox() {
        Box box = new Box();
        Animal animal1 = new Animal(new Vector2d(2, 2), conf, factory);
        Animal animal2 = new Animal(new Vector2d(2, 2), conf, factory);
        Plant plant = new Plant(new Vector2d(2, 2), 5);
        box.setPlant(plant);
        box.addAnimal(animal1);
        box.eatPlant();
        box.addAnimal(animal2);
        Optional<Animal> child = box.reproduce();
        assertTrue(child.isPresent());
        assertEquals(animal1, child.get().getInfo().getParentOne());
        assertEquals(animal2, child.get().getInfo().getParentTwo());
    }
}