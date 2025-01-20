package agh.ics.oop.model.Map;

import agh.ics.oop.model.Genomes.Genomes;
import agh.ics.oop.model.Genomes.GenomesFactory;
import agh.ics.oop.model.Genomes.NormalGenomesFactory;
import agh.ics.oop.model.Simulation.SimulationConfig;
import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.MapDirection;
import agh.ics.oop.model.WorldElements.Plant;
import agh.ics.oop.model.WorldElements.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoxesTest {
    SimulationConfig conf = new SimulationConfig(4, 4, "ziemia",
            0, 0, 0, "0", 0,
            20, 30, 25, 2, 5,
            "", 4, "0");
    SimulationConfig conf2 = new SimulationConfig(4, 4, "ziemia",
            0, 0, 0, "0", 0,
            0, 30, 25, 2, 5,
            "", 4, "0");
    GenomesFactory factory = new NormalGenomesFactory();
    @Test
    void boxesEmptyOnStart() {
        Boxes boxes = new Boxes();
        assertTrue(boxes.getBoxes().isEmpty());
    }

    @Test
    void addingAnimalAddsBox() {
        Boxes boxes = new Boxes();
        Vector2d position = new Vector2d(0, 0);
        Animal animal = new Animal(position, conf, factory);
        boxes.addAnimal(animal);
        assertTrue(boxes.getAnimals().contains(animal));
        assertEquals(1, boxes.getBoxes().size());
    }

    @Test
    void removingAnimalRemovesBox() {
        Boxes boxes = new Boxes();
        Vector2d position = new Vector2d(0, 0);
        Animal animal = new Animal(position, conf, factory);
        boxes.addAnimal(animal);
        assertTrue(boxes.getAnimals().contains(animal));
        assertEquals(1, boxes.getBoxes().size());
        boxes.removeAnimal(animal);
        assertFalse(boxes.getAnimals().contains(animal));
        assertEquals(0, boxes.getBoxes().size());
    }

    @Test
    void addingPlantAddsBox() {
        Boxes boxes = new Boxes();
        Vector2d position = new Vector2d(0, 0);
        Plant plant = new Plant(position, 0);
        boxes.addPlant(plant);
        assertTrue(boxes.isPlanted(position));
        assertEquals(1, boxes.getBoxes().size());
    }

    @Test
    void deadAndAliveAnimalsCountTest() {
        Boxes boxes = new Boxes();
        Vector2d position = new Vector2d(0, 0);
        Animal animal1 = new Animal(position, conf, factory);
        boxes.addAnimal(animal1);
        assertFalse(boxes.getDeadAnimals().contains(animal1));
        assertTrue(boxes.getAliveAnimals().contains(animal1));
        assertEquals(0, boxes.getDeadAnimals().size());
        assertEquals(1, boxes.getAliveAnimals().size());
        boxes.removeAnimal(animal1);
        Animal animal2 = new Animal(position, conf2, factory);
        boxes.addAnimal(animal2);
        assertTrue(boxes.getDeadAnimals().contains(animal2));
        assertFalse(boxes.getAliveAnimals().contains(animal2));
        assertEquals(1, boxes.getDeadAnimals().size());
        assertEquals(0, boxes.getAliveAnimals().size());
    }

    @Test
    void strongestAnimalTest() {
        Boxes boxes = new Boxes();
        Vector2d position = new Vector2d(0, 0);
        Animal animal1 = new Animal(position, conf, factory);
        boxes.addAnimal(animal1);
        assertEquals(animal1, boxes.strongestAnimalAt(position).get());
        Animal animal2 = new Animal(position, conf, factory);
        boxes.addAnimal(animal2);
        animal2.eatPlant(5);
        assertEquals(animal2, boxes.strongestAnimalAt(position).get());
    }

    @Test
    void strongestObjectTest() {
        Boxes boxes = new Boxes();
        Vector2d position = new Vector2d(0, 0);
        assertFalse(boxes.strongestObjectAt(position).isPresent());
        Plant plant = new Plant(position, 0);
        boxes.addPlant(plant);
        assertEquals(plant, boxes.strongestObjectAt(position).get());
        Animal animal1 = new Animal(position, conf, factory);
        boxes.addAnimal(animal1);
        assertEquals(animal1, boxes.strongestObjectAt(position).get());
        Animal animal2 = new Animal(position, conf, factory);
        boxes.addAnimal(animal2);
        animal2.eatPlant(5);
        assertEquals(animal2, boxes.strongestObjectAt(position).get());
    }

    @Test
    void getAnimalsWithGenomeTest() {
        Boxes boxes = new Boxes();
        Vector2d position = new Vector2d(0, 0);
        Genomes genome1 = new Genomes(List.of(0, 0, 0, 0), conf);
        Genomes genome2 = new Genomes(List.of(0, 1, 0, 0), conf);
        Animal animal1 = new Animal(position, MapDirection.NORTH, genome1, conf, factory);
        Animal animal2 = new Animal(position, MapDirection.NORTH, genome1, conf, factory);
        Animal animal3 = new Animal(position, MapDirection.NORTH, genome2, conf, factory);
        boxes.addAnimal(animal1);
        boxes.addAnimal(animal2);
        boxes.addAnimal(animal3);
        assertTrue(boxes.getAnimalsWithGenome(genome1).contains(animal1));
        assertTrue(boxes.getAnimalsWithGenome(genome1).contains(animal2));
        assertFalse(boxes.getAnimalsWithGenome(genome1).contains(animal3));
        assertFalse(boxes.getAnimalsWithGenome(genome2).contains(animal1));
        assertFalse(boxes.getAnimalsWithGenome(genome2).contains(animal2));
        assertTrue(boxes.getAnimalsWithGenome(genome2).contains(animal3));
    }
}