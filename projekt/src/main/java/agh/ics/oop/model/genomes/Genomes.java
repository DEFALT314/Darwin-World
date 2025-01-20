package agh.ics.oop.model.genomes;

import agh.ics.oop.model.worldelements.Animal;
import agh.ics.oop.model.simulation.SimulationConfig;

import java.util.List;

public class Genomes extends GenomesAbstract {
    public Genomes(List<Integer> genomes, SimulationConfig conf) {
        super(genomes, conf);
    }

    public Genomes(SimulationConfig conf) {
        super(conf);
    }

    public Genomes(Animal animal, Animal partner, SimulationConfig conf) {
        super(animal, partner, conf);
    }

    @Override
    public void mutateOneGenome() {
        int i = random.nextInt(conf.getGenomeLength());
        genomes.set(i, random.nextInt(8));
    }

}
