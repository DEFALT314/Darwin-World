package agh.ics.oop.model.Genomes;

import agh.ics.oop.model.Simulation.SimulationConfig;
import agh.ics.oop.model.WorldElements.Animal;

public class SwapGenomesFactory implements GenomesFactory {
    @Override
    public GenomesAbstract generateGenomes(SimulationConfig config) {
        return new GenomesSwap(config);
    }

    @Override
    public GenomesAbstract generateGenomes(Animal animal1, Animal animal2, SimulationConfig config) {
        return new GenomesSwap(animal1, animal2, config);
    }
}
