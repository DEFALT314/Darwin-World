package agh.ics.oop.model;

public interface GenomesFactory {
    public abstract GenomesAbstract generateGenomes(SimulationConfig config);

    public abstract GenomesAbstract generateGenomes(Animal animal1, Animal animal2, SimulationConfig config);

}
