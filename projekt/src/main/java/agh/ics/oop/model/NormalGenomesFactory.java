package agh.ics.oop.model;

public class NormalGenomesFactory implements  GenomesFactory{

    @Override
    public GenomesAbstract generateGenomes(SimulationConfig config) {
        return new Genomes(config);
    }

    @Override
    public GenomesAbstract generateGenomes(Animal animal1, Animal animal2, SimulationConfig config) {
        return new Genomes(animal1, animal2, config);
    }
}
