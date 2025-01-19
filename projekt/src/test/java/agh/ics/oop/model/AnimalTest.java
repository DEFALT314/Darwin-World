package agh.ics.oop.model;

import agh.ics.oop.model.Genomes.Genomes;
import agh.ics.oop.model.Genomes.GenomesFactory;
import agh.ics.oop.model.Genomes.NormalGenomesFactory;
import agh.ics.oop.model.Map.Boundary;
import agh.ics.oop.model.Simulation.SimulationConfig;
import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.MapDirection;
import agh.ics.oop.model.WorldElements.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    SimulationConfig conf = new SimulationConfig(4, 4, "ziemia",
            0, 0, 0, "0", 0,
            20, 30, 25, 2, 5,
            "", 4, "0");
    GenomesFactory genomesFactory = new NormalGenomesFactory();
    @Test
    void startingEnergyTest() {
        Animal animal = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        assertEquals(20, animal.getEnergy());
    }

    @Test
    void addEnergyTest() {
        Animal animal = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        animal.eatPlant(5);
        assertEquals(25, animal.getEnergy());
    }

    @Test
    void subtractEnergyTest() {
        Animal animal = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        animal.subtractEnergy(5);
        assertEquals(15, animal.getEnergy());
    }

    @Test
    void moveTest() {
        Genomes genome = new Genomes(List.of(0, 0, 0, 0), conf);
        Animal animal = new Animal(new Vector2d(2, 2), MapDirection.NORTH, genome, conf, genomesFactory);
        Boundary boundary = new Boundary(new Vector2d(0, 0), new Vector2d(3, 3));
        animal.move(boundary);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    void cannotMoveBeyondPoles() {
        Genomes genome = new Genomes(List.of(0, 0, 0, 0), conf);
        Animal animal = new Animal(new Vector2d(2, 2), MapDirection.NORTH, genome, conf, genomesFactory);
        Boundary boundary = new Boundary(new Vector2d(0, 0), new Vector2d(3, 3));
        animal.move(boundary);
        animal.move(boundary);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertEquals(MapDirection.SOUTH, animal.getOrientation());
    }

    @Test
    void earthRoundnessTest() {
        Genomes genome = new Genomes(List.of(0, 0, 0, 0), conf);
        Animal animal = new Animal(new Vector2d(2, 2), MapDirection.EAST, genome, conf, genomesFactory);
        Boundary boundary = new Boundary(new Vector2d(0, 0), new Vector2d(3, 3));
        animal.move(boundary);
        animal.move(boundary);
        assertEquals(new Vector2d(0, 2), animal.getPosition());
    }

    @Test
    void notEnoughEnergyToReproduce() {
        Animal animal1 = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        Animal animal2 = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        animal1.eatPlant(5);
        assertEquals(Optional.empty(), animal1.reproduce(animal2));
        assertEquals(Optional.empty(), animal2.reproduce(animal1));
    }

    @Test
    void reproduceTestRandomGenomes() {
        Animal animal1 = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        Animal animal2 = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        animal1.eatPlant(5);
        animal2.eatPlant(5);
        Optional<Animal> child = animal1.reproduce(animal2);
        assertTrue(child.isPresent());
        assertEquals(animal1, child.get().getInfo().getParentOne());
        assertEquals(animal2, child.get().getInfo().getParentTwo());
        assertEquals(1, animal1.getInfo().getDescendantCount());
        assertEquals(1, animal2.getInfo().getDescendantCount());
    }

    @Test
    void descendantCountTest() {
        Animal animal1 = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        Animal animal2 = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        Animal animal3 = new Animal(new Vector2d(2, 2), conf, genomesFactory);
        animal1.eatPlant(10);
        animal2.eatPlant(10);
        animal3.eatPlant(10);
        Optional<Animal> child = animal1.reproduce(animal2);
        child.get().eatPlant(30);
        Optional<Animal> child2 = animal3.reproduce(child.get());
        assertEquals(2, animal1.getInfo().getDescendantCount());
        assertEquals(2, animal2.getInfo().getDescendantCount());
        assertEquals(1, animal3.getInfo().getDescendantCount());
        assertEquals(1, child.get().getInfo().getDescendantCount());
        assertEquals(0, child2.get().getInfo().getDescendantCount());
    }
}