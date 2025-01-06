package agh.ics.oop.model;

public class Genomes extends GenomesAbstract{
    public Genomes(SimulationConfig conf) {
        super(conf);
    }

    public Genomes(Animal animal, Animal partner, SimulationConfig conf) {
        super(animal, partner, conf);
    }

    @Override
    public void mutateOneGenome() {
        int i = random.nextInt(conf.getGenomeLength());
        genomes.set(i,random.nextInt(8));
    }

}
