package agh.ics.oop.model.Map;

import agh.ics.oop.model.*;
import agh.ics.oop.model.Genomes.GenomesAbstract;
import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.Plant;
import agh.ics.oop.model.WorldElements.Vector2d;
import agh.ics.oop.model.WorldElements.WorldElement;

import java.util.List;
import java.util.Optional;

public abstract class AbstractMap implements WorldMap {
    private final Boundary boundary;
    private final Boxes boxesContainer = new Boxes();
    private final DeadAnimalsAgeTracker deadAnimals = new DeadAnimalsAgeTracker();
    private final int width;
    private final int height;

    public AbstractMap(int width, int height) {
        this.boundary = new Boundary(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
        this.height = height;
        this.width = width;
    }

    public void eatPlants() {
        getBoxesWithPlants().forEach(Box::eatPlant);
    }

    public void removeDeadAnimals() {
        getDeadAnimals().forEach(this::remove);
    }

    public void moveAnimals() {
        getAnimals().forEach(this::moveAnimal);
    }

    public void reproduce() {
        boxesContainer.getBoxes().forEach(Box::reproduce);
    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition())) {
            boxesContainer.addAnimal(animal);
        } else {
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    @Override
    public void moveAnimal(Animal animal) {
        boxesContainer.removeAnimal(animal);
        reduceEnergyToMove(animal);
        animal.move(boundary);
        boxesContainer.addAnimal(animal);
        if( animal.isDead()){
            deadAnimals.addDeadAnimal(animal);
        }
    }

    protected abstract void reduceEnergyToMove(Animal animal);


    @Override
    public void remove(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            boxesContainer.removeAnimal(animal);
        }

    }
    @Override
    public Optional<Animal> getStrongestAnimalAt(Vector2d position) {
        return boxesContainer.strongestAnimalAt(position);
    }


    public boolean isPlanted(Vector2d location) {
        return boxesContainer.isPlanted(location);
    }



    private List<Box> getBoxesWithPlants() {
        return boxesContainer.getBoxesWithPlants();
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(boundary.downLeft()) && position.precedes(boundary.upperRight());
    }

    @Override
    public void placePlant(Plant plant) {
        if (canMoveTo(plant.getPosition())) {
            boxesContainer.addPlant(plant);
        }
    }


    @Override
    public int aliveAnimalsCount() {
        return boxesContainer.getAliveAnimals().size();
    }

    @Override
    public int plantsCount() {
        return getBoxesWithPlants().size();
    }
    @Override public int emptyPositionsCount() {
        return width*height- boxesContainer.size();
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d pos) {
        return  boxesContainer.strongestObjectAt(pos);
    }

    @Override
    public int deadAnimalsAgeSum() {
        return deadAnimals.getDeadAnimalsAgeSum();
    }
    @Override
    public int deadAnimalsCount() {
        return deadAnimals.getDeadAnimalsCount();
    }

    @Override
    public Boundary getCurrentBounds() {
        return boundary;
    }
    @Override
    public List<Animal> getAnimalsWithGenome(GenomesAbstract genomesAbstract) {
        return boxesContainer.getAnimalsWithGenome(genomesAbstract);
    }

    // do testów
    public List<Animal> getDeadAnimals() {
        return boxesContainer.getDeadAnimals();
    }

    public List<Animal> getAliveAnimals() {
        return boxesContainer.getAliveAnimals();
    }

    public List<Animal> getAnimals() {
        return boxesContainer.getAnimals();
    }


}
