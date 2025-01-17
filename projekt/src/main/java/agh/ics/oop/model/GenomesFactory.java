package agh.ics.oop.model;

public interface GenomesFactory {
    GenomesAbstract generateGenomes(SimulationConfig config);

    GenomesAbstract generateGenomes(Animal animal1, Animal animal2, SimulationConfig config);

}
