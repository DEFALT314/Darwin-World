package agh.ics.oop.model;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class Animal implements WorldElement {

    private static final int GENOME_DIRECTIONS_COUNT = 8;
    private final GenomesAbstract genome;
    private final AnimalInfo info = new AnimalInfo();
    private final SimulationConfig conf;
    private final GenomesFactory genomesFactory;
    private MapDirection orientation;
    private Vector2d localisation;

    public Animal(Vector2d localisation, MapDirection direction, GenomesAbstract genomes, SimulationConfig config, GenomesFactory genomesFactory) {
        this.conf = config;
        this.genomesFactory = genomesFactory;
        this.orientation = direction;
        this.localisation = localisation;
        this.genome = genomes;
        initializeEnergy();
    }

    public Animal(Vector2d localisation, SimulationConfig config, GenomesFactory genomesFactory) {
        this(localisation, initializeRandomDirection(), genomesFactory.generateGenomes(config), config, genomesFactory);
    }

    public Animal(Vector2d localisation, GenomesAbstract genomes, Animal parentOne, Animal parentTwo, int energy, SimulationConfig config, GenomesFactory genomesFactory) {
        this.conf = config;
        this.genomesFactory = genomesFactory;
        this.localisation = localisation;
        this.genome = genomes;
        this.orientation = initializeRandomDirection();
        info.setEnergy(energy);
        info.setParentOne(parentOne);
        info.setParentTwo(parentTwo);
    }

    private static MapDirection initializeRandomDirection() {
        return MapDirection.values()[new Random().nextInt(GENOME_DIRECTIONS_COUNT)];
    }

    private void initializeEnergy() {
        info.setEnergy(conf.getStartingAnimalEnergy());
    }

    @Override
    public String toString() {
        return orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return localisation.equals(position);
    }

    public AnimalInfo getInfo() {
        return info;
    }

    public void move(Boundary boundary) {
        int activeGenom = genome.getActiveGenom();
        System.out.println(info.getEnergy());
        info.incrementDay();
        rotateAnimal(activeGenom);
        Vector2d newPosition = localisation.add(orientation.toUnitVector());


        if (boundary.downLeft().getY() > newPosition.getY() || newPosition.getY() > boundary.upperRight().getY()) {
            rotate180();
        } else if (boundary.downLeft().getX() > newPosition.getX()) {
            localisation = new Vector2d(boundary.upperRight().getX(), newPosition.getY());
        } else if (boundary.upperRight().getX() < newPosition.getX()) {
            localisation = new Vector2d(boundary.downLeft().getX(), newPosition.getY());
        } else {
            localisation = newPosition;
        }
    }

    private void rotate180() {
        rotateAnimal(4);
    }

    public Optional<Animal> reproduce(Animal partner) {
        AnimalInfo partnerInfo = partner.getInfo();
        if (info.getEnergy() < conf.getEnergyToReproduce() || partnerInfo.getEnergy() < conf.getEnergyToReproduce()) {
            return Optional.empty();
        }


        info.incrementDescendantAndChildCount();
        partnerInfo.incrementDescendantAndChildCount();

        GenomesAbstract childGenomes = genomesFactory.generateGenomes(this, partner, conf);
        info.subtractEnergy(conf.getEnergyToReproduce());
        partnerInfo.subtractEnergy(conf.getEnergyToReproduce());
        Animal child = new Animal(localisation, childGenomes, this, partner, conf.getEnergyToReproduce() * 2, conf, genomesFactory);
        return Optional.of(child);
    }

    private void rotateAnimal(int numberOfRotations) {
        for (int i = 0; i < numberOfRotations; i++) {
            orientation = orientation.next();
        }
    }

    @Override
    public Vector2d getPosition() {
        return localisation;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(genome, animal.genome) &&
                orientation == animal.orientation &&
                Objects.equals(localisation, animal.localisation) &&
                Objects.equals(info, animal.info);
    }

    public int getEnergy() {
        return info.getEnergy();
    }

    public int getChildrenCnt() {
        return info.getChildrenCnt();
    }

    @Override
    public int hashCode() {
        return Objects.hash(genome, orientation, localisation, info);
    }

    public GenomesAbstract getGenome() {
        return genome;
    }

    public void subtractEnergy(int i) {
        info.subtractEnergy(i);
    }

    public void addEnergy(int i) {
        info.addEnergy(i);
    }

    public void markAsDead() {
        info.markAsDead();
    }

    public boolean isDead() {
        return info.isDead();
    }

    public int getAge() {
        return info.getDay();
    }
}

