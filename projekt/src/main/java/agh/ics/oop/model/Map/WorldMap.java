package agh.ics.oop.model.Map;

import agh.ics.oop.model.Genomes.GenomesAbstract;
import agh.ics.oop.model.IncorrectPositionException;
import agh.ics.oop.model.WorldElements.Plant;
import agh.ics.oop.model.WorldElements.Vector2d;
import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.WorldElement;

import java.util.List;
import java.util.Optional;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap extends MoveValidator {

    /**
     * Place a animal on the map.
     *
     * @param animal The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the move is not valid.
     */
    void place(Animal animal) throws IncorrectPositionException;

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void moveAnimal(Animal animal);
    void remove(Animal animal);
    void placePlant(Plant plant);
        /**
         * Return true if given position on the map is occupied. Should not be
         * confused with canMove since there might be empty positions where the animal
         * cannot move.
         *
         * @param position Position to check.
         * @return True if the position is occupied.
         */


    boolean isPlanted(Vector2d cell);

    Optional<WorldElement> objectAt(Vector2d pos);
    void removeDeadAnimals();

    void moveAnimals();

    void eatPlants();

    void reproduce();

    List<Animal> getAliveAnimals();
    List<Animal> getDeadAnimals();
    int aliveAnimalsCount();
    int deadAnimalsCount();
    int plantsCount();
    int emptyPositionsCount();
    int deadAnimalsAgeSum();

    Boundary getCurrentBounds();

    List<Animal> getAnimalsWithGenome(GenomesAbstract genomesAbstract);

    Optional<Animal> getStrongestAnimalAt(Vector2d p);

}
