package agh.ics.oop.model;

import agh.ics.oop.model.Genomes.Genomes;
import agh.ics.oop.model.Genomes.GenomesFactory;
import agh.ics.oop.model.Genomes.NormalGenomesFactory;
import agh.ics.oop.model.Map.EarthMap;
import agh.ics.oop.model.Simulation.SimulationConfig;
import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.MapDirection;
import agh.ics.oop.model.WorldElements.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EarthMapTest {
    SimulationConfig conf = new SimulationConfig(4, 4, "ziemia",
            0, 0, 0, "0", 0,
            20, 30, 25, 2, 5,
            "", 4, "0");
    GenomesFactory factory = new NormalGenomesFactory();

    @Test
    void cannotPlaceAnimalOutsideTheMap() {
        EarthMap map = new EarthMap(5, 5);
        Animal animal = new Animal(new Vector2d(6, 6), conf, factory);
        assertThrows(IncorrectPositionException.class, () -> map.place(animal));
    }

    @Test
    void isObjectAt() {
        EarthMap map = new EarthMap(5, 5);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position, conf, factory);
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail();
        }
        assertTrue(map.objectAt(position).isPresent());
        assertEquals(animal, map.objectAt(position).get());
    }

    @Test
    void isAnimalAlive() {
        EarthMap map = new EarthMap(5, 5);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position, conf, factory);
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail();
        }
        assertTrue(map.getAliveAnimals().contains(animal));
    }

    @Test
    void moveChangesAnimalsPosition() {
        EarthMap map = new EarthMap(5, 5);
        Vector2d position = new Vector2d(2, 2);
        Genomes genome = new Genomes(List.of(0, 0, 0, 0), conf);
        Animal animal = new Animal(position, MapDirection.NORTH, genome, conf, factory);
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail();
        }
        map.moveAnimal(animal);
        if (map.objectAt(position).isPresent()) {
            assertNotEquals(animal, map.objectAt(position).get());
        }
        Vector2d position2 = position.add(MapDirection.NORTH.toUnitVector());
        assertTrue(map.objectAt(position2).isPresent());
        assertEquals(animal, map.objectAt(position2).get());
    }

    @Test
    void moveDecreasesEnergy() {
        EarthMap map = new EarthMap(5, 5);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position, conf, factory);
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail();
        }
        assertEquals(20, animal.getEnergy());
        map.moveAnimal(animal);
        assertEquals(19, animal.getEnergy());
    }

    @Test
    void isAnimalDeadIfNoEnergy() {
        EarthMap map = new EarthMap(5, 5);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position, conf, factory);
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail();
        }
        for (int i = 0; i < 21; i++) {
            map.moveAnimal(animal);
        }
        assertTrue(map.getDeadAnimals().contains(animal));
    }
}