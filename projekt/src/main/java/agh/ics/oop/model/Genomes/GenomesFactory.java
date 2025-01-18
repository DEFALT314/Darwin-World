package agh.ics.oop.model.Genomes;

import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.Simulation.SimulationConfig;

public interface GenomesFactory {
    GenomesAbstract generateGenomes(SimulationConfig config);

    GenomesAbstract generateGenomes(Animal animal1, Animal animal2, SimulationConfig config);

}
