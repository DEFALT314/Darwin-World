package agh.ics.oop.model.map;

import agh.ics.oop.model.genomes.Genomes;
import agh.ics.oop.model.genomes.GenomesFactory;
import agh.ics.oop.model.genomes.NormalGenomesFactory;
import agh.ics.oop.model.IncorrectPositionException;
import agh.ics.oop.model.simulation.SimulationConfig;
import agh.ics.oop.model.worldelements.Animal;
import agh.ics.oop.model.worldelements.MapDirection;
import agh.ics.oop.model.worldelements.Plant;
import agh.ics.oop.model.worldelements.Vector2d;
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
    void getBoundsTest() {
        EarthMap map = new EarthMap(5, 5);
        assertEquals(new Boundary(new Vector2d(0, 0), new Vector2d(4, 4)), map.getCurrentBounds());
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
        for (int i = 0; i < 20; i++) {
            map.moveAnimal(animal);
        }
        assertTrue(map.getDeadAnimals().contains(animal));
    }

    @Test
    void plantsCountTest() {
        EarthMap map = new EarthMap(5, 5);
        Plant plant1 = new Plant(new Vector2d(2, 2), 5);
        Plant plant2 = new Plant(new Vector2d(2, 3), 5);
        Animal animal = new Animal(new Vector2d(2, 2), conf, factory);
        assertEquals(0, map.plantsCount());
        map.placePlant(plant1);
        assertEquals(1, map.plantsCount());
        map.placePlant(plant2);
        assertEquals(2, map.plantsCount());
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail();
        }
        map.eatPlants();
        assertEquals(1, map.plantsCount());
    }

    @Test
    void aliveAnimalsCountTest() {
        EarthMap map = new EarthMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2), conf, factory);
        assertEquals(0, map.aliveAnimalsCount());
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail();
        }
        assertEquals(1, map.aliveAnimalsCount());
    }

    @Test
    void deadAnimalsCountTest() {
        EarthMap map = new EarthMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2), conf, factory);
        assertEquals(0, map.deadAnimalsCount());
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail();
        }
        for (int i = 0; i < 20; i++) {
            map.moveAnimal(animal);
        }
        assertEquals(1, map.deadAnimalsCount());
    }

    @Test
    void emptyPositionsCountTest() {
        EarthMap map = new EarthMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2), conf, factory);
        Animal animal2 = new Animal(new Vector2d(2, 3), conf, factory);
        Animal animal3 = new Animal(new Vector2d(2, 3), conf, factory);
        assertEquals(25, map.emptyPositionsCount());
        try {
            map.place(animal1);
        } catch (IncorrectPositionException e) {
            fail();
        }
        assertEquals(24, map.emptyPositionsCount());
        try {
            map.place(animal2);
        } catch (IncorrectPositionException e) {
            fail();
        }
        assertEquals(23, map.emptyPositionsCount());
        try {
            map.place(animal3);
        } catch (IncorrectPositionException e) {
            fail();
        }
        assertEquals(23, map.emptyPositionsCount());
    }
}