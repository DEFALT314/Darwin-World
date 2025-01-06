package agh.ics.oop.model;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class Animal implements WorldElement{
    private final Genomes genome;
    private MapDirection orientation;
    private Vector2d localisation;
    private int energy= 0;
    private int childrenCnt = 0;
    private Animal parentOne;
    private Animal parentTwo;
    private int descendantCnt= 0;
    private int deathDay = 0;
    private boolean isDead = false;
    private int plantEaten;
    private final SimulationConfig conf;
    public Animal(Vector2d localisation, SimulationConfig config){
        Random random = new Random();
        this.conf = config;
        this.orientation = MapDirection.values()[random.nextInt(8)];
        this.localisation = localisation;
        this.energy = config.getStartingAnimalEnergy();
        this.genome = new Genomes(config.getGenomeLength());
    }

    @Override
    public String toString() {
        return orientation.toString();
    }
    public boolean isAt(Vector2d position){
        return  localisation.equals(position);
    }
    public void move(Boundary boundary){
        int activeGenom = genome.getActiveGenom();
        rotateAnimal(activeGenom);
        Vector2d newPosition = localisation.add(orientation.toUnitVector());
        if (boundary.downLeft().getY() > newPosition.getY() || newPosition.getY() > boundary.upperRight().getY()){
            rotateAnimal(4);
        }
        else if (boundary.downLeft().getX() > newPosition.getX() ){
            localisation = new Vector2d(boundary.upperRight().getX(), newPosition.getY());
        }
        else if (boundary.upperRight().getX() < newPosition.getX()){
            localisation = new Vector2d(boundary.downLeft().getX(), newPosition.getY());
        }
        else {
            localisation = newPosition;
        }
    }
    public Optional<Animal> reproduce(Animal partner){
        if (this.energy < conf.getEnergyToReproduce() || partner.energy < conf.getEnergyToReproduce()){
            return Optional.empty();
        }
        this.energy -= conf.getEnergyToReproduce();
        partner.energy -= conf.getEnergyToReproduce();

        Genomes childGenomes = new Genomes(this, partner, conf);

    }

    private void rotateAnimal(int numberOfRotations) {
        for (int i = 0; i < numberOfRotations; i++) {
            orientation = orientation.next();
        }
    }

    public Vector2d getPosition() {
        return localisation;
    }
    public MapDirection getOrientation() {
        return orientation;
    }
    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public int getChildrenCnt() {
        return childrenCnt;
    }
    public void addChildrenCnt() {
        this.childrenCnt++;
    }

    public int getDescendantCnt() {
        return descendantCnt;
    }

    public Animal getParentOne() {
        return parentOne;
    }

    public Animal getParentTwo() {
        return parentTwo;
    }

    public Genomes getGenome() {
        return genome;
    }
    public boolean isDead() {
        return isDead;
    }
    public void setDead(boolean dead) {
        this.isDead = dead;
    }
    public void setDeathDay(int deathDay) {
        this.isDead = true;
        this.deathDay = deathDay;
    }

    public int getDeathDay() {
        return this.deathDay;
    }


    public void addDescendantCnt() {
        this.descendantCnt++;
    }

    public void setPlantEaten() {
        this.plantEaten += 1;
    }
    public int getPlantEaten() {
        return this.plantEaten;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return energy == animal.energy && childrenCnt == animal.childrenCnt && descendantCnt == animal.descendantCnt && deathDay == animal.deathDay && isDead == animal.isDead && plantEaten == animal.plantEaten && Objects.equals(genome, animal.genome) && orientation == animal.orientation && Objects.equals(localisation, animal.localisation) && Objects.equals(parentOne, animal.parentOne) && Objects.equals(parentTwo, animal.parentTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genome, orientation, localisation, energy, childrenCnt, parentOne, parentTwo, descendantCnt, deathDay, isDead, plantEaten);
    }
}

