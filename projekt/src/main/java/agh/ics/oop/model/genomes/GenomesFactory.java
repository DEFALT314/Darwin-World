package agh.ics.oop.model.genomes;

import agh.ics.oop.model.worldelements.Animal;
import agh.ics.oop.model.simulation.SimulationConfig;

public interface GenomesFactory {
    GenomesAbstract generateGenomes(SimulationConfig config);

    GenomesAbstract generateGenomes(Animal animal1, Animal animal2, SimulationConfig config);

}
